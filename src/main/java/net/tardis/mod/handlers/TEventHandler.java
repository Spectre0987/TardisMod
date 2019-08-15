package net.tardis.mod.handlers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.tardis.mod.Tardis;
import net.tardis.mod.api.dimensions.IDimensionProperties;
import net.tardis.mod.common.blocks.BlockConsole;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.data.TimeLord;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.items.INeedMetadata;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.recipes.RecipeCinnabar;
import net.tardis.mod.common.recipes.RecipeKey;
import net.tardis.mod.common.recipes.RecipeRemote;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.SystemTemporalGrace;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.world.TardisWorldSavedData;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageConfigSync;
import net.tardis.mod.util.common.helpers.RiftHelper;
import net.tardis.mod.util.common.helpers.TardisHelper;

@Mod.EventBusSubscriber(modid = Tardis.MODID)
public class TEventHandler {

	public static TardisWorldSavedData data;

	@SubscribeEvent
	public static void grav(LivingUpdateEvent event) {
		if (event.getEntityLiving().world.provider instanceof IDimensionProperties) {
			IDimensionProperties prop = (IDimensionProperties) event.getEntityLiving().world.provider;
			if (!event.getEntityLiving().onGround && !prop.hasGravity()) {
				event.getEntityLiving().motionY = prop.transformGrav(event.getEntityLiving().motionY);
			}
		}
		//Honor the config option
		ResourceLocation key = EntityList.getKey(event.getEntityLiving());
		if(!TardisConfig.USE_ENTITIES.entities && key != null && key.getNamespace().equals(Tardis.MODID))
			event.getEntityLiving().setDead();
	}


	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		// Blocks
		for (Block block : TBlocks.BLOCKS) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
		}
		for (Item item : TItems.items) {
			if (item.getHasSubtypes()) {
				NonNullList<ItemStack> list = NonNullList.create();
				item.getSubItems(item.getCreativeTab(), list);
				for (int i = 0; i < list.size(); i++) {
					ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName(), "type=" + i));
				}
			}
			else {
				if(item instanceof INeedMetadata) {
					for(int meta : ((INeedMetadata)item).getMeta().keySet()) {
						ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Tardis.MODID, ((INeedMetadata)item).getMeta().get(meta)), "inventory"));
					}
				}
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			}
		}
	}


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : TBlocks.BLOCKS) {
			event.getRegistry().register(block);
		}
	}

	@SubscribeEvent
	public static void loadTardises(WorldEvent.Load event) {
		data = (TardisWorldSavedData) event.getWorld().getMapStorage().getOrLoadData(TardisWorldSavedData.class, "tardis");
		if (data == null)
			data = new TardisWorldSavedData("tardis");
	}

	@SubscribeEvent
	public static void saveTardises(WorldEvent.Save event) {
		event.getWorld().getMapStorage().setData("tardis", data);
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		event.getRegistry().register(new RecipeKey(Tardis.MODID + ":spare_key"));
		event.getRegistry().register(new RecipeCinnabar(Tardis.MODID + ":cinnabar"));
		event.getRegistry().register(new RecipeRemote("remote_bind"));
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void stopHurt(LivingHurtEvent event) {
		
		EntityLivingBase e = event.getEntityLiving();
		if (!(e instanceof IMob) && !TardisHelper.getTardisForPosition(e.getPosition()).equals(BlockPos.ORIGIN)) {
			TileEntityTardis tardis = (TileEntityTardis) e.world.getTileEntity(TardisHelper.getTardisForPosition(e.getPosition()));
			if (tardis != null && tardis.getSystem(SystemTemporalGrace.class) != null) {
				SystemTemporalGrace sys = tardis.getSystem(SystemTemporalGrace.class);
				if (sys.getHealth() > 0.0F) {
					sys.setHealth(sys.getHealth() - 0.01F);
					event.setCanceled(true);
				}
			}
		}
		//RWF
		if(event.getEntityLiving().getRidingEntity() instanceof EntityTardis) {
			event.setCanceled(true);
		}
	}


	@SubscribeEvent
	public static void onDalekShot(LivingAttackEvent e){
		DamageSource source = e.getSource();
		Entity attacked = e.getEntity();
		if(source != null && attacked != null && source.getImmediateSource() != null){
			if(attacked instanceof EntityDalek && source.getImmediateSource() instanceof EntityArrow){
				if(!attacked.world.isRemote){
					attacked.world.playSound(null, e.getEntity().getPosition(), SoundEvents.ENTITY_IRONGOLEM_HURT, SoundCategory.HOSTILE, 1, 1);
					e.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void givePlayerKey(PlayerLoggedInEvent event) {
		if (TardisConfig.MISC.givePlayerKey) {
			EntityPlayer player = event.player;
			if (!TardisHelper.hasTardis(player.getGameProfile().getId())) {
				InventoryHelper.spawnItemStack(player.world, player.posX, player.posY, player.posZ, new ItemStack(TBlocks.tardis_coral));
			}
		}
		if (!event.player.world.isRemote) {
			try {
				HashMap<String, Long> map = new HashMap<String, Long>();
				File f = new File(event.player.world.getMinecraftServer().getDataDirectory() + "/pending_keys.json");
				if (f.exists()) {
					JsonReader jr = new JsonReader(new FileReader(f));
					jr.beginObject();
					while (jr.hasNext()) {
						map.put(jr.nextName(), Long.parseLong(jr.nextString()));
					}
					jr.endObject();
					jr.close();

					if (map.containsKey(event.player.getGameProfile().getId().toString())) {
						ItemStack stack = new ItemStack(TItems.key);
						ItemKey.setPos(stack, BlockPos.fromLong(map.get(event.player.getGameProfile().getId().toString())));
						event.player.inventory.addItemStackToInventory(stack);

						GsonBuilder gb = new GsonBuilder();
						gb.setPrettyPrinting();
						JsonWriter jw = gb.create().newJsonWriter(new FileWriter(f));
						jw.beginObject();
						map.remove(event.player.getGameProfile().getId().toString());
						for (String name : map.keySet()) {
							jw.name(name).value(map.get(name).toString());
						}
						jw.endObject();
						jw.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SubscribeEvent
	public static void giveManual(PlayerInteractEvent.RightClickBlock event) {
		if (!event.getWorld().isRemote) {
			World world = event.getWorld();
			IBlockState state = world.getBlockState(event.getPos());
			if (state.getBlock() instanceof BlockConsole) {
				EntityPlayer player = event.getEntityPlayer();
				if(player.getHeldItemMainhand().getItem() == Items.EGG) {
					InventoryHelper.spawnItemStack(world, player.posX, player.posY, player.posZ, new ItemStack(TBlocks.tree_egg));
					player.getHeldItemMainhand().shrink(1);
				}
				else if(player.getHeldItemMainhand().getItem() == Items.BOOK){
					InventoryHelper.spawnItemStack(world, player.posX, player.posY, player.posZ, new ItemStack(TItems.manual));
					player.getHeldItemMainhand().shrink(1);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onMissingMappingSound(RegistryEvent.MissingMappings<SoundEvent> e) {
		for (RegistryEvent.MissingMappings.Mapping<SoundEvent> map : e.getAllMappings()) {
			if (map.key.toString().equals("tardis:sonic")) {
				map.ignore();
			}
		}
	}
	
	@SubscribeEvent
	public static void onMissingMappingEntity(RegistryEvent.MissingMappings<EntityEntry> e) {
		for (RegistryEvent.MissingMappings.Mapping<EntityEntry> map : e.getAllMappings()) {
			if (map.key.toString().equals("tardis:raider")) {
				map.ignore();
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void useRegen(LivingDeathEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if(player.getRidingEntity() instanceof EntityTardis) {
				event.setCanceled(true);
				return;
			}
			if (TimeLord.isTimeLord(player)) {
				if (TimeLord.useRegen(player)) {
					event.setCanceled(true);
					player.setHealth(player.getMaxHealth());
					player.sendMessage(new TextComponentString("You have " + TimeLord.getRegens(player) + " regenerations left."));
					Potion[] me = {MobEffects.WEAKNESS, MobEffects.SLOWNESS, MobEffects.MINING_FATIGUE, MobEffects.ABSORPTION, MobEffects.REGENERATION, MobEffects.HUNGER};
					for (Potion p : me) {
						player.addPotionEffect(new PotionEffect(p, 600, 0));
					}
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 300, 0));
					return;
				}
				else TimeLord.setTimeLord(player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
		EntityPlayer player = e.player;
		if (Loader.isModLoaded(TStrings.ModIds.OPTIFINE))
			player.sendStatusMessage(new TextComponentTranslation(TStrings.OPTIFINE_INSTALLED), false);
		if(!e.player.world.isRemote)
			NetworkHandler.NETWORK.sendTo(new MessageConfigSync(), (EntityPlayerMP) player);
	}
	
	@SubscribeEvent
	public static void stopAttemptingTheStabby(LivingSetAttackTargetEvent event) {
		if(event.getEntityLiving() instanceof EntityLiving && event.getTarget() != null && event.getTarget().getRidingEntity() instanceof EntityTardis)
			((EntityLiving)event.getEntityLiving()).setAttackTarget(null);
	}

	@SubscribeEvent
	public static void saveChunkData(ChunkDataEvent.Save event) {
		RiftHelper.writeRiftStatus(event.getChunk(), event.getWorld(), event.getData());
	}

	@SubscribeEvent
	public static void loadChunkData(ChunkDataEvent.Load event) {
		RiftHelper.readRiftStatus(event.getChunk(), event.getWorld(), event.getData());
	}
}

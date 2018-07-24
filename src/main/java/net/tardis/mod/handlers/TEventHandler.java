package net.tardis.mod.handlers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.GuiVortexM;
import net.tardis.mod.common.blocks.BlockConsole;
import net.tardis.mod.common.blocks.TBlocks;
import net.tardis.mod.common.blocks.interfaces.IRenderBox;
import net.tardis.mod.common.blocks.interfaces.IUnbreakable;
import net.tardis.mod.common.data.TimeLord;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.items.clothing.ItemSpaceSuit;
import net.tardis.mod.common.recipes.RecipeKey;
import net.tardis.mod.common.world.TardisWorldSavedData;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.helpers.Helper;

@Mod.EventBusSubscriber
public class TEventHandler {
	
	public static TardisWorldSavedData data;
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		// Blocks
		for (Block block : TBlocks.blocks) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
		}
		for (Item item : TItems.items) {
			if (item.getHasSubtypes()) {
				NonNullList<ItemStack> list = NonNullList.create();
				item.getSubItems(item.getCreativeTab(), list);
				for (int i = 0; i < list.size(); i++) {
					ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName(), "type=" + i));
				}
			} else
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}

	}
	
	@SubscribeEvent
	public static  void registerItems(RegistryEvent.Register<Item> event) {
		for (Item item : TItems.items) {
			event.getRegistry().register(item);
		}
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : TBlocks.blocks) {
			event.getRegistry().register(block);
		}
	}
	
	@SubscribeEvent
	public static void loadTardises(WorldEvent.Load event) {
		data = (TardisWorldSavedData) event.getWorld().getMapStorage().getOrLoadData(TardisWorldSavedData.class, "tardis");
		if (data == null) data = new TardisWorldSavedData("tardis");
	}
	
	@SubscribeEvent
	public static void saveTardises(WorldEvent.Save event) {
		event.getWorld().getMapStorage().setData("tardis", data);
	}
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		event.getRegistry().register(new RecipeKey(Tardis.MODID + ":spare_key"));
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
	public static void stopRender(RenderPlayerEvent.Pre event) {
		if (event.getEntityPlayer().getRidingEntity() != null && event.getEntityPlayer().getRidingEntity() instanceof EntityTardis) {
			Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
			event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void stopHurt(LivingHurtEvent event) {
		if (event.getEntityLiving().getRidingEntity() != null) {
			Entity e = event.getEntityLiving().getRidingEntity();
			event.setCanceled(e instanceof EntityTardis);
		}
	}
	
	@SubscribeEvent
	public static  void makeTrueUnbreakable(BlockEvent.BreakEvent e) {
		e.setCanceled(e.getState().getBlock() instanceof IUnbreakable);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public static void cancelBBRender(DrawBlockHighlightEvent event) {
		World world = event.getPlayer().world;
		BlockPos pos = event.getTarget().getBlockPos();
		if (pos != null && !pos.equals(BlockPos.ORIGIN)) {
            if (world.getBlockState(pos).getBlock() instanceof IRenderBox) {
                IRenderBox block = (IRenderBox) world.getBlockState(pos).getBlock();
                event.setCanceled(!block.shouldRenderBox());
            }
        }
	}
	
	@SubscribeEvent
	public static void givePlayerKey(PlayerLoggedInEvent event) {
		if (TardisConfig.MISC.givePlayerKey) {
			EntityPlayer player = event.player;
			boolean hasKey = false;
			for(ItemStack stack : player.inventory.mainInventory) {
				if(stack.getItem() instanceof ItemKey) {
					hasKey = true;
				}
			}
			if(!hasKey) {
				EntityItem ei = new EntityItem(player.world, player.posX, player.posY, player.posZ, new ItemStack(TItems.key));
				if(!player.world.isRemote) {
					player.world.spawnEntity(ei);
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void giveManual(PlayerInteractEvent.RightClickBlock event) {
		if(!event.getWorld().isRemote) {
			if(event.getItemStack().getItem() == Items.BOOK) {
				World world = event.getWorld();
				IBlockState state = world.getBlockState(event.getPos());
				if(state.getBlock() instanceof BlockConsole) {
					EntityPlayer player = event.getEntityPlayer();
					int slot = Helper.getSlotForItem(player, Items.BOOK);
					if(slot != -1) {
						player.inventory.getStackInSlot(slot).shrink(1);
						EntityItem ei = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(TItems.manual));
						world.spawnEntity(ei);
					}
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void useVortexM(PlayerInteractEvent.RightClickEmpty e) {
		if(e.getEntityPlayer().getHeldItemMainhand().isEmpty() && e.getEntityPlayer().inventory.hasItemStack(new ItemStack(TItems.vortex_manip))) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiVortexM());
		}
	}
	
	@SubscribeEvent
	public static void stopDrown(LivingUpdateEvent event) {
		EntityLivingBase base = event.getEntityLiving();
		int count = 0;
		for(ItemStack stack : base.getArmorInventoryList()) {
			if(stack.getItem() instanceof ItemSpaceSuit) {
				count++;
			}
		}
		if(count >= 3) {
			base.setAir(200);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void useRegen(LivingDeathEvent event) {
		if(event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.getEntityLiving();
			if(TimeLord.isTimeLord(player)) {
				if(TimeLord.useRegen(player)) {
					event.setCanceled(true);
					player.setHealth(player.getMaxHealth());
					player.sendMessage(new TextComponentString("You have " + TimeLord.getRegens(player) + " regenerations left."));
					
				}
				else TimeLord.setTimeLord(player);
			}
		}
	}
}

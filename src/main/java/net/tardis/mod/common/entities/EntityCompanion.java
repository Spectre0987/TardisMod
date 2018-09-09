package net.tardis.mod.common.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.guis.GUICompanion;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

import java.util.UUID;

public class EntityCompanion extends EntityCreature implements IInventory, IEntityOwnable{

	public static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(EntityCompanion.class, DataSerializers.BOOLEAN);
	public static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityCompanion.class, DataSerializers.STRING);
    private NonNullList<ItemStack> inv = NonNullList.withSize(27, ItemStack.EMPTY);
	public BlockPos tardisPos = BlockPos.ORIGIN;
    private UUID player;
	
	public EntityCompanion(World worldIn) {
		super(worldIn);
		this.stepHeight = 1;
		this.tasks.addTask(1, new EntityAIFollowOwner(this, 1D));
		this.tasks.addTask(2, new EntityAIWander(this, 0.5D));
		this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 30));
		this.tasks.addTask(0, new EntityAIEnterTardis(this, 1.0D));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(SITTING, false);
		this.getDataManager().register(TYPE, EnumCompanionType.values()[rand.nextInt(EnumCompanionType.values().length - 1)].name());
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	public EnumCompanionType getType() {
		return Enum.valueOf(EnumCompanionType.class, this.getDataManager().get(TYPE));
	}
	
	public void setType(EnumCompanionType type) {
		this.getDataManager().set(TYPE, type.name());
	}
	
	@Override
	public int getSizeInventory() {
		return inv.size();
	}

	@Override
	public boolean isEmpty() {
		return inv.isEmpty();
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return index < inv.size() ? inv.get(index) : ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return this.getStackInSlot(index).splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack s = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		return s;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if(index < inv.size()) {
			inv.set(index, stack);
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		inv.clear();
	}

	public void setOwner(EntityPlayer player){
		this.player = player.getGameProfile().getId();
	}
	
	public EntityPlayer getOwner() {
		if(this.player != null && world.getPlayerEntityByUUID(player) != null) {
			return world.getPlayerEntityByUUID(player);
		}
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GUICompanion(this.getEntityId()));
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getType().getName());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		for(ItemStack stack : inv) {
			list.appendTag(stack.writeToNBT(new NBTTagCompound()));
		}
		compound.setTag("inv", list);
		compound.setString("owner", player != null ? player.toString() : "");
		compound.setString("type", this.getType().name());
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		int id = 0;
		NBTTagList list = compound.getTagList("inv", NBT.TAG_COMPOUND);
		for(NBTBase base : list) {
			inv.set(id, new ItemStack((NBTTagCompound)base));
			++id;
		}
		if(!compound.getString("owner").isEmpty()) player = UUID.fromString(compound.getString("owner"));
        if (compound.hasKey("type") && !compound.getString("type").isEmpty())
            this.setType(Enum.valueOf(EnumCompanionType.class, compound.getString("type")));
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		if(world.isRemote)
			openGui();
		else this.setOwner(player);
		return true;
	}
	
	@Override
	public boolean getAlwaysRenderNameTag() {
		return true;
	}

    public enum EnumCompanionType {
		JOE("joe", "Joe"),
		CLAIRE("claire", "Claire"),
		VASSILIS("vassilis", "Vassilis"),
		NONE("", "");
		
		ResourceLocation skin;
        String formattedName;
		
		EnumCompanionType(String name, String formatName) {
			skin = new ResourceLocation(Tardis.MODID, "textures/entity/" + name + ".png");
			formattedName = formatName;
		}
		
		public ResourceLocation getTexture() {
			return this.skin;
		}
		
		public String getName() {
			return this.formattedName;
		}
	}

	@Override
	public UUID getOwnerId() {
		return this.player;
	}
	
	public static class EntityAIFollowOwner extends EntityAIBase{

		EntityCompanion entity;
		double speed;
		
		public EntityAIFollowOwner(EntityCompanion entity, double speed) {
			this.entity = entity;
			this.speed = speed;
			this.setMutexBits(1);
		}
		
		@Override
		public boolean shouldExecute() {
			return entity.getOwner() != null && !entity.getDataManager().get(EntityCompanion.SITTING);
		}

		@Override
		public boolean isInterruptible() {
			return true;
		}

		@Override
		public void updateTask() {
			super.updateTask();
			EntityPlayer player = entity.getOwner();
			if(player == null) return;
			if(player.getPositionVector().distanceTo(entity.getPositionVector()) > 5)entity.moveHelper.setMoveTo(player.posX, player.posY, player.posZ, speed);
		}

		@Override
		public boolean shouldContinueExecuting() {
			return entity.getOwner() != null && entity.getOwner().getPositionVector().distanceTo(entity.getPositionVector()) > 5 && !entity.getDataManager().get(EntityCompanion.SITTING);
		}
		
	}
	
	public static class EntityAIEnterTardis extends EntityAIBase{

		double speed;
		EntityCompanion comp;
		
		public EntityAIEnterTardis(EntityCompanion com, double s) {
			this.comp = com;
			this.speed = s;
			this.setMutexBits(1);
		}
		
		@Override
		public boolean shouldExecute() {
			return !comp.tardisPos.equals(BlockPos.ORIGIN);
		}

		@Override
		public boolean shouldContinueExecuting() {
			return this.shouldExecute();
		}

		@Override
		public void updateTask() {
			if(Helper.blockPosToVec3d(comp.tardisPos).distanceTo(comp.getPositionVector()) > 3) {
				comp.moveHelper.setMoveTo(comp.tardisPos.getX(), comp.tardisPos.getY(), comp.tardisPos.getZ(), speed);
			}
			else {
				comp.tardisPos = BlockPos.ORIGIN;
				TileEntityTardis tardis;
				for(TileEntity te : comp.world.loadedTileEntityList) {
					if(te.getPos().distanceSq(comp.getPosition()) <= Math.pow(3, 2) && te instanceof TileEntityDoor) {
						
					}
				}
			}
		}
		
	}

	public boolean getSit() {
		return this.getDataManager().get(SITTING);
	}
	
	public void setSit(boolean sit) {
		this.getDataManager().set(SITTING, sit);
	}

}

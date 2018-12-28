package net.tardis.mod.common.systems;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.TileEntityTardis.EnumCourseCorrect;
import net.tardis.mod.network.NetworkHandler;
import net.tardis.mod.network.packets.MessageMissControl;

import java.util.Random;

public class SystemStabilizers extends BaseSystem{

	private boolean isStabilized = false;
	private int controlsMissed = 0;
	private Random rand = new Random();
	
	public SystemStabilizers() {
		
	}
	
	@Override
	public void onUpdate(World world, BlockPos consolePos) {
		if (world == null || consolePos == null) return;
		if(!world.isRemote) {
			if(world.getWorldTime() % 150 == 0) {
				TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(consolePos);
				if(!this.isStabilized) {
					if(tardis.isInFlight()) {
						if(tardis.getCourseCorrect() != EnumCourseCorrect.NONE) {
							++controlsMissed;
							this.explode(world, consolePos);
							SystemFlight f = tardis.getSystem(SystemFlight.class);
							f.setHealth(f.getHealth() - 0.05F);
							tardis.setHealth(tardis.getHealth() - 0.01F);
						}
						EnumCourseCorrect newEvent = EnumCourseCorrect.values()[rand.nextInt(EnumCourseCorrect.values().length)];
						tardis.setCourseEvent(newEvent);
						world.getEntitiesWithinAABB(EntityPlayer.class, Block.FULL_BLOCK_AABB.offset(consolePos).grow(16)).forEach(entityPlayer -> entityPlayer.sendStatusMessage(new TextComponentString(newEvent.getTranslation().getFormattedText()), true));
					}
					else {
						this.controlsMissed = 0;
						tardis.setCourseEvent(EnumCourseCorrect.NONE);
					}
				}
				else {
					this.controlsMissed = 0;
					tardis.setCourseEvent(EnumCourseCorrect.NONE);
				}
				if(this.getHealth() <= 0)
					this.isStabilized = false;
			}
			
		}
	}

	public void explode(World world, BlockPos pos) {
		if(!world.isRemote) {
			world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1F, 1F);
			NetworkHandler.NETWORK.sendToAllAround(new MessageMissControl(pos), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 25D));
			TileEntityTardis tardis = (TileEntityTardis)world.getTileEntity(pos);
			tardis.setDesination(tardis.getDestination().add(rand.nextInt(20) - 10, rand.nextInt(20) - 10, rand.nextInt(20) - 10), tardis.getTargetDim());
		}
	}
	
	@Override
	public void damage() {}

	@Override
	public Item getRepairItem() {
		return TItems.stabilizers;
	}

	@Override
	public String getNameKey() {
		return "system.tardis.stabilizers";
	}

	@Override
	public void wear() {
		if(this.isOn()) {
			this.setHealth(this.getHealth() - 0.01F);
		}
	}

	@Override
	public boolean shouldStopFlight() {
		return false;
	}
	
	public boolean isOn() {
		return this.isStabilized;
	}
	
	public void setOn(boolean on) {
		this.isStabilized = on;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.isStabilized = tag.getBoolean("stab");
		this.controlsMissed = tag.getInteger("cont");
	}

	@Override
	public NBTTagCompound writetoNBT(NBTTagCompound tag) {
		tag.setBoolean("stab", this.isStabilized);
		tag.setInteger("cont", this.controlsMissed);
		return super.writetoNBT(tag);
	}

}

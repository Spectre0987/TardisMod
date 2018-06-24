package net.tardis.mod.integrations;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.common.blocks.BlockConsole;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.items.ItemKey;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class WeepingAngel {
	
	public static void preInit() {
		MinecraftForge.EVENT_BUS.register(new WeepingAngel());
	}
	
	
	@SubscribeEvent
	public void hijackAngel(LivingEvent.LivingUpdateEvent event) {
		if(event.getEntityLiving() instanceof EntityAngel) {
			if(!event.getEntityLiving().world.isRemote) {
				EntityAngel angel = (EntityAngel)event.getEntityLiving();
				if(event.getEntityLiving().dimension != TDimensions.id) {
					ItemStack stack = angel.getHeldItemMainhand();
					if(stack.getItem() instanceof ItemKey) {
						WorldServer ws = DimensionManager.getWorld(TDimensions.id);
						BlockPos consolePos = ItemKey.getPos(stack);
						if(consolePos != null && ws !=null) {
							TileEntity te = ws.getTileEntity(consolePos);
							if(te instanceof TileEntityTardis) {
								BlockPos pos = ((TileEntityTardis)te).getLocation();
								angel.setAttackTarget(null);
								angel.setRevengeTarget(null);
								angel.getMoveHelper().setMoveTo(pos.getX(),pos.getY(),pos.getZ(), angel.getAIMoveSpeed());
							}
						}
					}
				}
				else {
					AxisAlignedBB bb = angel.getEntityBoundingBox().grow(16D);
					World world = angel.world;
					for(int x = (int)bb.minX; x < bb.maxX; ++x) {
						for(int y = (int)bb.minY; y < bb.maxY; ++y) {
							for(int z = (int)bb.minZ; z < bb.maxZ; ++z) {
								BlockPos newPos = new BlockPos(x,y,z);
								if(world.getBlockState(newPos).getBlock() instanceof BlockConsole) {
									TileEntityTardis tardis = ((TileEntityTardis)world.getTileEntity(newPos));
									if(tardis.fuel > 0.001) {
										tardis.fuel -= 0.001;
										tardis.markDirty();
									}
								}
							}
						}
					}
				}
			}
		}
	}

}

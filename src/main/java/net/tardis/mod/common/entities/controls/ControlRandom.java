package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.common.helpers.Helper;
import scala.util.Random;

public class ControlRandom extends EntityControl {
	
	public static int maxDist;
	
	public ControlRandom(TileEntityTardis tardis) {
		super(tardis);
		maxDist = TardisConfig.MISC.maxRandom;
	}
	
	public ControlRandom(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(-10, -1.5, -1.5);
		}
		return Helper.convertToPixels(0, -1, 9);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			Random rand = new Random();
			BlockPos loc = tardis.getLocation();
            if (loc == null) {
                loc = player.getPosition();
            }
			if(maxDist == 0) {
				WorldServer ws = DimensionManager.getWorld(tardis.dimension);
				if(ws != null) {
					int size = ws.getWorldBorder().getSize();
					tardis.setDesination(new BlockPos(rand.nextInt(size) - size / 2, 64, rand.nextInt(size) - size / 2), tardis.getTargetDim());
				}
			}
			else tardis.setDesination(new BlockPos((loc.getX() + rand.nextInt(rand.nextInt(maxDist))) - rand.nextInt(maxDist) / 2, 64, (loc.getZ() + rand.nextInt(maxDist)) - rand.nextInt(maxDist) / 2), tardis.getTargetDim());
		} else
			this.ticks = 20;
	}
	
}

package net.tardis.mod.common.tileentity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.tardis.mod.common.blocks.BlockTractorBeam;

public class TileEntityTractorBeam extends TileEntity implements ITickable{

	private AxisAlignedBB tractorAABB;
	public int pushTicks = 0;
	
	@Override
	public void onLoad() {
		super.onLoad();
		tractorAABB = new AxisAlignedBB(this.getPos().getX(), 0, this.getPos().getZ(),
				this.getPos().getX() + 1, this.getPos().getY() + 1, this.getPos().getZ() + 1);
	}

	@Override
	public void update() {
		if(tractorAABB != null) {
			List<Entity> entitiesInBeam = world.getEntitiesWithinAABB(Entity.class, tractorAABB);
			for(Entity entity : entitiesInBeam) {
				if(this.shouldRise()) {
					if(entity.motionY < 1)
						entity.motionY += 0.1;
				}
				else {
					if(entity.motionY < -0.1)
						entity.motionY += 0.05;
				}
				entity.fallDistance = 0.0F;
			}
			if(!world.isRemote && pushTicks == 0 && !world.getBlockState(getPos()).getValue(BlockTractorBeam.UP) && entitiesInBeam.isEmpty())
				world.setBlockState(getPos(), world.getBlockState(this.getPos()).withProperty(BlockTractorBeam.UP, true));
			
			if(this.pushTicks > 0)
				--pushTicks;
		}
	}

	public boolean shouldRise() {
		return world.getBlockState(this.getPos()).getValue(BlockTractorBeam.UP);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return this.tractorAABB;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}
	
	@Override
	public double getMaxRenderDistanceSquared()
    {
        return 1000000.0D;
    }
}

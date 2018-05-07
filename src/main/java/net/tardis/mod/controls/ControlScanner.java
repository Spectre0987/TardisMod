package net.tardis.mod.controls;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class ControlScanner extends EntityControl {
	
	public ControlScanner(TileEntityTardis tardis) {
		super(tardis);
		this.setRotation(180, 43);
	}
	
	public ControlScanner(World world) {
		super(world);
		this.setSize(0.25F, 0.25F);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(23, 16, 0);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
			if (!tardis.isInFlight()) {
				WorldServer ws = DimensionManager.getWorld(tardis.dimension);
				IBlockState state = ws.getBlockState(tardis.getLocation().down().north());
				player.sendMessage(new TextComponentTranslation(state.getBlock() != Blocks.AIR ? "scanner.safe" : "scanner.unsafe"));
			}
		}
	}
	
}

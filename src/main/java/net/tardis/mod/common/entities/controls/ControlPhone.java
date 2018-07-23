package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.helpers.Helper;

public class ControlPhone extends EntityControl{

	public ControlPhone(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlPhone(World world) {
		super(world);
		this.setSize(Helper.precentToPixels(4), Helper.precentToPixels(4));
	}

	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(0,-1,-8);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
			WorldServer ws = DimensionManager.getWorld(0);
			BlockPos villagePos = ws.getChunkProvider().getNearestStructurePos(ws, "Village", tardis.getLocation(), true);
			if(villagePos != null && !villagePos.equals(BlockPos.ORIGIN)) {
				for(int i = 0; i < 25; ++i) {
					EntityDalek dalek = new EntityDalek(ws);
					BlockPos pos = ws.getTopSolidOrLiquidBlock(villagePos.add(rand.nextInt(100) - 50, 64, rand.nextInt(100) - 50));
					dalek.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
					ws.spawnEntity(dalek);
				}
				tardis.setDesination(villagePos, 0);
				player.sendStatusMessage(new TextComponentString(new TextComponentTranslation(TStrings.EVENT.DALEK_INVASION).getFormattedText() + " " + Helper.formatBlockPos(villagePos)), true);
			}
		}
	}

}

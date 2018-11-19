package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.entities.EntityDalek;
import net.tardis.mod.common.enums.EnumEvent;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.systems.SystemAntenna;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
import net.tardis.mod.config.TardisConfig;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlPhone extends EntityControl{

	public ControlPhone(TileEntityTardis tardis) {
		super(tardis);
		if(tardis instanceof TileEntityTardis03)
			this.box = new AxisAlignedBB(0, 0, 0, Helper.precentToPixels(4F), 0.0625, 1);
	}
	
	public ControlPhone(World world) {
		super(world);
	}

	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(0, -2, 8);
		}
		if(tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(-14, 1, 8);
		return Helper.convertToPixels(0,-1,-8);
	}

	@Override
	public void preformAction(EntityPlayer player) {
		TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
		if(!world.isRemote) {
			if(tardis.currentEvent == EnumEvent.DALEK) {
				this.genDalekInvasion(player);
			}
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!world.isRemote && TardisConfig.MISC.invasions) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
			if(tardis != null) {
				SystemAntenna sys = tardis.getSystem(SystemAntenna.class);
				if(sys != null && sys.getHealth() > 0F) {
					if(tardis.currentEvent != EnumEvent.NONE) {
						if(world.getTotalWorldTime() % 80 == 0) {
							world.playSound(null, this.getPosition(), TSounds.phone, SoundCategory.BLOCKS, 1F, 1F);
						}
					}
					else if(world.getTotalWorldTime() % 24000 == 0) {
						if(rand.nextInt(4) == 0) {
							tardis.currentEvent = EnumEvent.DALEK;
						}
					}
				}
				else {
					tardis.currentEvent = EnumEvent.NONE;
				}
			}
		}
	}

	public void genDalekInvasion(EntityPlayer player) {
		if(!world.isRemote) {
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(getConsolePos());
			WorldServer ws = world.getMinecraftServer().getWorld(0);
			BlockPos villagePos = ws.getChunkProvider().getNearestStructurePos(ws, "Village", tardis.getLocation(), true);
			if(villagePos != null && !villagePos.equals(BlockPos.ORIGIN)) {
				for(int i = 0; i < 10; ++i) {
					EntityDalek dalek = new EntityDalek(ws);
					BlockPos pos = ws.getTopSolidOrLiquidBlock(villagePos.add(rand.nextInt(20) - 10, 64, rand.nextInt(20) - 10));
					dalek.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
					ws.spawnEntity(dalek);
				}
				for(int c = 0; c < 5; ++c) {
					EntityCompanion comp = new EntityCompanion(ws);
					BlockPos pos = ws.getTopSolidOrLiquidBlock(villagePos.add(rand.nextInt(50) - 25, 1, rand.nextInt(50) - 25));
					comp.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
					ws.spawnEntity(comp);
				}
				tardis.setDesination(villagePos, 0);
				player.sendStatusMessage(new TextComponentString(new TextComponentTranslation(TStrings.EVENT.DALEK_INVASION).getFormattedText() + " " + Helper.formatBlockPos(villagePos)), false);
				tardis.currentEvent = EnumEvent.NONE;
			}
		}
	}
}

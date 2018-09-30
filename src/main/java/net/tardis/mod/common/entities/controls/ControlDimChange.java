package net.tardis.mod.common.entities.controls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.strings.TStrings;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlDimChange extends EntityControl {
	
	public ControlDimChange(TileEntityTardis tardis) {
		super(tardis);
	}
	
	public ControlDimChange(World world) {
		super(world);
		this.setSize(0.125F, 0.0625F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if(tardis instanceof TileEntityTardis01 || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(2, -2.5, 13.5);
		}
		return Helper.convertToPixels(-1.5, -2, -12);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if (!world.isRemote) {
			Integer[] ids = DimensionManager.getStaticDimensionIDs();
			TileEntityTardis tardis = (TileEntityTardis) world.getTileEntity(this.getConsolePos());
			if (!player.isSneaking())
				++tardis.dimIndex;
			else
				--tardis.dimIndex;
			
			if (tardis.dimIndex >= ids.length || tardis.dimIndex < 0) tardis.dimIndex = 0;
			
			int dim = ids[tardis.dimIndex];
			if (Helper.isDimensionBlocked(dim)) this.preformAction(player);
			tardis.setTargetDimension(ids[tardis.dimIndex]);
			player.sendStatusMessage(new TextComponentString(new TextComponentTranslation(TStrings.TARDIS_DIMENSION).getFormattedText() + " " + Helper.formatDimensionName(DimensionManager.createProviderFor(ids[tardis.dimIndex]).getDimensionType().getName())), true);
		} else
			this.ticks = 20;
	}
	
	public int nextDI(int index, int size) {
		index += 1;
		if (index < 0 || index > size) {
			return 0;
		}
		return index;
	}
}

package net.tardis.mod.cap;

import java.text.DecimalFormat;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.systems.TardisSystems.BaseSystem;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.PlayerHelper;

public class TardisTrackerCapability implements ITardisTracker{

	public static final DecimalFormat FORMAT = new DecimalFormat("##.#");
	BlockPos consolePos;
	TileEntityTardis console;
	private boolean isSelected = false;
	
	//For Client only
	private BlockPos exteriorPos;
	
	public TardisTrackerCapability() {}
	
	@Override
	public void setConsole(BlockPos pos) {
		consolePos = pos.toImmutable();
		this.findConsoleInstance();
	}

	@Override
	public BlockPos getConsolePos() {
		return this.consolePos;
	}

	@Override
	public TileEntityTardis getConsole() {
		return console;
	}

	@Nullable
	@Override
	public BlockPos getExteriorPos() {
		if(consolePos == null)
			return null;
		if(console == null)
			this.findConsoleInstance();
		if(console == null)
			return null;
		return console.getLocation();
	}

	@Override
	public void deserialize(NBTTagCompound tag) {
		this.consolePos = BlockPos.fromLong(tag.getLong("console"));
		
		if(tag.hasKey("exterior"))
			this.exteriorPos = BlockPos.fromLong(tag.getLong("exterior"));
	}

	@Override
	public NBTTagCompound write(NBTTagCompound tag) {
		tag.setLong("console", this.consolePos.toLong());
		if(console != null)
			tag.setLong("exterior", this.console.getLocation().toLong());
		return tag;
	}

	//ServerSide only
	@Override
	public void findConsoleInstance() {
		if(consolePos == null)
			return;
		WorldServer ws = DimensionManager.getWorld(TDimensions.TARDIS_ID);
		if(ws != null) {
			TileEntity te = ws.getTileEntity(this.consolePos);
			if(te instanceof TileEntityTardis)
				this.console = (TileEntityTardis)te;
		}
	}
	
	@Override
	public void onRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		TileEntityTardis console = this.getConsole();
		if(console != null) {
			for(BaseSystem sub : console.systems) {
				PlayerHelper.sendMessage(playerIn, new TextComponentString(new TextComponentTranslation(sub.getNameKey()).getFormattedText() + " " + FORMAT.format(sub.getHealth() * 100) + "%"), false);
			}
		}
		else PlayerHelper.sendMessage(playerIn, "Remote not bound!", true);
	}
}

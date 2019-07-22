package net.tardis.mod.common.entities.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.EnumExterior;
import net.tardis.mod.client.guis.GuiTelepathicCircuts;
import net.tardis.mod.common.entities.EntityTardis;
import net.tardis.mod.common.sounds.TSounds;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis01;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis02;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis03;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis04;
import net.tardis.mod.common.tileentity.consoles.TileEntityTardis05;
import net.tardis.mod.util.common.helpers.Helper;

public class ControlTelepathicCircuts extends EntityControl {
	
	public ControlTelepathicCircuts(TileEntityTardis tardis) {
		super(tardis);
		this.setSize(0.125F, 0.125F);
	}
	
	public ControlTelepathicCircuts(World world) {
		super(world);
		this.setSize(0.125F, 0.125F);
	}
	
	@Override
	public Vec3d getOffset(TileEntityTardis tardis) {
		if (tardis.getClass() == TileEntityTardis01.class || tardis.getClass() == TileEntityTardis02.class) {
			return Helper.convertToPixels(0, -2, -10);
		}
		if (tardis instanceof TileEntityTardis03)
			return Helper.convertToPixels(8.5, 2, 5.5);
		if (tardis instanceof TileEntityTardis04)
			return Helper.convertToPixels(0, -1, -8);
		if (tardis instanceof TileEntityTardis05)
			return Helper.convertToPixels(0, -2, 10.5);
		return Helper.convertToPixels(10, -2, -6);
	}
	
	@Override
	public void preformAction(EntityPlayer player) {
		if(!world.isRemote && player.isSneaking()) {
			TileEntity te = world.getTileEntity(this.getConsolePos());
			if(te instanceof TileEntityTardis) {
				TileEntityTardis tardis = ((TileEntityTardis)te);
				WorldServer ws = ((WorldServer)world).getMinecraftServer().getWorld(tardis.dimension);
				BlockPos pos = tardis.getLocation();
				ws.setBlockState(tardis.getLocation(), Blocks.AIR.getDefaultState());
				ws.setBlockState(tardis.getLocation().up(), Blocks.AIR.getDefaultState());
				EntityTardis tardisEntity = new EntityTardis(ws);
				tardisEntity.setConsole(tardis.getPos());
				tardisEntity.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
				tardisEntity.setExteior(EnumExterior.getExteriorFromBlock(tardis.getTopBlock().getBlock()));
				ws.spawnEntity(tardisEntity);
				tardis.setTardisEntity(tardisEntity);
				((WorldServer)world).addScheduledTask(new Runnable() {
					@Override
					public void run() {
						player.setSneaking(false);
						tardis.transferPlayer(player, false);
					}
				});
			}
		}
		else if (world.isRemote && !player.isSneaking()) {
			openGui();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTelepathicCircuts(this.getConsolePos()));
	}
	
	@Override
	public void init(TileEntityTardis tardis) {
		if (tardis != null) {
			if (tardis instanceof TileEntityTardis03)
				setSize(Helper.precentToPixels(8F), Helper.precentToPixels(4F));
			if (tardis.getClass() == TileEntityTardis.class)
				this.setSize(Helper.precentToPixels(4F), Helper.precentToPixels(2F));
			if (tardis instanceof TileEntityTardis04)
				this.setSize(Helper.precentToPixels(6F), Helper.precentToPixels(2F));
			if (tardis instanceof TileEntityTardis05)
				this.setSize(Helper.precentToPixels(3F), Helper.precentToPixels(3F));
		}
	}
	
	@Override
	public SoundEvent getUseSound() {
		return TSounds.control_telepathic_circuit;
	}
	
}

package net.tardis.mod.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.guis.GuiProtocol;
import net.tardis.mod.helpers.Helper;
import net.tardis.mod.tileentity.TileEntityTardis;

public class ControlScreen extends EntityControl{
	
	public static final DataParameter<BlockPos> LOCATION=EntityDataManager.createKey(ControlScreen.class, DataSerializers.BLOCK_POS);
	
	public ControlScreen(TileEntityTardis tardis) {
		super(tardis);
		this.dataManager.set(LOCATION, tardis.getPos());
	}
	
	public ControlScreen(World world) {
		super(world);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(LOCATION, BlockPos.ORIGIN);
	}
	
	@Override
	public Vec3d getOffset() {
		return Helper.convertToPixels(-1,28,8);
	}

	@Override
	public Vec3d getRotation() {
		return null;
	}

	@Override
	public void preformAction(EntityPlayer player) {
		if(world.isRemote) {
			openGui();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiProtocol(this.dataManager.get(LOCATION)));
	}
	
	public BlockPos getConsolePos() {
		return this.getDataManager().get(LOCATION);
	}
}

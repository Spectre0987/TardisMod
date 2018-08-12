package net.tardis.mod.common.blocks;

import java.util.function.Supplier;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.guis.GuiProtocol;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class BlockMonitor extends BlockFacingDecoration {

	public BlockMonitor(Supplier<TileEntity> tileEntity) {
		super(tileEntity);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) {
			TileEntityTardis tardis = null;
			for(TileEntity te : worldIn.getChunkFromBlockCoords(pos).getTileEntityMap().values()) {
				if(te != null && te instanceof TileEntityTardis) {
					tardis = (TileEntityTardis)te;
					break;
				}
			}
			if(tardis != null) {
				this.openGui(tardis);
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui(TileEntityTardis tardis) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiProtocol(tardis.getPos()));
	}

}

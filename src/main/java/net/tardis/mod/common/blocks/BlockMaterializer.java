package net.tardis.mod.common.blocks;

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
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.client.guis.GuiItemMaterializer;
import net.tardis.mod.common.tileentity.TileEntityItemMaterializer;

public class BlockMaterializer extends BlockBase {

	public BlockMaterializer() {
		this.setCreativeTab(TardisTabs.BLOCKS);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityItemMaterializer();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) {
			openGui();
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	@SideOnly(Side.CLIENT)
	public void openGui() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiItemMaterializer());
	}

}

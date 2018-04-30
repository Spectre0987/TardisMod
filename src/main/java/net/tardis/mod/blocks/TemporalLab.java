package net.tardis.mod.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.guis.GuiTRecipe;
import net.tardis.mod.tileentity.TileEntityTemporalLab;

public class TemporalLab extends BlockContainer {

	public TemporalLab() {
		super(Material.IRON);
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTemporalLab();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			ItemStack stack=((TileEntityTemporalLab)worldIn.getTileEntity(pos)).result;
			if(!stack.isEmpty()) {
				EntityItem ei=new EntityItem(worldIn,pos.getX()+0.5,pos.getY()+1,pos.getZ()+0.5,stack);
				worldIn.spawnEntity(ei);
				((TileEntityTemporalLab)worldIn.getTileEntity(pos)).result=ItemStack.EMPTY;
			}
		}
		if(worldIn.isRemote) {
			renderGui(pos);
			return false;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void renderGui(BlockPos pos) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiTRecipe(pos));
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
}

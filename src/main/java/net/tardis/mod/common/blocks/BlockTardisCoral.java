package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.client.creativetabs.TardisTabs;
import net.tardis.mod.common.tileentity.TileEntityTardisCoral;

public class BlockTardisCoral extends BlockTileBase {
	
	public ItemBlock item = new ItemBlock(this);

	public BlockTardisCoral() {
		super(Material.SPONGE, TileEntityTardisCoral::new);
        setCreativeTab(TardisTabs.MAIN);
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,ItemStack stack) {
		if(!worldIn.isRemote && placer instanceof EntityPlayer) {
			TileEntityTardisCoral coral = (TileEntityTardisCoral)worldIn.getTileEntity(pos);
			coral.setOwner(((EntityPlayer)placer).getGameProfile().getId());
		}
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
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

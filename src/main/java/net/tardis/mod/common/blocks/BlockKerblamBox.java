package net.tardis.mod.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.common.tileentity.TileEntityKerblam;

public class BlockKerblamBox extends BlockTileBase {

	public BlockKerblamBox() {
		super(Material.SPONGE, TileEntityKerblam::new);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote) return false;
		TileEntityKerblam kerblam = (TileEntityKerblam) worldIn.getTileEntity(pos);
		if(!kerblam.hasBeenOpened()){
			EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), kerblam.getStack());
			worldIn.spawnEntity(item);
			kerblam.setHasBeenOpened(true);
			kerblam.setStack(ItemStack.EMPTY);
			worldIn.playSound(null, pos, SoundEvents.ENTITY_FIREWORK_LARGE_BLAST, SoundCategory.BLOCKS, 1, 1);
		} else {
			if(kerblam.getStack().isEmpty()) {
				kerblam.setStack(player.getHeldItemMainhand());
				kerblam.setHasBeenOpened(false);
			}
			
		}
		System.out.println(kerblam.getStack() + " " + kerblam.hasBeenOpened());
		
		return super.onBlockActivated(worldIn, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
}

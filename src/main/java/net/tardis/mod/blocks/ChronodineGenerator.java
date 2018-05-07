package net.tardis.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.entities.EntityAngel;

public class ChronodineGenerator extends Block {
	
	public static final AxisAlignedBB BB = new AxisAlignedBB(0, 0, 0, 1, 0.1, 1);
	
	public ChronodineGenerator() {
		super(Material.IRON);
		this.setCreativeTab(Tardis.tab);
		this.setHardness(0.3F);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightOpacity(0);
	}
	
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (entityIn instanceof EntityAngel) {
			entityIn.setDead();
			worldIn.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.HOSTILE, 1F, 1F);
			worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 0);
			worldIn.setBlockToAir(pos);
		}
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BB;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		drops.add(new ItemStack(this));
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

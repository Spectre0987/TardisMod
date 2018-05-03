package net.tardis.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.tardis.api.controls.IUnbreakable;
import net.tardis.mod.Tardis;

public class TardisBlock extends Block implements IUnbreakable{
	
	public TardisBlock() {
		super(Material.WOOD,MapColor.BLUE);
		this.setLightLevel(1F);
		this.setBlockUnbreakable();
		this.setResistance(999);
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
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

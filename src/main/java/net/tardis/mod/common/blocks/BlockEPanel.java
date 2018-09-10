package net.tardis.mod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.common.blocks.interfaces.INeedItem;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.tileentity.TileEntityEPanel;
import net.tardis.mod.util.helpers.TardisHelper;

public class BlockEPanel extends BlockTileBase implements INeedItem{
	
	public ItemBlock item = new ItemEPanel(this);
	
	public BlockEPanel() {
        super(Material.IRON, TileEntityEPanel::new);
		this.setHardness(1F);
		this.setCreativeTab(Tardis.tab);
		item.setCreativeTab(Tardis.tab);
	}

    @Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public ItemBlock getItem() {
		return item;
	}
	
	public static class ItemEPanel extends ItemBlock{

		public ItemEPanel(Block block) {
			super(block);
			this.setCreativeTab(Tardis.tab);
		}

		@Override
		public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,float hitX, float hitY, float hitZ, IBlockState newState) {
			boolean placed = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
			if(placed && player.dimension == TDimensions.TARDIS_ID) {
				if(TardisHelper.hasTardis(player.getGameProfile().getId())) {
					if(TardisHelper.getTardis(player.getGameProfile().getId()).distanceSq(pos) < Math.pow(8 * 16, 2)) {
						TileEntityEPanel panel = (TileEntityEPanel) world.getTileEntity(pos);
						panel.setOwner(player.getGameProfile().getId());
					}
				}
			}
			return placed;
		}
		
	}
}

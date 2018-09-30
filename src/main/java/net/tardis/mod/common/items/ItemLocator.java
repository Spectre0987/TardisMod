package net.tardis.mod.common.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.common.helpers.Helper;

public class ItemLocator extends ItemBase{

	public ItemLocator() {
		this.setMaxStackSize(1);
		this.addPropertyOverride(new ResourceLocation(Tardis.MODID, "direction"), new IItemPropertyGetter() {
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entity) {
				if(entity == null || worldIn == null) return -1;
				BlockPos pos = getExteriorLocation(stack);
				Vec3d direction = entity.getPositionVector().subtract(new Vec3d(pos.getX(), pos.getY(), pos.getZ())).normalize();
				return direction.x > 0 ? 0 : (direction.x < 0 ? 1 : (direction.z > 0 ? 2 : (direction.z < 0 ? 3 : 4)));
			}});
	}
	
	public static BlockPos getExteriorLocation(ItemStack stack) {
		return Helper.getStackTag(stack).hasKey("loc") ? BlockPos.fromLong(stack.getTagCompound().getLong("loc")) : BlockPos.ORIGIN;
	}
	
	public static void setExteriorLocation(ItemStack stack, BlockPos pos) {
		Helper.getStackTag(stack).setLong("loc", pos.toLong());
	}
	
	public static BlockPos getCoords(ItemStack stack) {
		return Helper.getStackTag(stack).hasKey("coords") ? BlockPos.fromLong(stack.getTagCompound().getLong("coords")) : BlockPos.ORIGIN;
	}
	
	public static void setCoords(ItemStack stack, BlockPos pos) {
		Helper.getStackTag(stack).setLong("coords", pos.toLong());
	}
}

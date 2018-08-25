package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InteractionFindTardis implements IScrew {

    @Override
    public void performAction(World world, EntityPlayer player, EnumHand hand) {

    }

    @Override
    public void blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {

    }

    @Override
    public void entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getCoolDownAmount() {
        return 0;
    }

    @Override
    public boolean causesCoolDown() {
        return false;
    }

}

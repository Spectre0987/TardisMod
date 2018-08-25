package net.tardis.mod.common.screwdriver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InteractionEntity implements IScrew {

    @Override
    public EnumActionResult performAction(World world, EntityPlayer player, EnumHand hand) {
        return EnumActionResult.FAIL;
    }

    @Override
    public EnumActionResult blockInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return EnumActionResult.FAIL;
    }

    @Override
    public boolean entityInteraction(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        //Creeper
        if(target instanceof EntityCreeper){
            EntityCreeper creeper = (EntityCreeper) target;
            creeper.ignite();
            return true;
        }

        //Player
        if(target instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) target;

        }

        //Sheep
        if(target instanceof EntitySheep){
            EntitySheep sheep = (EntitySheep) target;
            if (!sheep.world.isRemote) {
                sheep.setSheared(true);
                int woolAmount = 1 + sheep.world.rand.nextInt(3);

                for (int j = 0; j < woolAmount; ++j)
                {
                   sheep.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), woolAmount, sheep.getFleeceColor().getMetadata()), 1.0F);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String getName() {
        return "screw.entity";
    }

    @Override
    public int getCoolDownAmount() {
        return 50;
    }

    @Override
    public boolean causesCoolDown() {
        return true;
    }

    @Override
    public int energyRequired() {
        return 5;
    }
}

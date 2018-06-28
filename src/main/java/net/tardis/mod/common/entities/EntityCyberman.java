package net.tardis.mod.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.tardis.mod.common.items.TItems;

public class EntityCyberman extends EntityMob{
	
	public EntityCyberman(World worldIn) {
		super(worldIn);
	}

	public static class DamageSourceCyber extends DamageSource{

		public DamageSourceCyber() {
			super("death.cyberman.generic");
		}

		@Override
		public ITextComponent getDeathMessage(EntityLivingBase entity) {
			return new TextComponentString(entity.getDisplayName().getFormattedText() + " " + (new TextComponentTranslation("death.cyberman.generic").getFormattedText()));
		}
		
	}

	@Override
	protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
		ItemStack[] drops = new ItemStack[] {new ItemStack(TItems.circuts, 1 + lootingModifier)};
		for(ItemStack s : drops) {
			EntityItem ei = new EntityItem(world, posX, posY, posZ, s);
			if(!world.isRemote) {
				world.spawnEntity(ei);
			}
		}
	}
}

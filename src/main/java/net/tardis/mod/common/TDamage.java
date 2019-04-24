package net.tardis.mod.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.tardis.mod.util.TSource;

public class TDamage {
	public static DamageSource FORCEFIELD = new TSource("forcefield", false);
	public static DamageSource DALEK = new TSource("dalek", false);
	public static DamageSource CYBERMAN = new TSource("cyberman", false);
	public static DamageSource LASER = new TSource("laser");
	public static DamageSource SUFFICATION = new DamageSource("damage.tardis.noair") {

		@Override
		public ITextComponent getDeathMessage(EntityLivingBase base) {
			return new TextComponentString(base.getName() + " " + new TextComponentTranslation("damage.tardis.noair")
					.getFormattedText());
		}
		
	};

}

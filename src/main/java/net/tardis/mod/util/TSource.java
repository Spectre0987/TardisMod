package net.tardis.mod.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TSource extends DamageSource {

	private String message;
	private boolean blockable;

	public TSource(String name, boolean blockable) {
		super(name);
		this.message = "damagesrc.tardis." + name;
		this.blockable = blockable;
	}

	public TSource(String name) {
		this(name, false);
	}

	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entity) {
		return new TextComponentString(entity.getName() + " " + new TextComponentTranslation(message).getFormattedText());
	}

	@Override
	public boolean isUnblockable() {
		return !blockable;
	}

}

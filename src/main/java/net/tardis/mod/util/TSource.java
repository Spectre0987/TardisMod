package net.tardis.mod.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TSource extends DamageSource {

    private String message;
    private boolean blockable;

    public TSource(String name) {
        this(name, false);
        this.message = "damagesrc.tardis." + name;
    }

    public TSource(String name, boolean blockable) {
        super(name);
        this.message = "damagesrc.tardis." + name;
        this.blockable = blockable;
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entity) {
        return new TextComponentTranslation(message, entity.getName());
    }

    @Override
    public boolean isUnblockable() {
        return !blockable;
    }

}

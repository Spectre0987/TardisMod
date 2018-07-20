package net.tardis.mod.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelFourthHat;
import net.tardis.mod.client.models.clothing.ModelThirteenCoat;

public enum EnumClothes {

    HAT_FOURTH_DOC(new ModelFourthHat(), "fourth_hat"),
    HAT_VOID_SPECS(null, "void_specs"),
    CHEST_13TH_COAT(new ModelThirteenCoat(), "thirteen_coat");

    private final ModelBiped model;
    private final ResourceLocation texture;

    EnumClothes(ModelBiped model, String texName) {
        this.model = model;
        this.texture = new ResourceLocation(Tardis.MODID, "textures/clothing/" + texName + ".png");
    }

    public ModelBiped getModel() {
        return model;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

}

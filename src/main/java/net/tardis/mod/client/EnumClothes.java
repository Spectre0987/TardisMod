package net.tardis.mod.client;

import java.util.HashMap;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.util.ResourceLocation;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.clothing.ModelFourthHat;
import net.tardis.mod.client.models.clothing.ModelThirteenCoat;

public enum EnumClothes {

    HAT_FOURTH_DOC("fourth_hat"),
    HAT_VOID_SPECS("void_specs"),
    CHEST_13TH_COAT("thirteen_coat");

    private final ModelBiped model;
    private final ResourceLocation texture;

    EnumClothes(String texName) {
        this.model = ClothingHandler.CLOTHING.get(this);
        this.texture = new ResourceLocation(Tardis.MODID, "textures/clothing/" + texName + ".png");
    }

    public ModelBiped getModel() {
        return ClothingHandler.CLOTHING.get(this);
    }

    public ResourceLocation getTexture() {
        return texture;
    }


    public static class ClothingHandler {
        public static HashMap<EnumClothes, ModelBiped> CLOTHING = new HashMap<>();

        public static void init() {
            CLOTHING.put(EnumClothes.CHEST_13TH_COAT, new ModelThirteenCoat());
            CLOTHING.put(EnumClothes.HAT_FOURTH_DOC, new ModelFourthHat());
            CLOTHING.put(EnumClothes.HAT_VOID_SPECS, null);
        }
    }

}

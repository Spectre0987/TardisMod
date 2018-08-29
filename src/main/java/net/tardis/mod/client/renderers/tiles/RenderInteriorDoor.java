package net.tardis.mod.client.renderers.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.tardis.mod.client.models.decoration.ModelHellbentDoor;
import net.tardis.mod.common.tileentity.TileEntityInteriorDoor;

public class RenderInteriorDoor extends TileEntitySpecialRenderer<TileEntityInteriorDoor> {


    ModelBase interiorHellbentModel = new ModelHellbentDoor();
    ModelBase main = new ModelHellbentDoor();

    @Override
    public void render(TileEntityInteriorDoor te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha);

        IBlockState block = getWorld().getBlockState(te.getPos());
        System.out.println("The Door is: " + te.getOpen());
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 1.5, z + 0.5);
        main.render(null, 0, 0, 0, 0, 0, 0.0625F);
        GlStateManager.popMatrix();

    }
}

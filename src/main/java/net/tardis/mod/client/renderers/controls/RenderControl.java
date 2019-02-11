package net.tardis.mod.client.renderers.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.console.contols.ModelAllControls;
import net.tardis.mod.common.entities.controls.EntityControl;
import net.tardis.mod.common.items.TItems;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import org.lwjgl.opengl.GL11;

public abstract class RenderControl extends Render<EntityControl> {

	public static final ResourceLocation CONTROL_TEXTURE = new ResourceLocation(Tardis.MODID, "textures/controls/control_sheet.png");
	public ModelAllControls control_model = new ModelAllControls();
	protected Minecraft mc;

	public RenderControl() {
		super(Minecraft.getMinecraft().getRenderManager());
		mc = Minecraft.getMinecraft();
	}


	public abstract void renderControl(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks, TileEntityTardis tardis);

	@Override
	protected ResourceLocation getEntityTexture(EntityControl entity) {
		return CONTROL_TEXTURE;
	}

	@Override
	public void doRender(EntityControl entity, double x, double y, double z, float entityYaw, float partialTicks) {
		renderControl(entity, x, y, z, entityYaw, partialTicks, (TileEntityTardis) mc.world.getTileEntity(entity.getConsolePos()));
		if (mc.player.getHeldItemMainhand().getItem() == TItems.manual) {
			Entity look = mc.objectMouseOver.entityHit;
			if (look != null && look == entity) {
				float offset = MathHelper.cos(entity.ticksExisted * 0.1F) * -0.09F;
				GL11.glPushMatrix();
				GL11.glTranslatef(0, 0.4F, 0);
				GL11.glScalef(0.60F, 0.60F, 0.60F);
				this.renderLivingLabel(entity, entity.getControlName(), x, y + 0.4 + offset, z, 46);
				GL11.glPopMatrix();
			}
		}
	}


}

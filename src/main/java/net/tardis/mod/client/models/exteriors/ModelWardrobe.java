package net.tardis.mod.client.models.exteriors;

//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWardrobe extends ModelBase implements IExteriorModel{
	private final ModelRenderer balls;
	private final ModelRenderer shit;
	private final ModelRenderer r;
	private final ModelRenderer l;

	public ModelWardrobe() {
		textureWidth = 256;
		textureHeight = 256;

		balls = new ModelRenderer(this);
		balls.setRotationPoint(0.0F, 24.0F, 0.0F);
		balls.cubeList.add(new ModelBox(balls, 24, 192, -10.0F, -1.0F, -8.0F, 2, 1, 2, 0.0F, false));
		balls.cubeList.add(new ModelBox(balls, 16, 192, 8.0F, -1.0F, -8.0F, 2, 1, 2, 0.0F, false));
		balls.cubeList.add(new ModelBox(balls, 8, 192, -10.0F, -1.0F, 6.0F, 2, 1, 2, 0.0F, false));
		balls.cubeList.add(new ModelBox(balls, 0, 192, 8.0F, -1.0F, 6.0F, 2, 1, 2, 0.0F, false));

		shit = new ModelRenderer(this);
		shit.setRotationPoint(0.0F, 24.0F, 0.0F);
		shit.cubeList.add(new ModelBox(shit, 0, 0, -11.0F, -3.0F, -9.0F, 22, 2, 18, 0.0F, false));
		shit.cubeList.add(new ModelBox(shit, 0, 20, -11.0F, -41.0F, -9.0F, 2, 38, 18, 0.0F, false));
		shit.cubeList.add(new ModelBox(shit, 40, 20, 9.0F, -41.0F, -9.0F, 2, 38, 18, 0.0F, false));
		shit.cubeList.add(new ModelBox(shit, 80, 0, -11.0F, -43.0F, -9.0F, 22, 2, 18, 0.0F, false));
		shit.cubeList.add(new ModelBox(shit, 0, 76, -9.0F, -41.0F, 7.0F, 18, 38, 2, 0.0F, false));

		r = new ModelRenderer(this);
		r.setRotationPoint(9.0F, 2.0F, -5.0F);
		r.cubeList.add(new ModelBox(r, 8, 155, -8.0F, 17.0F, -3.0F, 8, 2, 3, 0.0F, false));
		r.cubeList.add(new ModelBox(r, 0, 116, -1.0F, -17.0F, -3.0F, 1, 34, 3, 0.0F, false));
		r.cubeList.add(new ModelBox(r, 0, 155, -8.0F, -17.0F, -3.0F, 1, 34, 3, 0.0F, false));
		r.cubeList.add(new ModelBox(r, 8, 160, -8.0F, -19.0F, -3.0F, 8, 2, 3, 0.0F, false));
		r.cubeList.add(new ModelBox(r, 54, 76, -7.0F, -17.0F, -2.0F, 6, 34, 1, 0.0F, false));

		l = new ModelRenderer(this);
		l.setRotationPoint(-9.0F, 2.0F, -5.0F);
		l.cubeList.add(new ModelBox(l, 8, 165, 0.0F, 17.0F, -3.0F, 8, 2, 3, 0.0F, false));
		l.cubeList.add(new ModelBox(l, 22, 116, 7.0F, -17.0F, -3.0F, 1, 34, 3, 0.0F, false));
		l.cubeList.add(new ModelBox(l, 14, 116, 0.0F, -17.0F, -3.0F, 1, 34, 3, 0.0F, false));
		l.cubeList.add(new ModelBox(l, 8, 170, 0.0F, -19.0F, -3.0F, 8, 2, 3, 0.0F, false));
		l.cubeList.add(new ModelBox(l, 40, 76, 1.0F, -17.0F, -2.0F, 6, 34, 1, 0.0F, false));
		l.cubeList.add(new ModelBox(l, 8, 116, 8.0F, -19.0F, -3.5F, 2, 38, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		balls.render(f5);
		shit.render(f5);
		r.render(f5);
		l.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void renderClosed(float scale) {
		r.rotateAngleY = (float)Math.toRadians(0);
		l.rotateAngleY = (float)Math.toRadians(0);
		this.render(null, 0, 0, 0, 0, 0, 0.0625F);
	}

	@Override
	public void renderOpen(float scale) {
		r.rotateAngleY = (float)Math.toRadians(90.0);
		l.rotateAngleY = (float)Math.toRadians(-90.0);
		this.render(null, 0, 0, 0, 0, 0, 0.0625F);
	}
}
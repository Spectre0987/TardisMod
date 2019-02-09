package net.tardis.mod.client.renderers;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Util that is only used on the client i.e. Rendering code
 *
 * @author Cadiboo
 */
@SideOnly(Side.CLIENT)
public final class CadibooClientUtil {

	/**
	 * Rotation algorithm Taken off Max_the_Technomancer from <a href= "https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/modification-development/2772267-tesr-getting-darker-and-lighter-as-it-rotates">here</a>
	 *
	 * @param face the {@link EnumFacing face} to rotate for
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void rotateForFace(final EnumFacing face) {
		GlStateManager.rotate(face == EnumFacing.DOWN ? 0 : face == EnumFacing.UP ? 180F : (face == EnumFacing.NORTH) || (face == EnumFacing.EAST) ? 90F : -90F, face.getAxis() == EnumFacing.Axis.Z ? 1 : 0, 0, face.getAxis() == EnumFacing.Axis.Z ? 0 : 1);
		GlStateManager.rotate(-90, 0, 0, 1);
	}

	/**
	 * All Light methods I can remember
	 *
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	private static void allLightMethods() {

		// GlStateManager.disableLighting();
		// GlStateManager.enableLighting();
		//
		// RenderHelper.disableStandardItemLighting();
		// RenderHelper.enableStandardItemLighting();
		//
		// RenderHelper.enableGUIStandardItemLighting();
		//
		// net.minecraftforge.client.model.pipeline.LightUtil.diffuseLight(EnumFacing side);
		// net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(BufferBuilder wr, BakedQuad quad, int auxColor);
		//
		// Tessellator.getInstance().getBuffer().lightmap(skyLight, blockLight);
		//
		// OpenGlHelper.setLightmapTextureCoords(int target, float p_77475_1_, float t)

	}

	/**
	 * Modifed by Cadiboo, based on Notch?'s lightning code. Cleaned it up, made it readable & used pushMatrix, popMatrix & translate instead of drawing it straight onto the screen
	 *
	 * @param number                      The seed for the randoms that are used
	 * @param NumberOfBranches            The number of main (full length) branches
	 * @param NumberOfPossibleSubBranches The number Sub branches for each main branch
	 * @param scale                       How large/small it will render
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void renderLightning(final int number, final int NumberOfBranches, final int NumberOfPossibleSubBranches, final double scale) {
		GlStateManager.depthMask(true);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();

		final double scale16 = scale / 16;

		final double[] translateXArray = new double[8];
		final double[] translateZArray = new double[8];
		double tempX = 0.0D;
		double tempZ = 0.0D;
		final Random random = new Random(number);

		for (int counter_ = 7; counter_ >= 0; --counter_) {
			translateXArray[counter_] = tempX;
			translateZArray[counter_] = tempZ;
			tempX += random.nextInt(11) - 5;
			tempZ += random.nextInt(11) - 5;
		}

		for (int shells = 0; shells < 4; ++shells) {
			final Random random1 = new Random(number);
			for (int branches = 0; branches < NumberOfBranches; branches++) {
				for (int possibleSubBranches = 0; possibleSubBranches < (NumberOfPossibleSubBranches + 1); ++possibleSubBranches) {
					int position = 7;
					int decendingHeight = 0;

					if (possibleSubBranches > 0) {
						position = Math.abs((7 - possibleSubBranches) % translateXArray.length);
					}

					if (possibleSubBranches > 0) {
						decendingHeight = position - 2;
					}

					double topTranslateX = translateXArray[position];
					double topTranslateZ = translateZArray[position];

					for (int yPos = position; yPos >= decendingHeight; --yPos) {
						final double bottomTranslateX = topTranslateX;
						final double bottomTranslateZ = topTranslateZ;

						if (possibleSubBranches == 0) { // Main branch
							topTranslateX += random1.nextInt(11) - 5;
							topTranslateZ += random1.nextInt(11) - 5;
						} else {// Sub branch
							topTranslateX += random1.nextInt(31) - 15;
							topTranslateZ += random1.nextInt(31) - 15;
						}

						bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
						double topWidth = 0.1D + (shells * 0.2D);

						if (yPos == 0) {
							topWidth *= (yPos * 0.1D) + 1.0D;
						}

						double bottomWidth = 0.1D + (shells * 0.2D);

						if (yPos == 0) {
							bottomWidth *= ((yPos - 1) * 0.1D) + 1.0D;
						}

						topWidth *= (scale / 16);
						bottomWidth *= (scale / 16);

						for (int side = 0; side < 5; ++side) {
							double topOffsetX = -topWidth;
							double topOffsetZ = -topWidth;

							if ((side == 1) || (side == 2)) {
								topOffsetX += topWidth * 2.0D;
							}

							if ((side == 2) || (side == 3)) {
								topOffsetZ += topWidth * 2.0D;
							}

							double bottomOffsetX = -bottomWidth;
							double bottomOffsetZ = -bottomWidth;

							if ((side == 1) || (side == 2)) {
								bottomOffsetX += bottomWidth * 2.0D;
							}

							if ((side == 2) || (side == 3)) {
								bottomOffsetZ += bottomWidth * 2.0D;
							}

							bufferbuilder.pos(bottomOffsetX + (topTranslateX * scale16), yPos * scale, bottomOffsetZ + (topTranslateZ * scale16)).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
							bufferbuilder.pos(topOffsetX + (bottomTranslateX * scale16), (yPos + 1) * scale, topOffsetZ + (bottomTranslateZ * scale16)).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
						}

						tessellator.draw();

					}
				}
			}

		}

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
	}

	/**
	 * Put a lot of effort into this, it gets the entities exact (really, really exact) position
	 *
	 * @param entity       The entity to calculate the position of
	 * @param partialTicks The multiplier used to predict where the entity is/will be
	 * @return The position of the entity as a Vec3d
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static Vec3d getEntityRenderPos(final Entity entity, final double partialTicks) {
		double flyingMultiplier = 1.825;
		double yFlying = 1.02;
		double yAdd = 0.0784000015258789;

		if ((entity instanceof EntityPlayer) && ((EntityPlayer) entity).capabilities.isFlying) {
			flyingMultiplier = 1.1;
			yFlying = 1.67;
			yAdd = 0;
		}

		final double yGround = ((entity.motionY + yAdd) == 0) && (entity.prevPosY > entity.posY) ? entity.posY - entity.prevPosY : 0;
		double xFall = 1;
		if (flyingMultiplier == 1.825) {
			if (entity.motionX != 0) {
				if ((entity.motionY + yAdd) != 0) {
					xFall = 0.6;
				} else if (yGround != 0) {
					xFall = 0.6;
				}
			} else {
				xFall = 0.6;
			}
		}

		double zFall = 1;
		if (flyingMultiplier == 1.825) {
			if (entity.motionZ != 0) {
				if ((entity.motionY + yAdd) != 0) {
					zFall = 0.6;
				} else if (yGround != 0) {
					zFall = 0.6;
				}
			} else {
				zFall = 0.6;
			}
		}

		final double dX = entity.posX - ((entity.prevPosX - entity.posX) * partialTicks) - ((entity.motionX * xFall) * flyingMultiplier);
		final double dY = entity.posY - yGround - ((entity.prevPosY - entity.posY) * partialTicks) - ((entity.motionY + yAdd) * yFlying);
		final double dZ = entity.posZ - ((entity.prevPosZ - entity.posZ) * partialTicks) - ((entity.motionZ * zFall) * flyingMultiplier);

		return new Vec3d(dX, dY, dZ);
	}

	/**
	 * Rotates the X axis directly towards a {@link Vec3d Vec3d} position
	 *
	 * @param source      The position to rotate from (I think that I've messed up the maths, it currently only works with 0, 0, 0 i.e. the Origin)
	 * @param destination The position to rotate towards
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void rotateXAxisTowardsPos(final Vec3d source, final Vec3d destination) {
		GlStateManager.rotate(90 + (float) getYaw(source, destination), 0, 1, 0);
		GlStateManager.rotate(270 - (float) getPitch(source, destination), 1, 0, 0);
	}

	/**
	 * Rotates the Y axis directly towards a {@link Vec3d Vec3d} position
	 *
	 * @param source      The position to rotate from (I think that I've messed up the maths, it currently only works with 0, 0, 0 i.e. the Origin)
	 * @param destination The position to rotate towards
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void rotateYAxisTowardsPos(final Vec3d source, final Vec3d destination) {
		GlStateManager.rotate((float) getYaw(source, destination), 0, 1, 0);
		GlStateManager.rotate((float) getPitch(source, destination), 0, 0, 1);
	}

	/**
	 * Rotates the Z axis directly towards a {@link Vec3d Vec3d} position
	 *
	 * @param source      The position to rotate from (I think that I've messed up the maths, it currently only works with 0, 0, 0 i.e. the Origin)
	 * @param destination The position to rotate towards
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void rotateZAxisTowardsPos(final Vec3d source, final Vec3d destination) {
		GlStateManager.rotate(180 + (float) getYaw(source, destination), 0, 1, 0);
		GlStateManager.rotate(90 - (float) getPitch(source, destination), 0, 0, 1);
	}

	/**
	 * Rotates around X axis based on Pitch input and around Y axis based on Yaw input
	 *
	 * @param pitch
	 * @param yaw
	 */
	@SideOnly(Side.CLIENT)
	public static void rotateForPitchYaw(final double pitch, final double yaw) {
		GlStateManager.rotate((float) yaw, 0, 1, 0);
		GlStateManager.rotate((float) pitch, 1, 0, 0);
	}

	/**
	 * Modified version of {@link CadibooClientUtil#renderLightning(int, int, int, double) renderLightning} that looks more electricity-arcy
	 *
	 * @param number           The seed for the randoms that are used
	 * @param NumberOfBranches The number of main (full length) branches
	 * @param scale            How large/small/long it will be
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void renderElectricity(final int number, final int NumberOfBranches, final double scale) {
		GlStateManager.depthMask(true);
		GlStateManager.disableTexture2D();
		GlStateManager.disableLighting();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();

		final double scale16 = scale / 16;

		final int NumberOfPossibleSubBranches = 3;

		final double[] translateXArray = new double[8];
		final double[] translateZArray = new double[8];
		double tempX = 0.0D;
		double tempZ = 0.0D;
		final Random random = new Random(number);

		for (int counter_ = 7; counter_ >= 0; --counter_) {
			translateXArray[counter_] = tempX;
			translateZArray[counter_] = tempZ;

			tempX += random.nextInt(10) * 0.1;
			tempZ += random.nextInt(10) * 0.1;
			tempX *= -3 * -counter_ * 0.11;
			tempZ *= -3 * -counter_ * 0.11;

			if (new Random(counter_ - 1).nextBoolean()) {
				tempX *= -1;
				tempZ *= -1;
			}
		}

		for (int shells = 0; shells < 4; ++shells) {
			final Random random1 = new Random(number);
			for (int branches = 0; branches < NumberOfBranches; branches++) {
				for (int possibleSubBranches = 0; possibleSubBranches < (NumberOfPossibleSubBranches + 1); ++possibleSubBranches) {
					int position = 7;
					int decendingHeight = 0;

					if (possibleSubBranches > 0) {
						position = Math.abs((7 - possibleSubBranches) % translateXArray.length);
					}

					if (possibleSubBranches > 0) {
						decendingHeight = position - 2;
					}

					double topTranslateX = translateXArray[position];
					double topTranslateZ = translateZArray[position];

					for (int yPos = position; yPos >= decendingHeight; --yPos) {
						final double bottomTranslateX = topTranslateX;
						final double bottomTranslateZ = topTranslateZ;

						// if (possibleSubBranches == 0) { // Main branch
						topTranslateX += random1.nextInt(10) * 0.1;
						topTranslateZ += random1.nextInt(10) * 0.1;
						topTranslateX *= -3 * yPos * 0.11;
						topTranslateZ *= -3 * yPos * 0.11;

						if (new Random(possibleSubBranches - 1).nextBoolean()) {
							topTranslateX *= -1;
							topTranslateZ *= -1;
						}

						if (possibleSubBranches != 0) {// Sub branch
							topTranslateX += random1.nextInt(9) - 3;
							topTranslateZ += random1.nextInt(9) - 3;
						}

						bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
						double topWidth = 0.1D + (shells * 0.2D);

						if (yPos == 0) {
							topWidth *= (yPos * 0.1D) + 1.0D;
						}

						double bottomWidth = 0.1D + (shells * 0.2D);

						if (yPos == 0) {
							bottomWidth *= ((yPos - 1) * 0.1D) + 1.0D;
						}

						topWidth *= (scale / 16);
						bottomWidth *= (scale / 16);

						for (int side = 0; side < 5; ++side) {
							double topOffsetX = -topWidth;
							double topOffsetZ = -topWidth;

							if ((side == 1) || (side == 2)) {
								topOffsetX += topWidth * 2.0D;
							}

							if ((side == 2) || (side == 3)) {
								topOffsetZ += topWidth * 2.0D;
							}

							double bottomOffsetX = -bottomWidth;
							double bottomOffsetZ = -bottomWidth;

							if ((side == 1) || (side == 2)) {
								bottomOffsetX += bottomWidth * 2.0D;
							}

							if ((side == 2) || (side == 3)) {
								bottomOffsetZ += bottomWidth * 2.0D;
							}

							bufferbuilder.pos(bottomOffsetX + (topTranslateX * scale16), yPos * scale, bottomOffsetZ + (topTranslateZ * scale16)).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
							bufferbuilder.pos(topOffsetX + (bottomTranslateX * scale16), (yPos + 1) * scale, topOffsetZ + (bottomTranslateZ * scale16)).color(0.45F, 0.45F, 0.5F, 0.3F).endVertex();
						}

						tessellator.draw();

					}
				}
			}

		}

		GlStateManager.disableBlend();
		GlStateManager.enableLighting();
		GlStateManager.enableTexture2D();
	}

	/**
	 * Draws a cuboid similar to the way blocks are drawn
	 *
	 * @param minU   Minimum texture U (x) to draw from (between 0 and 1)
	 * @param maxU   Maximum texture U (x) to draw from (between 0 and 1)
	 * @param minV   Minimum texture V (y) to draw from (between 0 and 1)
	 * @param maxV   Maximum texture V (y) to draw from (between 0 and 1)
	 * @param x_size The size of the cuboid on the X plane
	 * @param y_size The size of the cuboid on the Y plane
	 * @param z_size The size of the cuboid on the Z plane
	 * @param scale  How much to scale in {@link GlStateManager#scale(double, double, double) GLStateManager.scale}
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void drawCuboid(final float minU, final float maxU, final float minV, final float maxV, final double x_size, final double y_size, final double z_size, final double scale) {
		drawCuboidAt(0, 0, 0, minU, maxU, minV, maxV, x_size, y_size, z_size, scale);
	}

	public static void drawCuboidAt(final double x, final double y, final double z, final float minU, final float maxU, final float minV, final float maxV, final double x_size, final double y_size, final double z_size, final double scale) {

		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);

		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		// UP
		bufferbuilder.pos(-x_size + x, y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, -z_size + z).tex(minU, maxV).endVertex();

		// DOWN
		bufferbuilder.pos(-x_size + x, -y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, -y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, z_size + z).tex(maxU, minV).endVertex();

		// LEFT
		bufferbuilder.pos(x_size + x, -y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, z_size + z).tex(minU, minV).endVertex();

		// RIGHT
		bufferbuilder.pos(-x_size + x, -y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, -y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, -z_size + z).tex(maxU, maxV).endVertex();

		// BACK
		bufferbuilder.pos(-x_size + x, -y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, -z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, -z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, -z_size + z).tex(maxU, maxV).endVertex();

		// FRONT
		bufferbuilder.pos(x_size + x, -y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, -y_size + y, z_size + z).tex(minU, minV).endVertex();

		tessellator.draw();

		GlStateManager.popMatrix();
	}

	/**
	 * Draws a cuboid that has its texture always be "seamless"
	 *
	 * @param minU   Minimum texture U (x) to draw from (between 0 and 1)
	 * @param maxU   Maximum texture U (x) to draw from (between 0 and 1)
	 * @param minV   Minimum texture V (y) to draw from (between 0 and 1)
	 * @param maxV   Maximum texture V (y) to draw from (between 0 and 1)
	 * @param x_size The size of the cuboid on the X plane
	 * @param y_size The size of the cuboid on the Y plane
	 * @param z_size The size of the cuboid on the Z plane
	 * @param scale  How much to scale in {@link GlStateManager#scale(double, double, double) GLStateManager.scale}
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void drawSeamlessCuboid(final float minU, final float maxU, final float minV, final float maxV, final double z_size, final double y_size, final double x_size, final double scale) {
		drawSeamlessCuboidAt(0, 0, 0, minU, maxU, minV, maxV, x_size, y_size, z_size, scale);
	}

	public static void drawSeamlessCuboidAt(final double x, final double y, final double z, final float minU, final float maxU, final float minV, final float maxV, final double x_size, final double y_size, final double z_size, final double scale) {

		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);

		final double hlfU = minU + ((maxU - minU) / 2);
		final double hlfV = minV + ((maxV - minV) / 2);

		final double centre = 0d;

		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		// UP
		bufferbuilder.pos(-x_size + x, y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, -z_size + z).tex(minU, maxV).endVertex();

		// DOWN
		bufferbuilder.pos(-x_size + x, -y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, -y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, z_size + z).tex(maxU, minV).endVertex();

		// LEFT
		bufferbuilder.pos(x_size + x, -y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, z_size + z).tex(minU, minV).endVertex();

		// RIGHT
		bufferbuilder.pos(-x_size + x, -y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, -y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, -z_size + z).tex(maxU, maxV).endVertex();

		// BACK BOTTOM
		bufferbuilder.pos(-x_size + x, -y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(-x_size + x, centre, -z_size + z).tex(minU, hlfV).endVertex();
		bufferbuilder.pos(x_size + x, centre, -z_size + z).tex(maxU, hlfV).endVertex();
		bufferbuilder.pos(x_size + x, -y_size + y, -z_size + z).tex(maxU, maxV).endVertex();

		// BACK TOP
		bufferbuilder.pos(-x_size + x, centre, -z_size + z).tex(maxU, hlfV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, -z_size + z).tex(maxU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, -z_size + z).tex(minU, maxV).endVertex();
		bufferbuilder.pos(x_size + x, centre, -z_size + z).tex(minU, hlfV).endVertex();

		// FRONT BOTTOM
		bufferbuilder.pos(x_size + x, -y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size + x, centre, z_size + z).tex(maxU, hlfV).endVertex();
		bufferbuilder.pos(-x_size + x, centre, z_size + z).tex(minU, hlfV).endVertex();
		bufferbuilder.pos(-x_size + x, -y_size + y, z_size + z).tex(minU, minV).endVertex();

		// FRONT TOP
		bufferbuilder.pos(x_size + x, centre, z_size + z).tex(minU, hlfV).endVertex();
		bufferbuilder.pos(x_size + x, y_size + y, z_size + z).tex(minU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, y_size + y, z_size + z).tex(maxU, minV).endVertex();
		bufferbuilder.pos(-x_size + x, centre, z_size + z).tex(maxU, hlfV).endVertex();

		tessellator.draw();

		GlStateManager.popMatrix();
	}

	/**
	 * Draws a part of a texture
	 *
	 * @param minU   Minimum texture U (x) to draw from (between 0 and 1)
	 * @param maxU   Maximum texture U (x) to draw from (between 0 and 1)
	 * @param minV   Minimum texture V (y) to draw from (between 0 and 1)
	 * @param maxV   Maximum texture V (y) to draw from (between 0 and 1)
	 * @param x_size The size of the cuboid on the X plane
	 * @param y_size The size of the cuboid on the Y plane
	 * @param scale  How much to scale in {@link GlStateManager#scale(double, double, double) GLStateManager.scale}
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void drawQuad(final float minU, final float maxU, final float minV, final float maxV, final double x_size, final double y_size, final double scale) {
		GlStateManager.scale(scale, scale, scale);

		final double hlfU = minU + ((maxU - minU) / 2);
		final double hlfV = minV + ((maxV - minV) / 2);

		final double centre = 0d;

		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);

		// BACK
		bufferbuilder.pos(-x_size, -y_size, -1).tex(minU, maxV).endVertex();
		bufferbuilder.pos(-x_size, y_size, -1).tex(minU, minV).endVertex();
		bufferbuilder.pos(x_size, y_size, -1).tex(maxU, minV).endVertex();
		bufferbuilder.pos(x_size, -y_size, -1).tex(maxU, maxV).endVertex();

		tessellator.draw();

		GlStateManager.scale(1 / scale, 1 / scale, 1 / scale);
	}

	/**
	 * Renders an {@link ItemStack} with the ground camera transform
	 *
	 * @param stack stack the stack to render
	 * @param world the world passed to {@link net.minecraft.client.renderer.RenderItem#getItemModelWithOverrides(ItemStack, World, net.minecraft.entity.EntityLivingBase), RenderItem.getItemModelWithOverrides(ItemStack, World, EntityLivingBase)}
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void renderStack(final ItemStack stack, final World world) {
		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, null);
		model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.GROUND, false);

		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
	}

	/**
	 * Renders an {@link ItemStack} without any camera transforms
	 *
	 * @param stack the stack to render
	 * @param world the world passed to {@link net.minecraft.client.renderer.RenderItem#getItemModelWithOverrides(ItemStack, World, net.minecraft.entity.EntityLivingBase), RenderItem.getItemModelWithOverrides(ItemStack, World, EntityLivingBase)}
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static void renderStackWithoutTransforms(final ItemStack stack, final World world) {
		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, null);
		model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.NONE, false);

		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(stack, model);
	}

	/**
	 * Renders an {@link ItemStack} with the specified {@link IBakedModel} with the specified color
	 *
	 * @param stack the stack to render
	 * @param color the color to render the stack with
	 * @author Cadiboo
	 * @see {@link CadibooClientUtil#color(int, int, int)}
	 */
	@SideOnly(Side.CLIENT)
	public static void renderStackWithColor(final ItemStack stack, final World world, final int color) {
		final IBakedModel model = getModelFromStack(stack, world);
		renderModelWithColor(model, color);

	}

	/**
	 * Renders a baked model
	 *
	 * @param model the model to render
	 */
	@SideOnly(Side.CLIENT)
	public static void renderModel(final IBakedModel model) {
		renderModelWithColor(model, -1);
	}

	/**
	 * Renders a baked model with the specified color
	 *
	 * @param model the model to render
	 * @param color the color to render the model with
	 * @see {@link CadibooClientUtil#color(int, int, int)}
	 */
	@SideOnly(Side.CLIENT)
	public static void renderModelWithColor(final IBakedModel model, final int color) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);

		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.ITEM);

		for (final EnumFacing enumfacing : EnumFacing.values()) {
			renderQuadsColor(bufferbuilder, model.getQuads((IBlockState) null, enumfacing, 0L), color);
		}

		renderQuadsColor(bufferbuilder, model.getQuads((IBlockState) null, (EnumFacing) null, 0L), color);
		tessellator.draw();

		GlStateManager.popMatrix();
	}

	/**
	 * Renders a list of quads with the specified color
	 *
	 * @param bufferbuilder the bufferbuilder to render the quads with
	 * @param quads         a list with all the quads in it
	 * @param color         the color to render the quads with
	 * @see {@link CadibooClientUtil#color(int, int, int)}
	 */
	@SideOnly(Side.CLIENT)
	private static void renderQuadsColor(final BufferBuilder bufferbuilder, final List<BakedQuad> quads, int color) {

		int i = 0;
		for (final int j = quads.size(); i < j; ++i) {
			final BakedQuad bakedquad = quads.get(i);

			if ((color == -1) && bakedquad.hasTintIndex()) {
				if (EntityRenderer.anaglyphEnable) {
					color = TextureUtil.anaglyphColor(color);
				}

				color = color | -0x1000000;
			}

			net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(bufferbuilder, bakedquad, color);
		}
	}

	/**
	 * gets the model from a stack with overrides
	 *
	 * @param stack
	 * @param world
	 * @return The stack's model
	 * @author Cadiboo
	 */
	@SideOnly(Side.CLIENT)
	public static IBakedModel getModelFromStack(final ItemStack stack, final World world) {
		IBakedModel model = Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, world, null);
		model = ForgeHooksClient.handleCameraTransforms(model, ItemCameraTransforms.TransformType.NONE, false);
		return model;
	}

	/**
	 * Sets lightmap texture coords to brightest possbile
	 */
	@SideOnly(Side.CLIENT)
	public static void enableMaxLighting() {
		GlStateManager.disableLighting();
		final int lightmapCoords = 15728881;

		final int skyLight = lightmapCoords % 65536;
		final int blockLight = lightmapCoords / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, skyLight, blockLight);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * draws a texture that doesn't have to be 256x256, used in GUIs
	 *
	 * @param x
	 * @param y
	 * @param u
	 * @param v
	 * @param width
	 * @param height
	 * @param textureWidth
	 * @param textureHeight
	 */
	@SideOnly(Side.CLIENT)
	public static void drawNonStandardTexturedRect(final int x, final int y, final int u, final int v, final int width, final int height, final int textureWidth, final int textureHeight) {
		final double f = 1F / (double) textureWidth;
		final double f1 = 1F / (double) textureHeight;
		final Tessellator tessellator = Tessellator.getInstance();
		final BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + height, 0).tex(u * f, (v + height) * f1).endVertex();
		bufferbuilder.pos(x + width, y + height, 0).tex((u + width) * f, (v + height) * f1).endVertex();
		bufferbuilder.pos(x + width, y, 0).tex((u + width) * f, v * f1).endVertex();
		bufferbuilder.pos(x, y, 0).tex(u * f, v * f1).endVertex();
		tessellator.draw();
	}

	/**
	 * Gets the pitch rotation between two vectors
	 *
	 * @param source
	 * @param destination
	 * @return the pitch rotation
	 */
	@SideOnly(Side.CLIENT)
	public static double getPitch(final Vec3d source, final Vec3d destination) {
		double pitch = Math.atan2(destination.y, Math.sqrt((destination.x * destination.x) + (destination.z * destination.z)));
		pitch = pitch * (180 / Math.PI);
		pitch = pitch < 0 ? 360 - (-pitch) : pitch;
		return 90 - pitch;
	}

	/**
	 * Gets the yaw rotation between two vectors
	 *
	 * @param source
	 * @param destination
	 * @return the yaw rotation
	 */
	public static double getYaw(final Vec3d source, final Vec3d destination) {
		double yaw = Math.atan2(destination.x - source.x, destination.z - source.z);
		yaw = yaw * (180 / Math.PI);
		yaw = yaw < 0 ? 360 - (-yaw) : yaw;
		return yaw + 90;
	}

	/**
	 * Used in Guis to determine whether or not to render a tooltip
	 *
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param mouseX
	 * @param mouseY
	 * @return if the mouse is inside the rectangle
	 */
	public static boolean isInRectangle(final int x, final int y, final int xSize, final int ySize, final int mouseX, final int mouseY) {
		return (mouseX >= x) && (mouseX <= (x + xSize)) && (mouseY >= y) && (mouseY <= (y + ySize));
	}

	/**
	 * @param red   the red value of the color, between 0x00 (decimal 0) and 0xFF (decimal 255)
	 * @param green the red value of the color, between 0x00 (decimal 0) and 0xFF (decimal 255)
	 * @param blue  the red value of the color, between 0x00 (decimal 0) and 0xFF (decimal 255)
	 * @return the color in ARGB format
	 * @author Cadiboo
	 */
	public static int color(int red, int green, int blue) {

		red = MathHelper.clamp(red, 0x00, 0xFF);
		green = MathHelper.clamp(green, 0x00, 0xFF);
		blue = MathHelper.clamp(blue, 0x00, 0xFF);

		final int alpha = 0xFF;

		// 0x alpha red green blue
		// 0xaarrggbb

		int colorRGBA = 0;
		colorRGBA |= red << 16;
		colorRGBA |= green << 8;
		colorRGBA |= blue << 0;
		colorRGBA |= alpha << 24;

		return colorRGBA;
	}

	public static int colorf(final float red, final float green, final float blue) {
		final int redInt = Math.max(0, Math.min(255, Math.round(red * 255)));
		final int greenInt = Math.max(0, Math.min(255, Math.round(green * 255)));
		final int blueInt = Math.max(0, Math.min(255, Math.round(blue * 255)));
		return color(redInt, greenInt, blueInt);
	}

	// TODO
//	float f2 = (float)(i >> 16 & 255) / 255.0F;
//	float f3 = (float)(i >> 8 & 255) / 255.0F;
//	float f4 = (float)(i & 255) / 255.0F;
//	GL11.glColor3f(f2, f3, f4);

	// TODO

	// int r = (hex & 0xFF0000) >> 16;
	// int g = (hex & 0xFF00) >> 8;
	// int b = (hex & 0xFF);

}

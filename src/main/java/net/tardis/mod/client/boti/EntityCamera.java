package net.tardis.mod.client.boti;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;
import java.util.Objects;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tardis.mod.util.Vec2d;

public class EntityCamera extends EntityPlayerSP {
	
	public BufferedImage image;
	private DynamicTexture texture;
	public Vec3d origin;
	
	public EntityCamera(World world) {
		super(Minecraft.getMinecraft(), world, Objects.requireNonNull(Minecraft.getMinecraft().getConnection()), null, null);
	}
	
	public EntityCamera(World world, double x, double y, double z) {
		super(Minecraft.getMinecraft(), world, Objects.requireNonNull(Minecraft.getMinecraft().getConnection()), null, null);
		this.setPositionAndUpdate(x, y, z);
		this.origin = new Vec3d(x, y, z);
	}
	
	public void renderWorldToTexture(ICameraInterface tileEntity, Vec2d resolution, Vec3d tilePos, float partialRenderTicks) {
		if (!world.isRemote) return;
		
		Framebuffer mcBuffer = Minecraft.getMinecraft().getFramebuffer();
		
		tileEntity.setupOffset(this, tilePos);
		
		Minecraft.getMinecraft().setRenderViewEntity(this);
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		Minecraft.getMinecraft().player = this;
		
		Framebuffer framebuffer = new Framebuffer((int) resolution.x, (int) resolution.y, true);
		
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.skipRenderWorld) return;
		EntityRenderer entityRenderer = mc.entityRenderer;
		
		// Backup current render settings
		int heightBackup = mc.displayHeight;
		int widthBackup = mc.displayWidth;
		
		int thirdPersonBackup = mc.gameSettings.thirdPersonView;
		boolean hideGuiBackup = mc.gameSettings.hideGUI;
		int particleBackup = mc.gameSettings.particleSetting;
		boolean anaglyphBackup = mc.gameSettings.anaglyph;
		int renderDistanceBackup = mc.gameSettings.renderDistanceChunks;
		float FOVbackup = mc.gameSettings.fovSetting;
		
		// Render world
		try {
			// Set all of the render setting to work on the proxy world
			mc.displayHeight = framebuffer.framebufferHeight;
			mc.displayWidth = framebuffer.framebufferWidth;
			
			mc.gameSettings.thirdPersonView = 0;
			mc.gameSettings.hideGUI = true;
			mc.gameSettings.anaglyph = false;
			
			// Set gl options
			framebuffer.bindFramebuffer(true);
			
			int i1 = mc.gameSettings.limitFramerate;
			if (mc.isFramerateLimitBelowMax()) {
				entityRenderer.renderWorld(partialRenderTicks, (1000000000 / i1));
			} else {
				entityRenderer.renderWorld(partialRenderTicks, 0L);
			}
			
			// toBufferedImage
			int i = mc.displayHeight * mc.displayWidth;
			
			IntBuffer pixelBuffer = BufferUtils.createIntBuffer(i);
			int[] pixelValues = new int[i];
			
			GlStateManager.glPixelStorei(3333, 1);
			GlStateManager.glPixelStorei(3317, 1);
			pixelBuffer.clear();
			
			GlStateManager.glReadPixels(0, 0, mc.displayWidth, mc.displayHeight, 32993, 33639, pixelBuffer);
			
			pixelBuffer.get(pixelValues);
			TextureUtil.processPixelValues(pixelValues, mc.displayWidth, mc.displayHeight);
			BufferedImage bufferedimage = new BufferedImage(mc.displayWidth, mc.displayHeight, 1);
			bufferedimage.setRGB(0, 0, mc.displayWidth, mc.displayWidth, pixelValues, 0, mc.displayHeight);
			
			this.image = bufferedimage;
		} catch (Exception e) {
			try {
				// Clean up the tessellator, just in case.
				Tessellator.getInstance().draw();
			} catch (Exception e2) {
				// It might throw an exception, but that just means we didn't need to clean it up (this time)
			}
			throw new RuntimeException("Error while rendering proxy world", e);
		} finally {
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			framebuffer.bindFramebufferTexture();
			GL11.glViewport(0, 0, widthBackup, heightBackup);
			GL11.glLoadIdentity();
			
			mc.player = player;
			mc.gameSettings.thirdPersonView = thirdPersonBackup;
			mc.gameSettings.hideGUI = hideGuiBackup;
			mc.gameSettings.particleSetting = particleBackup;
			mc.gameSettings.anaglyph = anaglyphBackup;
			mc.gameSettings.renderDistanceChunks = renderDistanceBackup;
			mc.gameSettings.fovSetting = FOVbackup;
			
			mc.displayHeight = heightBackup;
			mc.displayWidth = widthBackup;
		}
		
		framebuffer.deleteFramebuffer();
		mcBuffer.bindFramebuffer(true);
		Minecraft.getMinecraft().setRenderViewEntity(Minecraft.getMinecraft().player);
	}
	
	public void bindTexture() {
		texture = new DynamicTexture(image);
		GlStateManager.bindTexture(texture.getGlTextureId());
	}
	
	public void deleteTexture(Vec3d renderPos) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (renderPos.distanceTo(new Vec3d(player.posX, player.posY, player.posZ)) < 20) { // TODO configure range
			image = null;
			GlStateManager.deleteTexture(texture.getGlTextureId());
			texture = null;
		}
	}
	
	@Override
	public void onEntityUpdate() {}
	
	@Override
	public void onLivingUpdate() {}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
		return 0;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
	
	@Override
	public void setAIMoveSpeed(float par1) {}
	
	@Override
	protected void dropEquipment(boolean par1, int par2) {}
	
	@Override
	protected void onDeathUpdate() {
		setDead();
	}
	
	@Override
	public void setRevengeTarget(EntityLivingBase par1) {}
	
	@Override
	protected void updatePotionEffects() {}
	
	@Override
	public void clearActivePotions() {}
	
	@Override
	public boolean isPotionActive(Potion par1) {
		return false;
	}
	
	@Override
	public PotionEffect getActivePotionEffect(Potion par1) {
		return null;
	}
	
	@Override
	public void addPotionEffect(PotionEffect par1) {}
	
	@Override
	public boolean isPotionApplicable(PotionEffect par1) {
		return false;
	}
	
	@Override
	public boolean isEntityUndead() {
		return false;
	}
	
	@Override
	protected void onNewPotionEffect(PotionEffect par1) {}
	
	@Override
	protected void onChangedPotionEffect(PotionEffect par1, boolean par2) {}
	
	@Override
	protected void onFinishedPotionEffect(PotionEffect par1) {}
	
	@Override
	public void heal(float par1) {}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1, float par2) {
		return false;
	}
	
	@Override
	public void renderBrokenItemStack(ItemStack par1) {}
	
	@Override
	public void onDeath(DamageSource par1) {
		world.setEntityState(this, (byte) 3);
	}
	
	@Override
	public void knockBack(Entity par1Entity, float par2, double par3, double par5) {}
	
	@Override
	public boolean isOnLadder() {
		return false;
	}
	
	@Override
	public int getTotalArmorValue() {
		return 0;
	}
	
	@Override
	protected float applyArmorCalculations(DamageSource par1DamageSource, float par2) {
		return par2;
	}
	
	@Override
	protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2) {
		return par2;
	}
	
	@Override
	protected void damageEntity(DamageSource par1, float par2) {}
	
	@Override
	protected void updateArmSwingProgress() {}
	
	@Override
	public void setSprinting(boolean par1) {}
	
	@Override
	protected float getSoundVolume() {
		return 0F;
	}
	
	@Override
	public void dismountEntity(Entity par1Entity) {}
	
	@Override
	public void updateRidden() {}
	
	@Override
	public void setJumping(boolean par1) {}
	
	@Override
	public void onItemPickup(Entity par1Entity, int par2) {}
	
	@Override
	public boolean canEntityBeSeen(Entity par1Entity) {
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	@Override
	public boolean handleWaterMovement() {
		return false;
	}
	
	@Override
	public boolean isInsideOfMaterial(Material par1Material) {
		return false;
	}
	
	@Override
	public void move(MoverType type, double par1, double par2, double par3) {}
	
	@Override
	public float getBrightness() {
		return 0;
	}
	
	@Override
	public void applyEntityCollision(Entity par1Entity) {}
	
	@Override
	public boolean isBurning() {
		return false;
	}
	
	@Override
	public boolean isRiding() {
		return false;
	}
	
	@Override
	public boolean isSneaking() {
		return false;
	}
	
	@Override
	public boolean isInvisible() {
		return true;
	}
	
	@Override
	public void onStruckByLightning(EntityLightningBolt par1) {}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return true;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return false;
	}
	
	@Override
	protected void collideWithEntity(Entity par1Entity) {}
	
	@Override
	protected void collideWithNearbyEntities() {}
	
	@Override
	public boolean doesEntityNotTriggerPressurePlate() {
		return true;
	}
}

package net.tardis.mod.common.dimensions.moon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.renderers.sky.SkyRendererMoon;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.dimensions.telos.ChunkGeneratorTelos;

public class MoonProvider extends WorldProvider{

	private static BiomeProviderSingle biomeP = new BiomeProviderSingle(TDimensions.moonBiome);
	
	public MoonProvider() {}
	
	@Override
	public DimensionType getDimensionType() {
		return TDimensions.MOON_TYPE;
	}
	
	@Override
	public BiomeProvider getBiomeProvider() {
		return biomeP;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorTelos(this.world, this.getSeed());
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3d(0, 0, 0);
	}

	@Override
	public boolean isSkyColored() {
		return false;
	}

	@Override
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		return new Vec3d(0, 0, 0);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IRenderHandler getSkyRenderer() {
		return new SkyRendererMoon();
	}

	@Override
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		return new float[] {0, 0, 0, 0};
	}

	@Override
	public void onPlayerAdded(EntityPlayerMP player) {
		super.onPlayerAdded(player);
		player.setNoGravity(true);
	}

	@Override
	public void onPlayerRemoved(EntityPlayerMP player) {
		super.onPlayerRemoved(player);
		player.setNoGravity(false);
	}
	
	@EventBusSubscriber
	public static class Events{
		
		@SubscribeEvent
		public static void grav(LivingUpdateEvent event) {
			if(event.getEntityLiving().dimension == TDimensions.MOON_ID) {
				if(!event.getEntityLiving().onGround) {
					event.getEntityLiving().motionY -= 0.01;
					event.getEntityLiving().fallDistance *= 0.01D;
				}
			}
		}
		
	}

}

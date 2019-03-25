package net.tardis.mod.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tardis.mod.Tardis;
import net.tardis.mod.capability.TardisCapStorage.TardisCapProvider;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class CapabilityTardis implements ITardisCap{

	EntityPlayer player;
	BlockPos pos = BlockPos.ORIGIN;
	
	public CapabilityTardis() {}
	
	public CapabilityTardis(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public void setTardis(BlockPos pos) {
		this.pos = pos;
	}

	@Override
	public BlockPos getTardis() {
		return pos;
	}

	@Override
	public void update() {
		if(this.getTardis().equals(BlockPos.ORIGIN) && player.dimension == TDimensions.TARDIS_ID) {
			this.setTardis(TardisHelper.getTardisForPosition(player.getPosition()));
		}
		else if(!this.getTardis().equals(BlockPos.ORIGIN) && player.dimension != TDimensions.TARDIS_ID) {
			this.setTardis(BlockPos.ORIGIN);
		}
		if(player.dimension == TDimensions.TARDIS_ID && !this.getTardis().equals(BlockPos.ORIGIN)) {
			if(player.getPosition().distanceSq(this.getTardis()) > 16384)
				player.setPositionAndUpdate(this.getTardis().getX(), this.getTardis().getY(), this.getTardis().getZ());
		}
	}

	@Mod.EventBusSubscriber(modid = Tardis.MODID)
	public static class Events{
		
		@SubscribeEvent
		public static void attach(AttachCapabilitiesEvent<Entity> event) {
			if(event.getObject() instanceof EntityPlayer)
				event.addCapability(new ResourceLocation(Tardis.MODID, "tardis_cap"), new TardisCapProvider((EntityPlayer)event.getObject()));
		}
		
		@SubscribeEvent
		public static void update(LivingUpdateEvent event) {
			CapabilityTardis cap = event.getEntityLiving().getCapability(TardisCapStorage.CAP, null);
			if(cap != null) {
				cap.update();
			}
		}
	}
}

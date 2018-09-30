package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageMissControl implements IMessage{

	public BlockPos pos = BlockPos.ORIGIN;
	
	public MessageMissControl() {}
	
	public MessageMissControl(BlockPos pos) {
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
	}
	
	public static class Handler implements IMessageHandler<MessageMissControl, IMessage>{

		@Override
		public IMessage onMessage(MessageMissControl message, MessageContext ctx) {
			//Sub please don't
			Minecraft.getMinecraft().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					Vec3d look = new Vec3d(0.1, 0, 0.1);
					for(int i = 0; i < 300; ++i) {
						Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, message.pos.getX() + 0.5, message.pos.getY(), message.pos.getZ() + 0.5, look.x, look.y, look.z, 0);
						look = look.rotateYaw(0.003F);
					}
				}
			});
			return null;
		}
		
	}

}

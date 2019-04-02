package net.tardis.mod.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.client.guis.GUICompanion.EnumAction;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.entities.EntityCompanion;
import net.tardis.mod.common.tileentity.TileEntityTardis;
import net.tardis.mod.util.common.helpers.Helper;
import net.tardis.mod.util.common.helpers.TardisHelper;

public class MessageCompanion implements IMessage {

	public int id;
	public EnumAction action;

	public MessageCompanion() {
	}

	public MessageCompanion(int id, EnumAction act) {
		this.id = id;
		this.action = act;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readInt();
		this.action = Enum.valueOf(EnumAction.class, ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeUTF8String(buf, action.name());
	}

	public static class Handler implements IMessageHandler<MessageCompanion, IMessage> {
		@Override
		public IMessage onMessage(MessageCompanion message, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(new Runnable() {
				@Override
				public void run() {
					EntityCompanion comp = (EntityCompanion) ctx.getServerHandler().player.getServerWorld().getEntityByID(message.id);
					if (comp != null) {
						if (message.action == EnumAction.GO_TO_TARDIS) {
							WorldServer world = ctx.getServerHandler().player.getServer().getWorld(TDimensions.TARDIS_ID);
							BlockPos pos = TardisHelper.hasTardis(ctx.getServerHandler().player.getGameProfile().getId()) ? TardisHelper.getTardis(ctx.getServerHandler().player.getGameProfile().getId()) : BlockPos.ORIGIN;
							if (pos.equals(BlockPos.ORIGIN)) return;
							TileEntityTardis tardis = Helper.getTardis(world.getTileEntity(pos));
							if (tardis != null) {
								comp.setSit(true);
								comp.tardisPos = tardis.getLocation().toImmutable();
							}
						}
						else if (message.action == EnumAction.FOLLOW) {
							comp.setSit(!comp.getSit());
							comp.tardisPos = BlockPos.ORIGIN;
						}
						else if (message.action == EnumAction.BRING_TARDIS) {
							if (comp.dimension != TDimensions.TARDIS_ID) {
								WorldServer world = ctx.getServerHandler().player.world.getMinecraftServer().getWorld(TDimensions.TARDIS_ID);
								if (comp.getOwner() != null && TardisHelper.hasTardis(comp.getOwner().getGameProfile().getId())) {
									TileEntityTardis tardis = Helper.getTardis(world.getTileEntity(TardisHelper.getTardis(comp.getOwner().getGameProfile().getId())));
									if (tardis != null) {
										comp.setSit(true);
										comp.tardisPos = tardis.getLocation();
										comp.flyTardis = true;
									}
								}
							}
						}
						else if(message.action == EnumAction.TAKE_HELD) {
							ItemStack oldHeld = comp.getHeldItemMainhand();
							if(!oldHeld.isEmpty())
								InventoryHelper.spawnItemStack(ctx.getServerHandler().player.world, comp.posX, comp.posY, comp.posZ, oldHeld);
							comp.setHeldItem(EnumHand.MAIN_HAND, ctx.getServerHandler().player.getHeldItem(EnumHand.MAIN_HAND));
							ctx.getServerHandler().player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
						}
					}
				}
			});
			return null;
		}

	}
}

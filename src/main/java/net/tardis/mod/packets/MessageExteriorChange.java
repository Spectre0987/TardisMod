package net.tardis.mod.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tardis.mod.common.blocks.BlockTardisTop;
import net.tardis.mod.common.dimensions.TDimensions;
import net.tardis.mod.common.systems.SystemCCircuit;
import net.tardis.mod.common.tileentity.TileEntityDoor;
import net.tardis.mod.common.tileentity.TileEntityTardis;

public class MessageExteriorChange implements IMessage {

	public BlockPos pos = BlockPos.ORIGIN;
	public IBlockState state = Blocks.AIR.getDefaultState();
	
	public MessageExteriorChange() {}
	
	public MessageExteriorChange(BlockPos pos, IBlockState state){
		this.pos = pos;
		this.state = state;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = BlockPos.fromLong(buf.readLong());
		this.state = Block.getStateById(buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeInt(Block.getStateId(state));
	}
	
	public static class Handler implements IMessageHandler<MessageExteriorChange, IMessage>{

		public Handler() {}
		
		@Override
		public IMessage onMessage(MessageExteriorChange mes, MessageContext ctx) {
			ctx.getServerHandler().player.getServerWorld().addScheduledTask(() -> {
                if(mes.state.getBlock() instanceof BlockTardisTop) {
                    WorldServer ws = DimensionManager.getWorld(TDimensions.TARDIS_ID);
                    TileEntity te = ws.getTileEntity(mes.pos);
                    if(te != null && te instanceof TileEntityTardis) {
                    	TileEntityTardis tardis = (TileEntityTardis)te;
                       if(tardis.getSystem(SystemCCircuit.class) != null && ((SystemCCircuit)tardis.getSystem(SystemCCircuit.class)).getHealth() > 0.0F) {
                    	   tardis.setExterior(mes.state);
                           WorldServer world = ws.getMinecraftServer().getWorld(tardis.dimension);
                           TileEntity door = world.getTileEntity(tardis.getLocation().up());
                           EnumFacing face = world.getBlockState(door.getPos()).getValue(BlockTardisTop.FACING);
                           NBTTagCompound tag = door.writeToNBT(new NBTTagCompound());
                           world.setBlockState(door.getPos(), mes.state.withProperty(BlockTardisTop.FACING, face));
                           ((TileEntityDoor)world.getTileEntity(door.getPos())).readFromNBT(tag);
                       }
                    }
                }
            });
			return null;
        }
    }

}

package net.tardis.mod.client.worldshell;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;

public class EntityStorage {

	public double posX, posY, posZ;
	public int id;

	public EntityStorage(Entity e) {
		id = EntityList.getID(e.getClass());
		posX = e.posX;
		posY = e.posY;
		posZ = e.posZ;
	}

	public EntityStorage() {
	}

	public static EntityStorage readFromBuf(ByteBuf bb) {
		EntityStorage stor = new EntityStorage();
		stor.id = bb.readInt();
		stor.posX = bb.readDouble();
		stor.posY = bb.readDouble();
		stor.posZ = bb.readDouble();
		return stor;
	}

	public void writeToBuf(ByteBuf bb) {
		bb.writeInt(id);
		bb.writeDouble(posX);
		bb.writeDouble(posY);
		bb.writeDouble(posZ);
	}

}

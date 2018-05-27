package net.tardis.mod.client.boti;

import net.minecraft.util.math.Vec3d;
import net.tardis.mod.packets.MessageRequestChunks;
import net.tardis.mod.util.Vec2d;


public interface ICameraInterface {
	
	int getCameraID();
	
	void setCameraID(int id);
	
	int getRenderDimension();
	
	MessageRequestChunks requestChunks();
	
	boolean isChunkEmpty(FakeWorld world);
	
	Vec3d getCameraSpawnPos();
	
	void setupOffset(EntityCamera camera, Vec3d tilePos);
	
	Vec2d getResolution();
	
}

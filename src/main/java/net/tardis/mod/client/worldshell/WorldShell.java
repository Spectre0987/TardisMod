package net.tardis.mod.client.worldshell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldShell implements IBlockAccess {

	// Keeps stores a collection of BlockAcess's mapped to blockpos coords
	public Map<BlockPos, BlockStorage> blockMap;
	@SideOnly(Side.CLIENT)
	BufferBuilder.State bufferstate;
	boolean updateRequired = false;
	// Contains every TESR to speed up rendering
	private List<TileEntity> tesrs;
	//Entities to render
	private List<NBTTagCompound> entities;
	private List<PlayerStorage> players = new ArrayList<>();
	// The distance between the root of the worldShell and (0,0,0). Subtracting this
	// from the coords in BlockMap should give you positions in relative terms for
	// rendering
	private BlockPos offset;
	private long time = 0L;
	private float rotation = 0F;
	private Biome shellBiome = Biome.getBiome(0);
	private List<Entity> renderEntities = new ArrayList<Entity>();
	private List<EntityPlayer> playersForRender = new ArrayList<EntityPlayer>();

	public WorldShell(BlockPos bp) {
		blockMap = new HashMap<>();
		offset = bp;
		tesrs = new ArrayList<>();
		entities = new ArrayList<>();
	}

	public BlockPos getOffset() {
		return offset;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public TileEntity getTileEntity(BlockPos pos) {
		if (blockMap == null || Minecraft.getMinecraft().world == null ||pos == null || !blockMap.containsKey(pos) || blockMap.get(pos) == null || blockMap.get(pos).tileentity == null) return null;
		return TileEntity.create(Minecraft.getMinecraft().world, blockMap.get(pos).tileentity);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getCombinedLight(BlockPos pos, int lightValue) {
		return 15;
	}

	public int getLightSet(EnumSkyBlock type, BlockPos pos) {
		return 15;
	}


	public void setShellBiome(Biome shellBiome) {
		this.shellBiome = shellBiome;
	}

	@Override
	public IBlockState getBlockState(BlockPos pos) {
		if (blockMap.get(pos) != null) {
			return blockMap.get(pos).blockstate;
		} else {
			return Blocks.AIR.getDefaultState();
		}
	}

	@Override
	public boolean isAirBlock(BlockPos pos) {
		return blockMap.get(pos).blockstate.getMaterial() == Material.AIR;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Biome getBiome(BlockPos pos) {
		return shellBiome;
	}

	@Override
	public int getStrongPower(BlockPos pos, EnumFacing direction) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public WorldType getWorldType() {
		return WorldType.DEFAULT;
	}

	@Override
	public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
		if (!blockMap.containsKey(pos))
			return _default;
		return blockMap.get(pos).blockstate.isSideSolid(this, pos, side);
	}

	public List<TileEntity> getTESRs() {
		return tesrs;
	}

	@SideOnly(Side.CLIENT)
	public void setTESRs() {
		for (BlockStorage bs : blockMap.values()) {
			if (bs.tileentity != null) {
				TileEntity te = TileEntity.create(Minecraft.getMinecraft().world, bs.tileentity);
				if (te != null) {
					TileEntitySpecialRenderer renderer = TileEntityRendererDispatcher.instance.renderers.get(te.getClass());
					if (renderer != null)
						tesrs.add(te);
				}
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void setEntities(World world) {
		for(NBTTagCompound tag : this.entities) {
			Entity e = EntityList.createEntityFromNBT(tag, world);
			if(e != null)
				this.renderEntities.add(e);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public List<Entity> getEntitiesForRender(){
		return this.renderEntities;
	}
	
	public List<NBTTagCompound> getEntities() {
		return this.entities;
	}

	public void setEntities(List<NBTTagCompound> entity) {
		this.entities = entity;
	}

	public List<PlayerStorage> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<PlayerStorage> storage) {
		this.players = storage;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * Gets the rotation to render the world from (In the case of doors)
	 **/
	public float getRotation() {
		return this.rotation;
	}

	public void setRotation(float rot) {
		this.rotation = rot;
	}
	
	@SideOnly(Side.CLIENT)
	public List<EntityPlayer> getPlayersForRender(){
		return this.playersForRender;
	}
	
	@SideOnly(Side.CLIENT)
	public void setPlayers(World world) {
		this.playersForRender.clear();
		for(PlayerStorage stor : this.players) {
			this.playersForRender.add(stor.getPlayer(world));
		}
	}
}

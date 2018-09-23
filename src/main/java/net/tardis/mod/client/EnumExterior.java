package net.tardis.mod.client;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.Tardis;
import net.tardis.mod.client.models.IInteriorModel;
import net.tardis.mod.client.models.ModelBlocks;
import net.tardis.mod.client.models.exteriors.IExteriorModel;
import net.tardis.mod.client.models.exteriors.ModelTardis01;
import net.tardis.mod.client.models.exteriors.ModelTardis02;
import net.tardis.mod.client.models.exteriors.ModelTardis03;
import net.tardis.mod.client.models.exteriors.ModelTardis04;
import net.tardis.mod.client.models.interiors.ModelInteriorDoor01;
import net.tardis.mod.client.models.interiors.ModelInteriorDoor02;
import net.tardis.mod.client.models.interiors.ModelInteriorDoor03;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor03;
import net.tardis.mod.client.renderers.exteriors.RenderTileDoor04;
import net.tardis.mod.client.renderers.exteriors.RendererTileDoor01;
import net.tardis.mod.client.renderers.tiles.RenderTileDoor;
import net.tardis.mod.common.blocks.TBlocks;

@SideOnly(Side.CLIENT)
public enum EnumExterior {

	FIRST(new ModelTardis01(), new ModelInteriorDoor01(), RenderTileDoor.TEXTURE, TBlocks.tardis_top, "box.tardis.01"),
	SECOND(new ModelTardis02(), new ModelInteriorDoor02(), RendererTileDoor01.TEXTURE, TBlocks.tardis_top_01, "box.tardis.02"),
	THIRD(new ModelTardis03(), new ModelInteriorDoor03(), RenderTileDoor03.TEXTURE, TBlocks.tardis_top_02, "box.tardis.03"),
	CC(new ModelBlocks(new ResourceLocation(Tardis.MODID + ":shells/tree.json")), new ModelInteriorDoor01(), RenderTileDoor.TEXTURE, TBlocks.tardis_top_cc, "box.tardis.cc"),
	FOURTH(new ModelTardis04(), new ModelInteriorDoor01(), RenderTileDoor04.TEXTURE, TBlocks.tardis_top_03, "box.tardis.04");
	
	public IExteriorModel model;
	public IInteriorModel interiorModel;
	public ResourceLocation tex;
	public Block block;
	public String name = "";
	
	EnumExterior(IExteriorModel base, IInteriorModel intModel, ResourceLocation tex, Block block, String name){
		this.model = base;
		this.tex = tex;
		this.block = block;
		this.interiorModel = intModel;
		this.name = name;
	}
	
	public static EnumExterior getExteriorFromBlock(Block block) {
		for(EnumExterior ext : EnumExterior.values()) {
			if(ext.block == block) {
				return ext;
			}
		}
		return EnumExterior.FIRST;
	}
}

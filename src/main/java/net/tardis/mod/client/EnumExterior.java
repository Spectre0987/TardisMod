package net.tardis.mod.client;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tardis.mod.client.models.exteriors.IExteriorModel;
import net.tardis.mod.client.models.interiors.IInteriorModel;
import net.tardis.mod.common.blocks.TBlocks;

@SideOnly(Side.CLIENT)
public enum EnumExterior {

	FIRST(TBlocks.tardis_top, "box.tardis.01"),
	SECOND(TBlocks.tardis_top_01, "box.tardis.02"),
	THIRD(TBlocks.tardis_top_02, "box.tardis.03"),
	CC(TBlocks.tardis_top_cc, "box.tardis.cc"),
	FOURTH(TBlocks.tardis_top_03, "box.tardis.04"),
	FIFTH(TBlocks.tardis_top_04, "box.tardis.05"),
	CLOCK(TBlocks.tardis_top_clock, "box.master.clock"),
	TT(TBlocks.tardis_top_tt, "box.tt"),
	WOOD_DOOR(TBlocks.tardis_top_wood_door, "box.door"),
	WARDROBE(TBlocks.tardis_top_wardrobe, "box.wardrobe");

	@SideOnly(Side.CLIENT)
	public IExteriorModel model;
	@SideOnly(Side.CLIENT)
	public IInteriorModel interiorModel;
	public ResourceLocation tex;
	public Block block;
	public String name;

	EnumExterior(Block block, String name) {
		this.block = block;
		this.name = name;
	}

	public static EnumExterior getExteriorFromBlock(Block block) {
		for (EnumExterior ext : EnumExterior.values()) {
			if (ext.block == block) {
				return ext;
			}
		}
		return EnumExterior.FIRST;
	}
	
	public void setupModels(IExteriorModel model, IInteriorModel intModel, ResourceLocation texture) {
		this.model = model;
		this.interiorModel = intModel;
		this.tex = texture;
	}
}

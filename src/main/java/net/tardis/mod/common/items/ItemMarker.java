package net.tardis.mod.common.items;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tardis.mod.Tardis;
import net.tardis.mod.util.helpers.Helper;

public class ItemMarker extends ItemBase {
	
	public ItemMarker() {
		this.setCreativeTab(Tardis.tab);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		NBTTagCompound tag = Helper.getStackTag(player.getHeldItem(hand));
		if(tag.hasKey(NBT.FIRST_POS)) {
			tag.setLong(NBT.LAST_POS, pos.offset(facing).toLong());
		}
		else tag.setLong(NBT.FIRST_POS, pos.offset(facing).toLong());
		player.getHeldItem(hand).setTagCompound(tag);
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(worldIn.isRemote) {
			NBTTagCompound tag = Helper.getStackTag(playerIn.getHeldItem(handIn));
			if(tag.hasKey(NBT.FIRST_POS) && tag.hasKey(NBT.LAST_POS)) {
				BlockPos fp = BlockPos.fromLong(tag.getLong(NBT.FIRST_POS));
				BlockPos ep = BlockPos.fromLong(tag.getLong(NBT.LAST_POS));
				try {
					File f = new File(Minecraft.getMinecraft().mcDataDir + "/shells");
					f.mkdirs();
					f = new File(f.getCanonicalPath() + "/render.json");
					if(f.exists())f.createNewFile();
					GsonBuilder gb = new GsonBuilder();
					gb.setPrettyPrinting();
					JsonWriter jw = gb.create().newJsonWriter(new FileWriter(f));
					
					jw.beginObject();
					
					jw.name("data");
					jw.beginObject();
					
					jw.name("size");
					jw.beginArray();
					BlockPos sizePos = ep.subtract(fp);
					jw.value(Math.abs(sizePos.getX()));
					jw.value(Math.abs(sizePos.getY()));
					jw.value(Math.abs(sizePos.getZ()));
					jw.endArray();
					
					jw.name("position");
					jw.beginArray();
					jw.value(0);
					jw.value(0);
					jw.value(0);
					jw.endArray();
					
					jw.name("rotation");
					jw.beginArray();
					jw.value(0D);
					jw.value(0D);
					jw.value(0D);
					jw.endArray();
					jw.endObject();
					
					jw.name("structure");
					jw.beginObject();
					
					for(BlockPos pos : BlockPos.getAllInBox(fp, ep)) {
						if(worldIn.getBlockState(pos).getMaterial() != Material.AIR) {
							jw.name("blocks");
							jw.beginObject();
							
							jw.name("X").value(fp.getX() - pos.getX());
							jw.name("Y").value(fp.getY() - pos.getY());
							jw.name("Z").value(fp.getZ() - pos.getZ());
							jw.name("Block").value(worldIn.getBlockState(pos).getBlock().getRegistryName().toString());
							jw.name("Meta").value(worldIn.getBlockState(pos).getBlock().getMetaFromState(worldIn.getBlockState(pos)));
							jw.endObject();
						}
					}
					
					jw.endObject();
					
					jw.endObject();
					jw.close();
				}
				catch(IOException e) {}
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound tag = Helper.getStackTag(stack);
		if(tag.hasKey(NBT.FIRST_POS))tooltip.add("First pos: " + Helper.formatBlockPos(BlockPos.fromLong(tag.getLong(NBT.FIRST_POS))));
		if(tag.hasKey(NBT.LAST_POS))tooltip.add("Last pos: " + Helper.formatBlockPos(BlockPos.fromLong(tag.getLong(NBT.LAST_POS))));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	public static class NBT{
		public static final String FIRST_POS = "first_pos";
		public static final String LAST_POS = "last_pos";
	}
}

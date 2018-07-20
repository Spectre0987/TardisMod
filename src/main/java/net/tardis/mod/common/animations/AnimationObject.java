package net.tardis.mod.common.animations;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.tardis.mod.common.entities.controls.EntityControl;

public class AnimationObject {
	
	public ArrayList<EntityControl> objectsToAnimated = new ArrayList<>();
	
	public AnimationObject(){}
	
	public void fromNBT(NBTTagCompound tag, World world) {
		NBTTagCompound nTag = tag.getCompoundTag(NBT.TAG);
		NBTTagList list = nTag.getTagList(NBT.LIST, Constants.NBT.TAG_INT);
		Iterator<NBTBase> it = list.iterator();
		while(it.hasNext()) {
			NBTTagInt intTag = (NBTTagInt)it.next();
			Entity ent = world.getEntityByID(intTag.getInt());
			if(ent != null && ent instanceof EntityControl) {
				this.objectsToAnimated.add((EntityControl)ent);
			}
		}
	}
	
	public NBTTagCompound toNBT(NBTTagCompound compound) {
		NBTTagCompound tag = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		for(EntityControl c : this.objectsToAnimated) {
			list.appendTag(new NBTTagInt(c.getEntityId()));
		}
		tag.setTag(NBT.LIST, list);
		compound.setTag(NBT.TAG, tag);
		return compound;
	}
	
	public static class NBT{
		public static final String LIST = "tagList";
		public static final String TAG = "aniObjTag";
	}
	
	public void animate(EntityControl control) {
		for(EntityControl c : this.objectsToAnimated) {
			if(c.getEntityId() == control.getEntityId())
				return;
		}
		this.objectsToAnimated.add(control);
	}

}

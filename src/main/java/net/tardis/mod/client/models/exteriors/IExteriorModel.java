package net.tardis.mod.client.models.exteriors;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IExteriorModel {

	void renderClosed(float scale);

	void renderOpen(float scale);
}

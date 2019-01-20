package net.tardis.mod.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.tardis.mod.Tardis;
import org.lwjgl.input.Keyboard;

public class TardisKeyBinds {
    public static KeyBinding SNAP;

    public static void init(){
        SNAP = new KeyBinding(Tardis.MODID+".keybinds.snap", Keyboard.KEY_C, Tardis.NAME);
        ClientRegistry.registerKeyBinding(SNAP);
    }
}

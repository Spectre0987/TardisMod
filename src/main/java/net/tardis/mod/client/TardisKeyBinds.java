package net.tardis.mod.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.tardis.mod.Tardis;
import org.lwjgl.input.Keyboard;

public class TardisKeyBinds {
    public static KeyBinding DEMAT_REMAT_ANIM;

    public static void init(){
        DEMAT_REMAT_ANIM = new KeyBinding(Tardis.MODID+".keybinds.demat_remat_anim", Keyboard.KEY_J, Tardis.NAME);
        ClientRegistry.registerKeyBinding(DEMAT_REMAT_ANIM);
    }
}

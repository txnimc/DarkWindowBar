package toni.darkwindowbar.mixins;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import toni.darkwindowbar.DarkWindowBar;

@Mixin(Minecraft.class)
public class WindowCreateMixin {
    @Shadow @Final private Window window;

    @Inject(at = {@At("TAIL")}, method = {"<init>"})
    public void createWindow(GameConfig gameConfig, CallbackInfo ci) {
        DarkWindowBar.setDarkWindowBar(this.window);
    }
}

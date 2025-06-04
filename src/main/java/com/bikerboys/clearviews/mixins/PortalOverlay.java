package com.bikerboys.clearviews.mixins;


import com.bikerboys.clearviews.Config;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class)
public class PortalOverlay {



    @Inject(method = "renderPortalOverlay", at = @At("HEAD"), cancellable = true)
    private void renderPortalOverlayd(GuiGraphics p_283375_, float p_283296_, CallbackInfo ci) {

        if (Config.PortalOverlay) {
            ci.cancel();
        }
    }
    @Inject(method = "renderSpyglassOverlay", at = @At("HEAD"), cancellable = true)
    private void changespygalass(GuiGraphics context, float scale, CallbackInfo ci) {
        if (Config.SpyglassOverlay) {
            ci.cancel();
        }


    }


}

package com.bikerboys.clearviews.mixins;


import com.bikerboys.clearviews.Config;
import com.mojang.blaze3d.shaders.FogShape;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogParameters;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.renderer.FogRenderer;

@Mixin(value = FogRenderer.class)
public class FogMixin  {


    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    private static void alwaysSpectatorFog(Camera p_234173_, FogRenderer.FogMode p_234174_, Vector4f color, float p_234175_, boolean p_234176_, float p_234177_, CallbackInfoReturnable<FogParameters> cir) {
        // Force spectator fog settings
        FogShape fogShape = FogShape.SPHERE;

        int fogStart = Config.FogStart;
        int fogEnd = Config.FogEnd;

        if (Config.CustomFog) {
            cir.setReturnValue(new FogParameters(fogStart, fogEnd, fogShape, color.x, color.y, color.z, color.w));
        }
    }

}

package com.bikerboys.clearviews;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {



    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DARKNESSEFFECT = BUILDER
            .comment("If set to true, the darkness effect will be removed")
            .define("Darkness Effect Remover", true);

    private static final ForgeConfigSpec.BooleanValue BLINDNESS = BUILDER
            .comment("If set to true, the blindness effect will be removed")
            .define("Blindness Effect Remover", true);

    private static final ForgeConfigSpec.BooleanValue NAUSEA = BUILDER
            .comment("If set to true, the nausea effect will be removed")
            .define("Nausea Effect Remover", true);

    private static final ForgeConfigSpec.BooleanValue PORTALOVERLAY = BUILDER
            .comment("If set to true, the portal overlay will be removed/disabled")
            .define("Portal Overlay Remover", true);

    private static final ForgeConfigSpec.BooleanValue REMOVESPYGLASSOVERLAY = BUILDER
            .comment("If set to true, the spyglass overlay will be removed/disabled")
            .define("Spyglass Overlay Remover", true);

    private static final ForgeConfigSpec.BooleanValue CUSTOMFOG = BUILDER
            .comment("If set to true, the custom fog will be applied. else the regular fog will be applied")
            .define("Custom Fog", true);

    private static final ForgeConfigSpec.IntValue FOGSTART = BUILDER
            .comment("Defines where the fog effect will start, it will smooth the fog effect over between the fogstart and fogend values")
            .defineInRange("Fog Start", 1000, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue FOGEND = BUILDER
            .comment("Defines where the fog effect will end, it will smooth the fog effect over between the fogstart and fogend values")
            .defineInRange("Fog End", 1000, Integer.MIN_VALUE, Integer.MAX_VALUE);


    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static boolean DarknessRemover;
    public static boolean BlindnessRemover;
    public static boolean NauseaRemover;

    public static boolean PortalOverlay;
    public static boolean SpyglassOverlay;
    public static boolean CustomFog;

    public static int FogEnd;
    public static int FogStart;




    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {

        DarknessRemover = DARKNESSEFFECT.get();
        BlindnessRemover = BLINDNESS.get();
        NauseaRemover = NAUSEA.get();

        CustomFog = CUSTOMFOG.get();

        PortalOverlay = PORTALOVERLAY.get();
        SpyglassOverlay = REMOVESPYGLASSOVERLAY.get();

        FogEnd = FOGEND.get();
        FogStart = FOGSTART.get();
    }
}

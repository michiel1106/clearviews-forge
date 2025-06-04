package com.bikerboys.clearviews;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "clearviews";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();




    public ExampleMod(FMLJavaModLoadingContext context) {


        // Register the commonSetup method for modloading
        context.getModEventBus().addListener(this::commonSetup);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);




        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);


        MinecraftForge.registerConfigScreen(((minecraft, screen) -> new ClearviewConfigScreen(Component.literal("Clearview Config"), screen, minecraft)));

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ClientModEvents {




        @SubscribeEvent
        public static void onClientTIck(TickEvent.ClientTickEvent event) {

             if (Minecraft.getInstance().player != null) {
                 LocalPlayer mc = Minecraft.getInstance().player;

                 if (Config.NauseaRemover) {
                     mc.removeEffect(MobEffects.CONFUSION);
                 }
                 if (Config.BlindnessRemover) {
                     mc.removeEffect(MobEffects.BLINDNESS);
                 }
                 if (Config.DarknessRemover) {
                     mc.removeEffect(MobEffects.DARKNESS);
                 }

         }
     }



    }



}

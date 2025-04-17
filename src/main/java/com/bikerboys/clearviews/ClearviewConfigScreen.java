package com.bikerboys.clearviews;


import net.minecraft.client.Minecraft;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class ClearviewConfigScreen extends Screen {
    private final Screen parent;



    protected ClearviewConfigScreen(Component component, Screen parent, Minecraft minecraft) {
        super(component);

        this.minecraft = minecraft;
        this.parent = parent;
    }


    @Override
    protected void init() {
        int y = 40;
        int spacing = 25;

        addToggle(0, y, "Darkness Effect", Config.DarknessRemover, val -> Config.DarknessRemover = val);
        y += spacing;
        addToggle(1, y, "Blindness Effect", Config.BlindnessRemover, val -> Config.BlindnessRemover = val);
        y += spacing;
        addToggle(2, y, "Nausea Effect", Config.NauseaRemover, val -> Config.NauseaRemover = val);
        y += spacing;
        addToggle(3, y, "Portal Overlay", Config.PortalOverlay, val -> Config.PortalOverlay = val);
        y += spacing;
        addToggle(4, y, "Spyglass Overlay", Config.SpyglassOverlay, val -> Config.SpyglassOverlay = val);
        y += spacing;
        addToggle(5, y, "Custom Fog", Config.CustomFog, val -> Config.CustomFog = val);
        y += spacing;

        // Label for Fog Start
        this.addRenderableOnly(new StringWidget(
                this.width / 2 - 100,
                y,
                200,
                20,
                Component.literal("Fog Start Distance (m)"),
                this.font
        ));
        y += 15;

        // Input for Fog Start
        EditBox fogStartBox = new EditBox(
                this.font,
                this.width / 2 - 100,
                y,
                200,
                20,
                Component.literal("Fog Start")
        );
        fogStartBox.setValue(String.valueOf(Config.FogStart));
        fogStartBox.setEditable(true);
        fogStartBox.setResponder(value -> {
            try {
                Config.FogStart = Integer.parseInt(value);
            } catch (NumberFormatException ignored) {}
        });
        this.addRenderableWidget(fogStartBox);
        y += spacing;

        // Label for Fog End
        this.addRenderableOnly(new StringWidget(
                this.width / 2 - 100,
                y,
                200,
                20,
                Component.literal("Fog End Distance (m)"),
                this.font
        ));
        y += 15;

        // Input for Fog End
        EditBox fogEndBox = new EditBox(
                this.font,
                this.width / 2 - 100,
                y,
                200,
                20,
                Component.literal("Fog End")
        );
        fogEndBox.setValue(String.valueOf(Config.FogEnd));
        fogEndBox.setEditable(true);
        fogEndBox.setResponder(value -> {
            try {
                Config.FogEnd = Integer.parseInt(value);
            } catch (NumberFormatException ignored) {}
        });
        this.addRenderableWidget(fogEndBox);
        y += spacing;

        // Done Button
        Button done = addRenderableWidget(
                Button.builder(Component.literal("Done"), btn -> this.minecraft.setScreen(parent))
                        .pos(this.width / 2 - 100, y)
                        .size(200, 20)
                        .build()
        );
    }

    private void addToggle(int index, int y, String label, boolean initialValue, java.util.function.Consumer<Boolean> setter) {
        final boolean[] currentValue = { initialValue };

        Button toggle = Button.builder(
                        Component.literal(label + ": " + (currentValue[0] ? "Enabled" : "Disabled")),
                        btn -> {
                            currentValue[0] = !currentValue[0];
                            setter.accept(currentValue[0]);
                            btn.setMessage(Component.literal(label + ": " + (currentValue[0] ? "Enabled" : "Disabled")));
                        }
                )
                .pos(this.width / 2 - 100, y)
                .size(200, 20)
                .build();

        addRenderableWidget(toggle);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }




}

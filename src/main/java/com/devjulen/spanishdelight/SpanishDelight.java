package com.devjulen.spanishdelight;

import com.devjulen.spanishdelight.common.event.ModEvents;
import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import com.devjulen.spanishdelight.common.registry.ModCreativeTabs;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(SpanishDelight.MOD_ID)
public class SpanishDelight {
    public static final String MOD_ID = "spanishdelight";

    public SpanishDelight(IEventBus modEventBus) {
        ModBlocksRegistry.register(modEventBus);
        ModItemsRegistry.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        modEventBus.addListener(this::onAddCreative);

        NeoForge.EVENT_BUS.addListener(ModEvents::addCustomTrades);
        NeoForge.EVENT_BUS.addListener(ModEvents::addCustomWanderingTrades);
    }

    private void onAddCreative(BuildCreativeModeTabContentsEvent event) {}
}

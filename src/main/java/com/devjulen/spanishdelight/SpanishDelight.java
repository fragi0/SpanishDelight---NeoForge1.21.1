package com.devjulen.spanishdelight;

import com.devjulen.spanishdelight.common.event.ModEvents;
import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import com.devjulen.spanishdelight.common.registry.ModCreativeTabs;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import com.devjulen.spanishdelight.data.ModBlockStateProvider;
import com.devjulen.spanishdelight.data.ModItemModelProvider;
import com.devjulen.spanishdelight.data.ModItemTagsProvider;
import com.devjulen.spanishdelight.data.ModLootTableProvider;
import com.devjulen.spanishdelight.data.ModRecipeProvider;
import com.devjulen.spanishdelight.data.worldgen.ModWorldgenProvider;
import com.devjulen.spanishdelight.data.worldgen.ModBiomeModifierJsonProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(SpanishDelight.MOD_ID)
public class SpanishDelight {
    public static final String MOD_ID = "spanishdelight";

    public SpanishDelight(IEventBus modEventBus) {
        ModBlocksRegistry.register(modEventBus);
        ModItemsRegistry.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        modEventBus.addListener(this::onAddCreative);
        modEventBus.addListener(this::gatherData);

        NeoForge.EVENT_BUS.addListener(ModEvents::addCustomTrades);
        NeoForge.EVENT_BUS.addListener(ModEvents::addCustomWanderingTrades);
    }

    private void onAddCreative(BuildCreativeModeTabContentsEvent event) {}

    private void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var out = gen.getPackOutput();
        var lookup = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        // Client
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(out, helper));
        gen.addProvider(event.includeClient(), new ModItemModelProvider(out, helper));

        // Server
        gen.addProvider(event.includeServer(), new ModLootTableProvider(out, lookup));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(out, lookup));
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(out, lookup, helper));

        // Worldgen
        gen.addProvider(event.includeServer(), new ModWorldgenProvider(out, lookup));
        gen.addProvider(event.includeServer(), new ModBiomeModifierJsonProvider(out));
    }
}

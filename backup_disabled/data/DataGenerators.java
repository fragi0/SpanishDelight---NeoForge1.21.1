package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.data.loot.SDBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = SpanishDelight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new ItemModels(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new BlockStates(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new LootTables(packOutput, Collections.emptySet(), List.of(
                new LootTableProvider.SubProviderEntry(SDBlockLootTables::new, LootContextParamSets.BLOCK)
        )));
        generator.addProvider(event.includeServer(), new GlobalLootModifiers(packOutput));
        BlockTags blockTags = generator.addProvider(event.includeServer(), new BlockTags(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemTags(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new Recipes(packOutput));
        generator.addProvider(event.includeServer(), new WorldGen(packOutput, lookupProvider));
    }
}

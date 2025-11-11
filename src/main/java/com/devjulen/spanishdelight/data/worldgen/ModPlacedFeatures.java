package com.devjulen.spanishdelight.data.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public final class ModPlacedFeatures {

    public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = ctx.lookup(Registries.CONFIGURED_FEATURE);

        ctx.register(ModWorldgenKeys.WILD_GARLIC_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_GARLIC_PATCH), commonPlacement(24)));
        ctx.register(ModWorldgenKeys.WILD_RED_PEPPER_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_RED_PEPPER_PATCH), commonPlacement(28)));
        ctx.register(ModWorldgenKeys.WILD_GREEN_PEPPER_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_GREEN_PEPPER_PATCH), commonPlacement(28)));
    }

    private static List<PlacementModifier> commonPlacement(int rarityAvgChunks) {
        return List.of(
                RarityFilter.onAverageOnceEvery(rarityAvgChunks),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome()
        );
    }

    private ModPlacedFeatures() {}
}

package com.devjulen.spanishdelight.data.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public final class ModPlacedFeatures {

    public static void bootstrap(BootstrapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = ctx.lookup(Registries.CONFIGURED_FEATURE);

        ctx.register(ModWorldgenKeys.WILD_GARLIC_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_GARLIC_PATCH),
                        wildHerbPlacement(12)));

        ctx.register(ModWorldgenKeys.WILD_RED_PEPPER_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_RED_PEPPER_PATCH),
                        wildHerbPlacement(14)));

        ctx.register(ModWorldgenKeys.WILD_GREEN_PEPPER_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_GREEN_PEPPER_PATCH),
                        wildHerbPlacement(14)));
    }

    // Un parche cada ~N chunks (promedio), tries internos = 32 (definido en RandomPatchConfiguration)
    private static List<PlacementModifier> wildHerbPlacement(int rarityAvgChunks) {
        return List.of(
                RarityFilter.onAverageOnceEvery(rarityAvgChunks),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE),
                BiomeFilter.biome()
        );
    }

    private ModPlacedFeatures() {}
}

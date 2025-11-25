package com.devjulen.spanishdelight.data.worldgen;

import com.devjulen.spanishdelight.common.config.ModConfig;
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

        int garlicRarity = (int) ModConfig.COMMON.WILD_GARLIC_RARITY.getDefault();
        int pepperRarity = (int) ModConfig.COMMON.WILD_PEPPER_RARITY.getDefault();

        ctx.register(ModWorldgenKeys.WILD_GARLIC_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_GARLIC_PATCH),
                        wildHerbPlacement(garlicRarity)));

        ctx.register(ModWorldgenKeys.WILD_RED_PEPPER_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_RED_PEPPER_PATCH),
                        wildHerbPlacement(pepperRarity)));

        ctx.register(ModWorldgenKeys.WILD_GREEN_PEPPER_PLACED,
                new PlacedFeature(configured.getOrThrow(ModWorldgenKeys.WILD_GREEN_PEPPER_PATCH),
                        wildHerbPlacement(pepperRarity)));
    }

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

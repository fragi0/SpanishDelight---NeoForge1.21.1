package com.devjulen.spanishdelight.data.worldgen;

import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class ModWorldgenKeys {
    // Configured features (random_patch)
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_GARLIC_PATCH = cf("wild_garlic_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_RED_PEPPER_PATCH = cf("wild_red_pepper_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILD_GREEN_PEPPER_PATCH = cf("wild_green_pepper_patch");

    // Placed features
    public static final ResourceKey<PlacedFeature> WILD_GARLIC_PLACED = pf("wild_garlic_placed");
    public static final ResourceKey<PlacedFeature> WILD_RED_PEPPER_PLACED = pf("wild_red_pepper_placed");
    public static final ResourceKey<PlacedFeature> WILD_GREEN_PEPPER_PLACED = pf("wild_green_pepper_placed");

    private static ResourceKey<ConfiguredFeature<?, ?>> cf(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, name));
    }
    private static ResourceKey<PlacedFeature> pf(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, name));
    }

    private ModWorldgenKeys() {}
}

package com.devjulen.spanishdelight.data.worldgen;

import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class ModConfiguredFeatures {

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {
        ctx.register(ModWorldgenKeys.WILD_GARLIC_PATCH, randomPatch(ModBlocksRegistry.WILD_GARLIC.get().defaultBlockState()));
        ctx.register(ModWorldgenKeys.WILD_RED_PEPPER_PATCH, randomPatch(ModBlocksRegistry.WILD_RED_PEPPER.get().defaultBlockState()));
        ctx.register(ModWorldgenKeys.WILD_GREEN_PEPPER_PATCH, randomPatch(ModBlocksRegistry.WILD_GREEN_PEPPER.get().defaultBlockState()));
    }

    private static ConfiguredFeature<?, ?> randomPatch(net.minecraft.world.level.block.state.BlockState state) {
        // 1. Configured feature simple_block
        SimpleBlockConfiguration simpleConfig = new SimpleBlockConfiguration(BlockStateProvider.simple(state));
        ConfiguredFeature<?, ?> simpleFeature = new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, simpleConfig);
        Holder<ConfiguredFeature<?, ?>> simpleHolder = Holder.direct(simpleFeature);

        // 2. PlacedFeature sin modificadores (lo exige RandomPatchConfiguration)
        PlacedFeature placedFeature = new PlacedFeature(simpleHolder, java.util.List.of());
        Holder<PlacedFeature> placedHolder = Holder.direct(placedFeature);

        // 3. RandomPatchConfiguration: tries=32, xzSpread=6, ySpread=2 (puedes ajustar)
        RandomPatchConfiguration patchConfig = new RandomPatchConfiguration(32, 6, 2, placedHolder);

        // 4. Random patch final
        return new ConfiguredFeature<>(Feature.RANDOM_PATCH, patchConfig);
    }

    private ModConfiguredFeatures() {}
}

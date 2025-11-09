package com.devjulen.spanishdelight.common.world;

import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ForgeBiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class SDBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_WILD_GARLIC = registerKey("add_wild_garlic");
    public static final ResourceKey<BiomeModifier> ADD_RED_PEPPER_GARLIC = registerKey("add_red_pepper_garlic");
    public static final ResourceKey<BiomeModifier> ADD_GREEN_PEPPER_GARLIC = registerKey("add_green_pepper_garlic");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placed = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_WILD_GARLIC, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placed.getOrThrow(SDPlacedFeatures.WILD_GARLIC_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_RED_PEPPER_GARLIC, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_SAVANNA),
                HolderSet.direct(placed.getOrThrow(SDPlacedFeatures.WILD_RED_PEPPER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_GREEN_PEPPER_GARLIC, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_TAIGA),
                HolderSet.direct(placed.getOrThrow(SDPlacedFeatures.WILD_GREEN_PEPPER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS,
                new ResourceLocation(SpanishDelight.MOD_ID, name));
    }
}

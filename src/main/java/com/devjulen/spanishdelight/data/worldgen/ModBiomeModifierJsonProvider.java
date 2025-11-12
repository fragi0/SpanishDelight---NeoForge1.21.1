package com.devjulen.spanishdelight.data.worldgen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBiomeModifierJsonProvider implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final PackOutput output;

    public ModBiomeModifierJsonProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        return CompletableFuture.allOf(
                writeAddFeatures(cache, "add_wild_garlic", List.of(id("wild_garlic_placed"))),
                writeAddFeatures(cache, "add_wild_red_pepper", List.of(id("wild_red_pepper_placed"))),
                writeAddFeatures(cache, "add_wild_green_pepper", List.of(id("wild_green_pepper_placed")))
        );
    }

    private CompletableFuture<?> writeAddFeatures(CachedOutput cache, String filename, List<ResourceLocation> features) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "neoforge:add_features");
        root.addProperty("step", "vegetal_decoration");
        root.addProperty("biomes", "#minecraft:is_overworld");
        JsonArray arr = new JsonArray();
        for (ResourceLocation f : features) arr.add(f.toString());
        root.add("features", arr);

        Path path = output.getOutputFolder()
                .resolve("data")
                .resolve(SpanishDelight.MOD_ID)
                .resolve("neoforge")
                .resolve("biome_modifier")
                .resolve(filename + ".json");

        return DataProvider.saveStable(cache, GSON.toJsonTree(root), path);
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, path);
    }

    @Override
    public String getName() {
        return "SpanishDelight Biome Modifiers";
    }
}

package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput out) {
        // sliced_potato (shapeless)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsRegistry.SLICED_POTATO.get(), 2)
                .requires(Items.POTATO)
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(out, ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, "sliced_potato"));

        // fried_squid_ring (smelting/smoking/campfire)
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemsRegistry.SQUID_RING.get()),
                        RecipeCategory.FOOD, ModItemsRegistry.FRIED_SQUID_RING.get(), 0.35f, 200)
                .unlockedBy("has_raw", has(ModItemsRegistry.SQUID_RING.get()))
                .save(out, ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, "fried_squid_ring"));

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItemsRegistry.SQUID_RING.get()),
                        RecipeCategory.FOOD, ModItemsRegistry.FRIED_SQUID_RING.get(), 0.35f, 100)
                .unlockedBy("has_raw", has(ModItemsRegistry.SQUID_RING.get()))
                .save(out, ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, "fried_squid_ring_from_smoking"));

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ModItemsRegistry.SQUID_RING.get()),
                        RecipeCategory.FOOD, ModItemsRegistry.FRIED_SQUID_RING.get(), 0.35f, 600)
                .unlockedBy("has_raw", has(ModItemsRegistry.SQUID_RING.get()))
                .save(out, ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, "fried_squid_ring_from_campfire_cooking"));
    }
}

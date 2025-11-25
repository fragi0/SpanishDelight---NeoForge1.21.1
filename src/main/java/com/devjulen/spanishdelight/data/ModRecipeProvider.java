package com.devjulen.spanishdelight.data;

import java.util.concurrent.CompletableFuture;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ModRecipeProvider {
public ModRecipeProvider(PackOutput out, CompletableFuture<Provider> lookup) {
		// TODO Auto-generated constructor stub
	}

protected void buildRecipes(RecipeOutput out) {
    // Ya existentes
    ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsRegistry.SLICED_POTATO.get(), 2)
            .requires(Items.POTATO)
            .unlockedBy("has_potato", has(Items.POTATO))
            .save(out, rl("sliced_potato"));

    SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemsRegistry.SQUID_RING.get()),
            RecipeCategory.FOOD, ModItemsRegistry.FRIED_SQUID_RING.get(), 0.35f, 200)
            .unlockedBy("has_raw", has(ModItemsRegistry.SQUID_RING.get()))
            .save(out, rl("fried_squid_ring"));

    // Añadir shapeless onion
    ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsRegistry.SLICED_ONION.get(), 2)
            .requires(ItemsRegistry.ONION_BASE.get()) // Ajusta si onion viene de FarmersDelight
            .unlockedBy("has_onion", has(ItemsRegistry.ONION_BASE.get()))
            .save(out, rl("sliced_onion"));

    // Añadir pantumaca
    ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsRegistry.PANTUMACA.get())
            .requires(farmers("tomato"))
            .requires(Items.BREAD)
            .unlockedBy("has_tomato", has(farmers("tomato")))
            .save(out, rl("pantumaca"));

    // Añadir tortilla (shaped)
    ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItemsRegistry.SPANISH_TORTILLA.get())
            .pattern("PEP")
            .pattern("EGE")
            .pattern("PTP")
            .define('P', ModItemsRegistry.SLICED_POTATO.get())
            .define('E', Items.EGG)
            .define('G', ModItemsRegistry.SLICED_ONION.get())
            .define('T', Items.TORCHFLOWER) // Cambia
            .unlockedBy("has_potato", has(ModItemsRegistry.SLICED_POTATO.get()))
            .save(out, rl("spanish_tortilla_shaped"));
}

private ResourceLocation rl(String path) {
    return ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, path);
}
}

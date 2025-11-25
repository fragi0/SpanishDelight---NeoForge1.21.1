package com.devjulen.spanishdelight.data;

import java.util.concurrent.CompletableFuture;

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
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookup) {
        super(out, lookup);
    }

    @Override
    protected void buildRecipes(RecipeOutput out) {
        // ========================================================================
        // RECETAS DE CUTTING BOARD (Tabla de cortar)
        // ========================================================================
        
        // FIX: Usamos el tag directo de Farmer's Delight ya que c:tools/knives estaba vacío.
        TagKey<Item> knives = ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));

        // Patata -> 3x Rodajas
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(Items.POTATO),
                Ingredient.of(knives), 
                ModItemsRegistry.SLICED_POTATO.get(), 3)
                .build(out, rl("cutting/potato_to_sliced").toString());

        // Cebolla -> 3x Rodajas
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(commonTag("crops/onion")),
                Ingredient.of(knives),
                ModItemsRegistry.SLICED_ONION.get(), 3)
                .build(out, rl("cutting/onion_to_sliced").toString());

        // Pimiento Rojo -> 3x Pimentón
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(ModItemsRegistry.RED_PEPPER.get()),
                Ingredient.of(knives),
                ModItemsRegistry.PAPRIKA.get(), 3)
                .build(out, rl("cutting/red_pepper_to_paprika").toString());


        // ========================================================================
        // RECETAS DE COOKING POT (Olla)
        // ========================================================================
        // Nota: .toString() añadido porque el builder de esta versión pide String, no ResourceLocation.

        // Tortilla de Patatas
        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsRegistry.SPANISH_TORTILLA.get(), 1, 100, 1.0f)
                .addIngredient(ModItemsRegistry.SLICED_POTATO.get())
                .addIngredient(ModItemsRegistry.SLICED_ONION.get())
                .addIngredient(Items.EGG)
                .addIngredient(Items.EGG) 
                .unlockedBy("has_sliced_potato", has(ModItemsRegistry.SLICED_POTATO.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(out, rl("cooking/spanish_tortilla").toString());

        // Paella
        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsRegistry.PAELLA.get(), 1, 400, 2.0f, Items.BOWL)
                .addIngredient(commonTag("crops/rice")) 
                .addIngredient(Items.RABBIT)            
                .addIngredient(ModItemsRegistry.GREEN_BEAN.get()) 
                .addIngredient(ModItemsRegistry.PAPRIKA.get())
                .addIngredient(ModItemsRegistry.GARLIC.get())
                .unlockedBy("has_paella_ingredients", has(ModItemsRegistry.GREEN_BEAN.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(out, rl("cooking/paella").toString());

        // Bacalao al Pil Pil
        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsRegistry.PIL_PIL_COD.get(), 1, 400, 2.0f, Items.BOWL)
                .addIngredient(Items.COD)
                .addIngredient(ModItemsRegistry.GARLIC.get())
                .addIngredient(ModItemsRegistry.GARLIC.get()) 
                .addIngredient(ModItemsRegistry.SLICED_POTATO.get()) 
                .addIngredient(ModItemsRegistry.GREEN_PEPPER.get())
                .unlockedBy("has_cod", has(Items.COD))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(out, rl("cooking/pil_pil_cod").toString());

        // Croquetas
        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsRegistry.CROQUETTES.get(), 2, 200, 1.0f)
                .addIngredient(Items.POTATO)
                .addIngredient(Items.MILK_BUCKET)
                .addIngredient(Items.COOKED_CHICKEN) 
                .addIngredient(Items.EGG)
                .addIngredient(Items.BREAD)
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(out, rl("cooking/croquettes").toString());

        // Patatas Bravas
        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsRegistry.BRAVA_POTATOES.get(), 1, 200, 1.0f, Items.BOWL)
                .addIngredient(ModItemsRegistry.SLICED_POTATO.get())
                .addIngredient(ModItemsRegistry.SLICED_POTATO.get())
                .addIngredient(ModItemsRegistry.PAPRIKA.get())
                .unlockedBy("has_paprika", has(ModItemsRegistry.PAPRIKA.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .build(out, rl("cooking/brava_potatoes").toString());

        // Churros
        CookingPotRecipeBuilder.cookingPotRecipe(ModItemsRegistry.CHURRO.get(), 2, 200, 1.0f)
                .addIngredient(Items.WHEAT)
                .addIngredient(Items.SUGAR)
                .addIngredient(Items.EGG)
                .addIngredient(Items.WATER_BUCKET)
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS) 
                .build(out, rl("cooking/churros").toString());

        // ========================================================================
        // RECETAS VANILLA / HORNO (Standard)
        // ========================================================================

        // Calamar a la Romana (Horno / Smoker)
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(ModItemsRegistry.SQUID_RING.get()),
                RecipeCategory.FOOD, ModItemsRegistry.FRIED_SQUID_RING.get(), 0.35f, 200)
                .unlockedBy("has_raw_squid_ring", has(ModItemsRegistry.SQUID_RING.get()))
                .save(out, rl("smelting/fried_squid_ring"));
        
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(ModItemsRegistry.SQUID_RING.get()),
                RecipeCategory.FOOD, ModItemsRegistry.FRIED_SQUID_RING.get(), 0.35f, 100)
                .unlockedBy("has_raw_squid_ring", has(ModItemsRegistry.SQUID_RING.get()))
                .save(out, rl("smoking/fried_squid_ring"));

        // Pantumaca (Shapeless normal, es frío)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsRegistry.PANTUMACA.get())
                .requires(Items.BREAD)
                .requires(commonTag("crops/tomato"))
                .requires(ModItemsRegistry.GARLIC.get())
                .unlockedBy("has_tomato", has(commonTag("crops/tomato")))
                .save(out, rl("crafting/pantumaca"));

        // Gazpacho (Shapeless normal, es sopa fría)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItemsRegistry.GAZPACHO.get())
                .requires(commonTag("crops/tomato"))
                .requires(ModItemsRegistry.GREEN_PEPPER.get())
                .requires(ModItemsRegistry.GARLIC.get())
                .requires(Items.BREAD)
                .requires(Items.BOWL)
                .unlockedBy("has_tomato", has(commonTag("crops/tomato")))
                .save(out, rl("crafting/gazpacho"));
    }

    // Helper para ResourceLocation
    private ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, path);
    }
    
    // Helper para tags comunes "c:" (NeoForge standard)
    private TagKey<Item> commonTag(String path) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
    }
}

package com.devjulen.spanishdelight.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class SDFoodValues {
    // Durations (in ticks)
    public static final int BRIEF_DURATION = 600;    // 30s
    public static final int SHORT_DURATION = 1200;   // 1m
    public static final int MEDIUM_DURATION = 3600;  // 3m
    public static final int LONG_DURATION = 6000;    // 5m

    // Basic Ingredients
    public static final FoodProperties SLICED_POTATO = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.3f).fast().build();
    public static final FoodProperties SLICED_ONION = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.4f).fast().build();
    public static final FoodProperties GARLIC = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.2f).fast().build();
    public static final FoodProperties GREEN_BEAN = new FoodProperties.Builder()
            .nutrition(1).saturationModifier(0.2f).fast().build();
    public static final FoodProperties RED_PEPPER = new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.3f).build();
    public static final FoodProperties GREEN_PEPPER = new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.3f).build();
    
    // Missing Ingredient (Raw Squid Ring)
    public static final FoodProperties SQUID_RING = new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.3f).fast().build();

    // Platos (Dishes)
    // NOTE: Removed .value() from ModEffects calls to fix NeoForge 1.21.1 Holder<MobEffect> error.

    public static final FoodProperties CROQUETTES = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0), 1.0F).build();

    public static final FoodProperties BRAVA_POTATOES = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0), 1.0F).build();

    public static final FoodProperties CHURRO = new FoodProperties.Builder()
            .nutrition(5).saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, SHORT_DURATION, 0), 1.0F).build();

    public static final FoodProperties GAZPACHO = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.6f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0), 1.0F).build();

    public static final FoodProperties PANTUMACA = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0), 1.0F).build();

    public static final FoodProperties FRIED_SQUID_RING = new FoodProperties.Builder()
            .nutrition(6).saturationModifier(0.5f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, SHORT_DURATION, 0), 1.0F).build();

    // New/Missing Dishes
    public static final FoodProperties SPANISH_TORTILLA = new FoodProperties.Builder()
            .nutrition(9).saturationModifier(0.8f) // High nutrition for a full meal
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, LONG_DURATION, 0), 1.0F).build();

    public static final FoodProperties PAELLA = new FoodProperties.Builder()
            .nutrition(12).saturationModifier(0.9f) // Feast level stats
            .effect(() -> new MobEffectInstance(ModEffects.NOURISHMENT, LONG_DURATION, 0), 1.0F)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, LONG_DURATION, 0), 1.0F).build();

    public static final FoodProperties PIL_PIL_COD = new FoodProperties.Builder()
            .nutrition(8).saturationModifier(0.7f)
            .effect(() -> new MobEffectInstance(ModEffects.COMFORT, MEDIUM_DURATION, 0), 1.0F).build();
}

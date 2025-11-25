package com.devjulen.spanishdelight.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {
    public static final ModConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        // --- Worldgen ---
        public final ModConfigSpec.IntValue WILD_GARLIC_RARITY;
        public final ModConfigSpec.IntValue WILD_PEPPER_RARITY;

        // --- Gameplay / Drops ---
        public final ModConfigSpec.BooleanValue GREEN_BEAN_EXTRA_DROPS;
        public final ModConfigSpec.DoubleValue GREEN_BEAN_EXTRA_CHANCE;

        // --- Balance / XP ---
        public final ModConfigSpec.DoubleValue RECIPE_XP_MULTIPLIER;

        public Common(ModConfigSpec.Builder builder) {
            builder.comment("Configuración General de Spanish Delight").push("general");

            builder.comment("--- Generación de Mundo ---").push("worldgen");
            
            WILD_GARLIC_RARITY = builder
                    .comment("Rareza media (en chunks) para generar parches de Ajo Silvestre.",
                             "Valor más alto = Menos frecuente. Default: 12")
                    .defineInRange("wildGarlicRarity", 12, 1, 1000);

            WILD_PEPPER_RARITY = builder
                    .comment("Rareza media (en chunks) para generar parches de Pimientos (Rojo/Verde).",
                             "Valor más alto = Menos frecuente. Default: 14")
                    .defineInRange("wildPepperRarity", 14, 1, 1000);
            
            builder.pop();

            builder.comment("--- Gameplay y Drops ---").push("gameplay");

            GREEN_BEAN_EXTRA_DROPS = builder
                    .comment("Si es TRUE, las judías verdes tendrán probabilidad de soltar items extra al cosechar.")
                    .define("greenBeanExtraDrops", true);

            GREEN_BEAN_EXTRA_CHANCE = builder
                    .comment("Probabilidad (0.0 a 1.0) de drop extra si la opción anterior está activada.")
                    .defineInRange("greenBeanExtraChance", 0.1, 0.0, 1.0);

            RECIPE_XP_MULTIPLIER = builder
                    .comment("Multiplicador global para la experiencia ganada cocinando (Solo referencia futura/loot modifiers).")
                    .defineInRange("recipeXpMultiplier", 1.0, 0.0, 10.0);

            builder.pop();
            builder.pop();
        }
    }
}

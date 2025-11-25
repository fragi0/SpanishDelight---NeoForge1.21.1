package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementsGenerator()));
    }

    public static class ModAdvancementsGenerator implements AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            
            // ROOTE: Spanish Delight
            // Icono: Paella
            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ModItemsRegistry.PAELLA.get(),
                            Component.translatable("advancements.spanishdelight.root.title"),
                            Component.translatable("advancements.spanishdelight.root.desc"),
                            ResourceLocation.withDefaultNamespace("textures/block/bricks.png"), // Fondo
                            AdvancementType.TASK,
                            true, // Show toast
                            true, // Show in chat
                            false // Hidden
                    )
                    .addCriterion("has_paella", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.PAELLA.get()))
                    .addCriterion("has_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.RED_PEPPER.get()))
                    .save(saver, rl("root"));

            // 1. Cosecha Propia (Al obtener cualquiera de los cultivos)
            AdvancementHolder cosechaPropia = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItemsRegistry.GREEN_BEAN.get(),
                            Component.literal("Cosecha Propia"), // Puedes usar translatable si prefieres
                            Component.literal("Recoge tus primeros pimientos, ajos o judías de la huerta."),
                            null,
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("has_garlic", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.GARLIC.get()))
                    .addCriterion("has_red_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.RED_PEPPER.get()))
                    .addCriterion("has_green_pepper", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.GREEN_PEPPER.get()))
                    .save(saver, rl("cosecha_propia"));

            // 2. Españolito de Nacimiento (Tortilla)
            AdvancementHolder espanolito = Advancement.Builder.advancement()
                    .parent(cosechaPropia)
                    .display(
                            ModItemsRegistry.SPANISH_TORTILLA.get(),
                            Component.literal("Españolito de Nacimiento"),
                            Component.literal("Cocina una auténtica Tortilla de Patatas."),
                            null,
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("has_tortilla", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.SPANISH_TORTILLA.get()))
                    .save(saver, rl("espanolito_nacimiento"));

            // 3. Visca Catalunya (Pantumaca)
            AdvancementHolder catalunya = Advancement.Builder.advancement()
                    .parent(cosechaPropia)
                    .display(
                            ModItemsRegistry.PANTUMACA.get(),
                            Component.literal("Visca Catalunya"),
                            Component.literal("Prepara un Pantumaca. ¡Visca la terra!"),
                            null,
                            AdvancementType.TASK,
                            true, true, false
                    )
                    .addCriterion("has_pantumaca", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.PANTUMACA.get()))
                    .save(saver, rl("visca_catalunya"));
            
            // 4. Tapas Variadas (Croquetas o Bravas)
            AdvancementHolder tapas = Advancement.Builder.advancement()
                    .parent(espanolito)
                    .display(
                            ModItemsRegistry.CROQUETTES.get(),
                            Component.literal("De Tapeo"),
                            Component.literal("Prepara unas Croquetas o unas Patatas Bravas."),
                            null,
                            AdvancementType.GOAL, 
                            true, true, false
                    )
                    .addCriterion("has_croquettes", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.CROQUETTES.get()))
                    .addCriterion("has_bravas", InventoryChangeTrigger.TriggerInstance.hasItems(ModItemsRegistry.BRAVA_POTATOES.get()))
                    .save(saver, rl("de_tapeo"));
        }

        private static String rl(String path) {
            return SpanishDelight.MOD_ID + ":" + path;
        }
    }
}

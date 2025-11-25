package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.loot.AddItemModifier;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import com.devjulen.spanishdelight.common.registry.ModLootModifiers;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.LootContext;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, SpanishDelight.MOD_ID);
    }

    @Override
    protected void start() {
        // Añadir Squid Ring al loot del Calamar (minecraft:squid)
        add("squid_ring_from_squid", new AddItemModifier(
                new LootItemCondition[]{
                        // FIX: Usamos EntityPredicate para filtrar por EntityType.SQUID
                        LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().of(EntityType.SQUID)
                        ).build()
                },
                ModItemsRegistry.SQUID_RING.get()
        ));
    }
}

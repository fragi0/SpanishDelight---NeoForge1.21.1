package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {

    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, Set.of(), List.of(new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)), lookup);
    }

    static class Blocks extends BlockLootSubProvider {

        protected Blocks(HolderLookup.Provider provider) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        protected void generate() {
            var crop = ModBlocksRegistry.GREEN_BEAN_CROP.get();
            var mature = LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(crop)
                    .setProperties(StatePropertiesPredicate.Builder.properties()
                            .hasProperty(CropBlock.AGE, 5));

            LootPool.Builder base = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItemsRegistry.GREEN_BEAN.get()));

            LootPool.Builder extra = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(ModItemsRegistry.GREEN_BEAN.get())
                            .apply(net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
                                    .setCount(UniformGenerator.between(1.0F, 2.0F))))
                    .when(mature);

            add(crop, LootTable.lootTable().withPool(base).withPool(extra));

            wild(ModBlocksRegistry.WILD_GARLIC.get(), ModItemsRegistry.GARLIC.get());
            wild(ModBlocksRegistry.WILD_RED_PEPPER.get(), ModItemsRegistry.RED_PEPPER.get());
            wild(ModBlocksRegistry.WILD_GREEN_PEPPER.get(), ModItemsRegistry.GREEN_PEPPER.get());
        }

        private void wild(Block plant, net.minecraft.world.item.Item item) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1.0F))
                    .add(LootItem.lootTableItem(item)
                            .apply(net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
                                    .setCount(UniformGenerator.between(1.0F, 3.0F))));
            add(plant, LootTable.lootTable().withPool(pool));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocksRegistry.BLOCKS.getEntries().stream()
                    .map(DeferredHolder::get)
                    .collect(Collectors.toList());
        }
    }
}

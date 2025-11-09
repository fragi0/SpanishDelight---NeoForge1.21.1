package com.devjulen.spanishdelight.data.loot;

import com.devjulen.spanishdelight.common.block.GreenBeanCropBlock;
import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;

public class SDBlockLootTables extends BlockLootSubProvider {

    public SDBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        LootItemCondition.Builder matureCondition = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocksRegistry.GREEN_BEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(GreenBeanCropBlock.AGE, GreenBeanCropBlock.MAX_AGE));

        LootTable greenBeanLoot = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(LootItem.lootTableItem(ModItemsRegistry.GREEN_BEAN.get()))
                )
                .withPool(
                        LootPool.lootPool()
                                .when(matureCondition)
                                .add(LootItem.lootTableItem(ModItemsRegistry.GREEN_BEAN.get())
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(
                                                Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                )
                );

        this.add(ModBlocksRegistry.GREEN_BEAN_CROP.get(),
                this.applyExplosionDecay(ModBlocksRegistry.GREEN_BEAN_CROP.get(), greenBeanLoot)
        );

        wildSimple(ModBlocksRegistry.WILD_GARLIC.get(), ModItemsRegistry.GARLIC.get());
        wildSimple(ModBlocksRegistry.WILD_RED_PEPPER.get(), ModItemsRegistry.RED_PEPPER.get());
        wildSimple(ModBlocksRegistry.WILD_GREEN_PEPPER.get(), ModItemsRegistry.GREEN_PEPPER.get());
    }

    private void wildSimple(Block block, Item item) {
        LootTable table = LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .add(LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                                )
                );
        this.add(block, this.applyExplosionDecay(block, table));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocksRegistry.BLOCKS.getEntries().stream().map(DeferredHolder::get)::iterator;
    }
}

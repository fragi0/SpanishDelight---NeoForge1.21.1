package com.devjulen.spanishdelight.common.registry;

import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.devjulen.spanishdelight.common.registry.ModCreativeTabs.addToTab;

public class ModItemsRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, SpanishDelight.MOD_ID);

    public static final DeferredHolder<Item, Item> PAPRIKA =
            addToTab(ITEMS.register("paprika", () -> new Item(new Item.Properties())));

    public static final DeferredHolder<Item, Item> TEST_BLOCK_ITEM =
            addToTab(ITEMS.register("test_block", () -> new BlockItem(ModBlocksRegistry.TEST_BLOCK.get(), new Item.Properties())));

    public static void register(IEventBus bus) { ITEMS.register(bus); }
}

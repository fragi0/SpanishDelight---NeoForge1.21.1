package com.devjulen.spanishdelight.common.registry;

import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpanishDelight.MOD_ID);

    private static final List<Supplier<? extends ItemLike>> ENTRIES = new ArrayList<>();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB =
            TABS.register("main_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("tab.spanishdelight"))
                    .icon(ModItemsRegistry.PAPRIKA.get()::getDefaultInstance)
                    .displayItems((params, output) -> ENTRIES.forEach(s -> output.accept(s.get())))
                    .build());

    public static void register(IEventBus bus) { TABS.register(bus); }

    public static <T extends Item> DeferredHolder<Item, T> addToTab(DeferredHolder<Item, T> holder) {
        ENTRIES.add(holder);
        return holder;
    }
}

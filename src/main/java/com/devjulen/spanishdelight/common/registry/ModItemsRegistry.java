package com.devjulen.spanishdelight.common.registry;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.SDFoodValues;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.devjulen.spanishdelight.common.registry.ModCreativeTabs.addToTab;

public class ModItemsRegistry {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, SpanishDelight.MOD_ID);

    // Ingredientes
    public static final DeferredHolder<Item, Item> PAPRIKA =
            addToTab(ITEMS.register("paprika", () -> new Item(new Item.Properties())));

    public static final DeferredHolder<Item, Item> SLICED_POTATO =
            addToTab(ITEMS.register("sliced_potato", () -> new Item(new Item.Properties().food(SDFoodValues.SLICED_POTATO))));
    public static final DeferredHolder<Item, Item> SLICED_ONION =
            addToTab(ITEMS.register("sliced_onion", () -> new Item(new Item.Properties().food(SDFoodValues.SLICED_ONION))));
    public static final DeferredHolder<Item, Item> GARLIC =
            addToTab(ITEMS.register("garlic", () -> new Item(new Item.Properties().food(SDFoodValues.GARLIC))));

    // Semilla + alimento del cultivo
    public static final DeferredHolder<Item, Item> GREEN_BEAN =
            addToTab(ITEMS.register("green_bean", () ->
                    new ItemNameBlockItem(ModBlocksRegistry.GREEN_BEAN_CROP.get(),
                            new Item.Properties().food(SDFoodValues.GREEN_BEAN))));

    public static final DeferredHolder<Item, Item> RED_PEPPER =
            addToTab(ITEMS.register("red_pepper", () -> new Item(new Item.Properties().food(SDFoodValues.RED_PEPPER))));
    public static final DeferredHolder<Item, Item> GREEN_PEPPER =
            addToTab(ITEMS.register("green_pepper", () -> new Item(new Item.Properties().food(SDFoodValues.GREEN_PEPPER))));
    public static final DeferredHolder<Item, Item> SQUID_RING =
            addToTab(ITEMS.register("squid_ring", () -> new Item(new Item.Properties().food(SDFoodValues.SQUID_RING))));
    public static final DeferredHolder<Item, Item> FRIED_SQUID_RING =
            addToTab(ITEMS.register("fried_squid_ring", () -> new Item(new Item.Properties().food(SDFoodValues.FRIED_SQUID_RING))));

    // Platos
    public static final DeferredHolder<Item, Item> SPANISH_TORTILLA =
            addToTab(ITEMS.register("spanish_tortilla", () -> new Item(new Item.Properties().food(SDFoodValues.SPANISH_TORTILLA))));
    public static final DeferredHolder<Item, Item> PAELLA =
            addToTab(ITEMS.register("paella", () -> new Item(new Item.Properties().food(SDFoodValues.PAELLA))));
    public static final DeferredHolder<Item, Item> PIL_PIL_COD =
            addToTab(ITEMS.register("pil_pil_cod", () -> new Item(new Item.Properties().food(SDFoodValues.PIL_PIL_COD))));
    public static final DeferredHolder<Item, Item> CROQUETTES =
            addToTab(ITEMS.register("croquettes", () -> new Item(new Item.Properties().food(SDFoodValues.CROQUETTES))));
    public static final DeferredHolder<Item, Item> BRAVA_POTATOES =
            addToTab(ITEMS.register("brava_potatoes", () -> new Item(new Item.Properties().food(SDFoodValues.BRAVA_POTATOES))));
    public static final DeferredHolder<Item, Item> CHURRO =
            addToTab(ITEMS.register("churro", () -> new Item(new Item.Properties().food(SDFoodValues.CHURRO))));
    public static final DeferredHolder<Item, Item> GAZPACHO =
            addToTab(ITEMS.register("gazpacho", () -> new Item(new Item.Properties().food(SDFoodValues.GAZPACHO))));
    public static final DeferredHolder<Item, Item> PANTUMACA =
            addToTab(ITEMS.register("pantumaca", () -> new Item(new Item.Properties().food(SDFoodValues.PANTUMACA))));

    // BlockItems silvestres (aparecerán después en worldgen)
    public static final DeferredHolder<Item, Item> WILD_GARLIC =
            addToTab(ITEMS.register("wild_garlic", () -> new BlockItem(ModBlocksRegistry.WILD_GARLIC.get(), new Item.Properties())));
    public static final DeferredHolder<Item, Item> WILD_RED_PEPPER =
            addToTab(ITEMS.register("wild_red_pepper", () -> new BlockItem(ModBlocksRegistry.WILD_RED_PEPPER.get(), new Item.Properties())));
    public static final DeferredHolder<Item, Item> WILD_GREEN_PEPPER =
            addToTab(ITEMS.register("wild_green_pepper", () -> new BlockItem(ModBlocksRegistry.WILD_GREEN_PEPPER.get(), new Item.Properties())));

    // Bloque de prueba (opcional quitarlo luego)
    public static final DeferredHolder<Item, Item> TEST_BLOCK_ITEM =
            addToTab(ITEMS.register("test_block", () -> new BlockItem(ModBlocksRegistry.TEST_BLOCK.get(), new Item.Properties())));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}

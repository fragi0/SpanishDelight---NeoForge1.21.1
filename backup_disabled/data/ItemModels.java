package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ItemModels extends ItemModelProvider {
    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SpanishDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simple(ModItemsRegistry.PAPRIKA);
        simple(ModItemsRegistry.SLICED_POTATO);
        simple(ModItemsRegistry.SLICED_ONION);
        simple(ModItemsRegistry.GARLIC);
        simple(ModItemsRegistry.GREEN_BEAN);
        simple(ModItemsRegistry.RED_PEPPER);
        simple(ModItemsRegistry.GREEN_PEPPER);
        simple(ModItemsRegistry.SQUID_RING);
        simple(ModItemsRegistry.SPANISH_TORTILLA);
        simple(ModItemsRegistry.PAELLA);
        simple(ModItemsRegistry.PIL_PIL_COD);
        simple(ModItemsRegistry.CROQUETTES);
        simple(ModItemsRegistry.BRAVA_POTATOES);
        simple(ModItemsRegistry.CHURRO);
        simple(ModItemsRegistry.GAZPACHO);
        simple(ModItemsRegistry.PANTUMACA);
        simple(ModItemsRegistry.FRIED_SQUID_RING);

        blockItem(ModBlocksRegistry.WILD_GARLIC);
        blockItem(ModBlocksRegistry.WILD_RED_PEPPER);
        blockItem(ModBlocksRegistry.WILD_GREEN_PEPPER);
    }

    private ItemModelBuilder simple(DeferredHolder<Item, ? extends Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(SpanishDelight.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder blockItem(DeferredHolder<Block, ? extends Block> block) {
        return withExistingParent(block.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(SpanishDelight.MOD_ID, "block/" + block.getId().getPath()));
    }
}

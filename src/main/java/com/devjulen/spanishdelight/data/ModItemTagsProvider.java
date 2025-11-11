package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookup,
                               ExistingFileHelper helper) {
        super(output, lookup, CompletableFuture.completedFuture(TagsProvider.TagLookup.empty()), 
              SpanishDelight.MOD_ID, helper);
    }

    public ModItemTagsProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookup,
                               CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
                               ExistingFileHelper helper) {
        super(output, lookup, blockTags, SpanishDelight.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        TagKey<net.minecraft.world.item.Item> WATER_INGREDIENT =
                TagKey.create(Registries.ITEM,
                        ResourceLocation.fromNamespaceAndPath(SpanishDelight.MOD_ID, "water_ingredient"));

        tag(WATER_INGREDIENT).add(Items.WATER_BUCKET);
    }
}

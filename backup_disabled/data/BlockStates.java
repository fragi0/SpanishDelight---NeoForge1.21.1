package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.block.GreenBeanCropBlock;
import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {
    public BlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SpanishDelight.MOD_ID, exFileHelper);
    }

    private String blockName(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    private ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(SpanishDelight.MOD_ID, "block/" + path);
    }

    @Override
    protected void registerStatesAndModels() {
        makeGreenBeanCrop((CropBlock) ModBlocksRegistry.GREEN_BEAN_CROP.get(), "green_bean_stage", "green_bean_stage");
        wildCropBlock(ModBlocksRegistry.WILD_GARLIC.get());
        wildCropBlock(ModBlocksRegistry.WILD_GREEN_PEPPER.get());
        wildCropBlock(ModBlocksRegistry.WILD_RED_PEPPER.get());
    }

    private void wildCropBlock(Block block) {
        simpleBlock(block, models().cross(blockName(block), resourceBlock(blockName(block))).renderType("cutout"));
    }

    private void makeGreenBeanCrop(CropBlock block, String modelName, String textureName) {
        getVariantBuilder(block).forAllStates(state -> {
            int age = state.getValue(((GreenBeanCropBlock) block).getAgeProperty());
            return new ConfiguredModel[]{
                    new ConfiguredModel(models().crop(modelName + age,
                            new ResourceLocation(SpanishDelight.MOD_ID, "block/" + textureName + age))
                            .renderType("cutout"))
            };
        });
    }
}

package com.devjulen.spanishdelight.data;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.registry.ModBlocksRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existing) {
        super(output, SpanishDelight.MOD_ID, existing);
    }

    @Override
    protected void registerStatesAndModels() {
        // Cultivo: modelos por etapa 0..5
        for (int age = 0; age <= 5; age++) {
            String stage = "green_bean_stage" + age;
            models().crop(stage, modLoc("block/" + stage))
                    .texture("layer0", modLoc("block/" + stage))
                    .renderType("minecraft:cutout");
        }

        // Blockstate con 0..5 (6/7 usan la última)
        getVariantBuilder(ModBlocksRegistry.GREEN_BEAN_CROP.get()).forAllStates(state -> {
            int age = state.getValue(net.minecraft.world.level.block.CropBlock.AGE);
            if (age > 5) age = 5;
            ModelFile mf = models().getExistingFile(modLoc("green_bean_stage" + age));
            return ConfiguredModel.builder().modelFile(mf).build();
        });

        // Silvestres cross
        crossPlant("wild_garlic");
        crossPlant("wild_red_pepper");
        crossPlant("wild_green_pepper");
    }

    private void crossPlant(String name) {
        BlockModelBuilder model = models().cross(name, modLoc("block/" + name))
                .renderType("minecraft:cutout");
        getVariantBuilder(
                switch (name) {
                    case "wild_garlic" -> ModBlocksRegistry.WILD_GARLIC.get();
                    case "wild_red_pepper" -> ModBlocksRegistry.WILD_RED_PEPPER.get();
                    default -> ModBlocksRegistry.WILD_GREEN_PEPPER.get();
                }
        ).partialState().addModels(new ConfiguredModel(new ModelFile.UncheckedModelFile(modLoc("block/" + name))));
    }
}

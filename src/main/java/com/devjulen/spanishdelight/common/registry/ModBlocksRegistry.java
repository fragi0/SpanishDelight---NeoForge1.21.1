package com.devjulen.spanishdelight.common.registry;

import com.devjulen.spanishdelight.SpanishDelight;
import com.devjulen.spanishdelight.common.block.GreenBeanCropBlock;
import com.devjulen.spanishdelight.common.block.WildSimpleCropBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocksRegistry {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, SpanishDelight.MOD_ID);

    // Cultivo
    public static final DeferredHolder<Block, Block> GREEN_BEAN_CROP =
            BLOCKS.register("green_bean_crop", () ->
                    new GreenBeanCropBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .noOcclusion()));

    // Plantas silvestres
    public static final DeferredHolder<Block, Block> WILD_GARLIC =
            BLOCKS.register("wild_garlic", () ->
                    new WildSimpleCropBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .noOcclusion()));

    public static final DeferredHolder<Block, Block> WILD_RED_PEPPER =
            BLOCKS.register("wild_red_pepper", () ->
                    new WildSimpleCropBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .noOcclusion()));

    public static final DeferredHolder<Block, Block> WILD_GREEN_PEPPER =
            BLOCKS.register("wild_green_pepper", () ->
                    new WildSimpleCropBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .noOcclusion()));

    // Bloque de prueba (puedes retirar luego)
    public static final DeferredHolder<Block, Block> TEST_BLOCK =
            BLOCKS.register("test_block", () ->
                    new Block(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.0F)));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}

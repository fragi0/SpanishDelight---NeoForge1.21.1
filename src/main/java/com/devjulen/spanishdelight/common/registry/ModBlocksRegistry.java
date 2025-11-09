package com.devjulen.spanishdelight.common.registry;

import com.devjulen.spanishdelight.SpanishDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, SpanishDelight.MOD_ID);

    public static final DeferredHolder<Block, Block> TEST_BLOCK =
            BLOCKS.register("test_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.0F)));

    public static void register(IEventBus bus) { BLOCKS.register(bus); }
}

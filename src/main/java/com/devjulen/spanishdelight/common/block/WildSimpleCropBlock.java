package com.devjulen.spanishdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Planta silvestre simple (decorativa / recolectable).
 */
public class WildSimpleCropBlock extends BushBlock {

    public static final MapCodec<WildSimpleCropBlock> CODEC = simpleCodec(WildSimpleCropBlock::new);

    private static final VoxelShape SHAPE = box(2, 0, 2, 14, 13, 14);

    public WildSimpleCropBlock(Properties props) {
        super(props);
    }

    @Override
    public MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPE;
    }
}

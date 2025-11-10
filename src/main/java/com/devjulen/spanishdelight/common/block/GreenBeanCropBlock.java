package com.devjulen.spanishdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Cultivo Green Bean con 6 etapas visibles (0-5).
 */
public class GreenBeanCropBlock extends CropBlock {

    public static final int MAX_AGE = 5;
    public static final MapCodec<GreenBeanCropBlock> CODEC = simpleCodec(GreenBeanCropBlock::new);

    private static final VoxelShape[] SHAPES = new VoxelShape[]{
        box(2, 0, 2, 14, 4, 14),
        box(2, 0, 2, 14, 6, 14),
        box(2, 0, 2, 14, 8, 14),
        box(2, 0, 2, 14,10, 14),
        box(2, 0, 2, 14,12, 14),
        box(2, 0, 2, 14,14, 14)
    };

    public GreenBeanCropBlock(Properties props) {
        super(props);
    }

    // <-- firma corregida: debe coincidir con la de CropBlock
    @Override
    public MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        int age = Math.min(getAge(state), MAX_AGE);
        return SHAPES[age];
    }
}

package com.devjulen.spanishdelight.common.block;

import com.devjulen.spanishdelight.common.registry.ModItemsRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Cultivo Green Bean (0-5).
 */
public class GreenBeanCropBlock extends CropBlock {

    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    // Requisito 1.21: codec para serialización (datagen / world saves)
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

    @Override
    public MapCodec<? extends CropBlock> codec() {
        // Nota: en algunas mappings puede ser MapCodec<? extends Block>. Si marca error, cambia la firma a
        // protected MapCodec<? extends Block> codec()
        return CODEC;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected Item getBaseSeedId() {
        return ModItemsRegistry.GREEN_BEAN.get();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPES[getAge(state)];
    }
}

package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.Qubasis;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public abstract class QBlockStateProvider extends BlockStateProvider {
    public QBlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected abstract void registerStatesAndModels();

    protected void horizontallyOrientedBlock(Block block, Function<BlockState, ModelFile> modelFunction) {
        getVariantBuilder(block)
                .forAllStates(blockState -> {
                    Direction direction = blockState.get(BlockStateProperties.HORIZONTAL_FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunction.apply(blockState))
                            .rotationX(direction.getAxis() == Direction.Axis.Y ? direction.getAxisDirection().getOffset() * -90 : 0)
                            .rotationY(direction.getAxis() != Direction.Axis.Y ? ((direction.getHorizontalIndex() + 2) % 4) * 90 : 0)
                            .build();
                });
    }

    protected void blockAll(Block block, String texturePath, String name) {
        getVariantBuilder(block).forAllStates(blockState ->
                ConfiguredModel.allRotations(models().cubeAll(name, new ResourceLocation(Qubasis.MODID, texturePath)), true));
    }
}
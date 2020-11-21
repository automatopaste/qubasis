package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.Qubasis;
import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends QBlockStateProvider{
    public BlockStates(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerChargingDock();
        blockAll(RegistryHandler.PERSIUM_ORE.get(), "block/persium_ore", "persium_ore");
        blockAll(RegistryHandler.PERSIUM_BLOCK.get(), "block/persium_block", "persium_block");
    }

    private void registerChargingDock() {
        ResourceLocation topTexture = new ResourceLocation(Qubasis.MODID, "block/charging_dock_top");
        ResourceLocation bottomTexture = new ResourceLocation(Qubasis.MODID, "block/charging_dock_bottom");
        ResourceLocation frontTexture = new ResourceLocation(Qubasis.MODID, "block/charging_dock_front");
        ResourceLocation sideTexture = new ResourceLocation(Qubasis.MODID, "block/charging_dock_side");

        BlockModelBuilder chargingDockModel = models()
                .cube("charging_dock", bottomTexture, topTexture, frontTexture, sideTexture, sideTexture, sideTexture)
                .texture("particle", sideTexture);
        horizontallyOrientedBlock(RegistryHandler.CHARGING_DOCK.get(), blockState -> {
            return chargingDockModel;
        });
    }
}
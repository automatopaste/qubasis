package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.Qubasis;
import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends QItemModelProvider {
    public ItemModels(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(RegistryHandler.PERSIUM_INGOT.get().getRegistryName().getPath(), new ResourceLocation("item/generated"),
                "layer0", new ResourceLocation(Qubasis.MODID, "item/persium_ingot"));
        singleTexture(RegistryHandler.PERSIUM.get().getRegistryName().getPath(), new ResourceLocation("item/generated"),
                "layer0", new ResourceLocation(Qubasis.MODID, "item/persium"));
        cubeAll(RegistryHandler.PERSIUM_BLOCK_ITEM.get().getRegistryName().getPath(), new ResourceLocation(Qubasis.MODID, "block/persium_block"));
        cubeAll(RegistryHandler.PERSIUM_ORE_ITEM.get().getRegistryName().getPath(), new ResourceLocation(Qubasis.MODID, "block/persium_ore"));
        withExistingParent(RegistryHandler.CHARGING_DOCK_ITEM.get().getRegistryName().getPath(), new ResourceLocation(Qubasis.MODID, "block/charging_dock"));
    }
}

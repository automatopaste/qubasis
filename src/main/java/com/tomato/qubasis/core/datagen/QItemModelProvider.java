package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.Qubasis;
import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class QItemModelProvider extends ItemModelProvider {
    public QItemModelProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
        super(generator, modid, existingFileHelper);
    }

    @Override
    protected abstract void registerModels();
}

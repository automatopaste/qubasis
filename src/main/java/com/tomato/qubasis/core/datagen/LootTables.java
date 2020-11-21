package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.data.DataGenerator;

public class LootTables extends QLootTableProvider {
    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(
                RegistryHandler.PERSIUM_ORE.get(),
                createSilkTouchTable(null, RegistryHandler.PERSIUM_ORE.get(), RegistryHandler.PERSIUM.get(), 2, 5, 2)
        );
        lootTables.put(
                RegistryHandler.CHARGING_DOCK.get(),
                createStandardTable("charging_dock", RegistryHandler.CHARGING_DOCK.get())
        );
    }
}

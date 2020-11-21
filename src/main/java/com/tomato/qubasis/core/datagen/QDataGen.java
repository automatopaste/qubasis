package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.Qubasis;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Qubasis.MODID)
public class QDataGen {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {
        if (event.includeServer()) addServerProviders(event);
        if (event.includeClient()) addClientProviders(event);
        Qubasis.LOGGER.info("bruh");
    }

    private static void addServerProviders(final GatherDataEvent event) {
        final DataGenerator gen = event.getGenerator();
        gen.addProvider(new LootTables(gen));
        gen.addProvider(new Recipes(gen));
    }

    private static void addClientProviders(final GatherDataEvent event) {
        final DataGenerator gen = event.getGenerator();
        final ExistingFileHelper exFileHelper = event.getExistingFileHelper();
        gen.addProvider(new BlockStates(gen, Qubasis.MODID, exFileHelper));
        gen.addProvider(new ItemModels(gen, Qubasis.MODID, exFileHelper));
    }
}

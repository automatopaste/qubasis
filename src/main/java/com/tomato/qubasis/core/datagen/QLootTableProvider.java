package com.tomato.qubasis.core.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.loot.functions.*;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class QLootTableProvider extends LootTableProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator generator;
    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

    public QLootTableProvider(final DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
        this.generator = dataGeneratorIn;
    }

    protected abstract void addTables();

    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block)
                        .acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
                        .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                .addOperation("inv", "BlockEntityTag.inv", CopyNbt.Action.REPLACE)
                                .addOperation("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
                        .acceptFunction(SetContents.func_215920_b().func_216075_a(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents"))))
                );
        return LootTable.builder().addLootPool(builder);
    }

    protected LootTable.Builder createSilkTouchTable(final String name, final Block block, final Item lootItem, final float min, final float max, final int bonus) {
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(AlternativesLootEntry.builder(
                        ItemLootEntry.builder(block).acceptCondition(
                                MatchTool.builder(ItemPredicate.Builder.create().enchantment(
                                        new EnchantmentPredicate(
                                                Enchantments.SILK_TOUCH,
                                                MinMaxBounds.IntBound.atLeast(1)
                                        )
                                ))
                        ),
                        ItemLootEntry.builder(lootItem)
                                .acceptFunction(SetCount.builder(new RandomValueRange(min, max)))
                                .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, bonus))
                                .acceptFunction(ExplosionDecay.builder())
                        )
                );
        return LootTable.builder().addLootPool(builder);
    }

    protected LootTable.Builder createSilkTouchOreTable(final Block block, final Item lootItem) {
        final LootPool.Builder builder = LootPool.builder()
                .rolls(ConstantRange.of(1))
                .addEntry(AlternativesLootEntry.builder(
                        ItemLootEntry.builder(block).acceptCondition(
                                MatchTool.builder(ItemPredicate.Builder.create().enchantment(
                                        new EnchantmentPredicate(
                                                Enchantments.SILK_TOUCH,
                                                MinMaxBounds.IntBound.atLeast(1)
                                        )
                                ))
                        ),
                        ItemLootEntry.builder(lootItem)
                                .acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE))
                                .acceptFunction(ExplosionDecay.builder())
                ));
        return LootTable.builder().addLootPool(builder);
    }

    protected LootTable.Builder createEmptyTable() {
        return LootTable.builder();
    }

    @Override
    public void act(final DirectoryCache cache) {
        addTables();
        final Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (final Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    private void writeTables(final DirectoryCache cache, final Map<ResourceLocation, LootTable> tables) {
        final Path outputFolder = generator.getOutputFolder();
        final Path[] path = new Path[1];
        tables.forEach((key, lootTable) -> {
            path[0] = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path[0]);
            }
            catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}" + path[0], (Object)e);
            }
        });
    }

    @Override
    public String getName() {
        return "Qubasis Loot Tables";
    }
}
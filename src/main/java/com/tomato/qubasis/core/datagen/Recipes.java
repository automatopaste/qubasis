package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends QRecipeProvider{
    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        generateSmeltingAndBlastingRecipes(RegistryHandler.PERSIUM_ORE_ITEM.get(), RegistryHandler.PERSIUM_INGOT.get(), consumer, 10, "");

        ShapedRecipeBuilder.shapedRecipe(RegistryHandler.PERSIUM_BLOCK_ITEM.get())
                .patternLine("xxx")
                .patternLine("xxx")
                .patternLine("xxx")
                .key('x', RegistryHandler.PERSIUM_INGOT.get())
                .addCriterion("persium_ingot", InventoryChangeTrigger.Instance.forItems(RegistryHandler.PERSIUM_BLOCK.get()))
                .build(consumer);
    }
}

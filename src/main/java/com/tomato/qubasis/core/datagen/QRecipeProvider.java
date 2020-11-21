package com.tomato.qubasis.core.datagen;

import com.tomato.qubasis.core.Qubasis;
import com.tomato.qubasis.core.setup.RegistryHandler;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Arrays;
import java.util.function.Consumer;

public abstract class QRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public QRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected abstract void registerRecipes(Consumer<IFinishedRecipe> consumer);

    protected void generateSmeltingAndBlastingRecipes(final Item input, final Item output, Consumer<IFinishedRecipe> consumer, final float xp, final String folder) {
        CookingRecipeBuilder
                .smeltingRecipe(Ingredient.fromItems(input), output, xp, 200)
                .addCriterion("has_" + input.getRegistryName().getPath(), hasItem(input))
                .build(consumer, Qubasis.MODID + ":" + folder + "/" + output.getRegistryName().getPath() + "_from_smelting");
        CookingRecipeBuilder
                .blastingRecipe(Ingredient.fromItems(input), output, xp, 100)
                .addCriterion("has_" + input.getRegistryName().getPath(), hasItem(input))
                .build(consumer, Qubasis.MODID + ":" + folder + "/" + output.getRegistryName().getPath() + "_from_blasting");
    }
}

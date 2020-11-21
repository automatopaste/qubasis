package com.tomato.qubasis.core.setup;

import com.tomato.qubasis.core.Qubasis;
import com.tomato.qubasis.core.block.ChargingDockBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Qubasis.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHandler {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Qubasis.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Qubasis.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //blocks
    public static final RegistryObject<Block> PERSIUM_ORE = BLOCKS.register("persium_ore", () ->
            new Block(
                    AbstractBlock.Properties
                            .create(Material.IRON)
                            .hardnessAndResistance(6.0f, 8.0f)
                            .sound(SoundType.STONE)
                            .harvestLevel(2)
                            .harvestTool(ToolType.PICKAXE)
            )
    );
    public static final RegistryObject<Block> PERSIUM_BLOCK = BLOCKS.register("persium_block", () ->
            new Block(
                    AbstractBlock.Properties
                            .create(Material.IRON)
                            .hardnessAndResistance(4.0f, 6.0f)
                            .sound(SoundType.METAL)
                            .harvestLevel(1)
                            .harvestTool(ToolType.PICKAXE)
            )
    );
    public static final RegistryObject<Block> CHARGING_DOCK = BLOCKS.register("charging_dock", () ->
            new ChargingDockBlock(
                    AbstractBlock.Properties
                            .create(Material.ANVIL)
                            .hardnessAndResistance(2.0f, 4.0f)
                            .sound(SoundType.METAL)
                            .harvestLevel(0)
                            .harvestTool(ToolType.PICKAXE)
            )
    );

    //items
    public static final RegistryObject<Item> PERSIUM = ITEMS.register("persium", () ->
            new Item(
                    new Item.Properties().group(ItemGroup.MATERIALS)
            )
    );
    public static final RegistryObject<Item> PERSIUM_INGOT = ITEMS.register("persium_ingot", () ->
            new Item(
                    new Item.Properties().group(ItemGroup.MATERIALS)
            )
    );
    public static final RegistryObject<Item> PERSIUM_ORE_ITEM = ITEMS.register("persium_ore", () ->
            new BlockItem(
                    PERSIUM_ORE.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Item> PERSIUM_BLOCK_ITEM = ITEMS.register("persium_block", () ->
            new BlockItem(
                    PERSIUM_BLOCK.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Item> CHARGING_DOCK_ITEM = ITEMS.register("charging_dock", () ->
            new BlockItem(
                    CHARGING_DOCK.get(),
                    new Item.Properties().group(ItemGroup.DECORATIONS)
            )
    );
}

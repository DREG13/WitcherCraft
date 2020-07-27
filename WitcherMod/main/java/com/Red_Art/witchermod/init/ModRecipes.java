package com.Red_Art.witchermod.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes 
{
public static void init()
{
	GameRegistry.addSmelting(new ItemStack(ModBlocks.SILVER_ORE, 1), new ItemStack(ModItems.SILVER_INGOT, 1), 1.0f);
	GameRegistry.addSmelting(new ItemStack(ModBlocks.METEOR_IRON_ORE, 1), new ItemStack(ModItems.METEOR_IRON_INGOT, 1), 2.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.THINN_IRON_ROD, 1), new ItemStack(ModItems.MOLTEN_THINN_IRON_ROD, 1), 0.5f);
	GameRegistry.addSmelting(new ItemStack(ModItems.IRON_ROD, 1), new ItemStack(ModItems.MOLTEN_IRON_ROD, 1), 0.5f);
	GameRegistry.addSmelting(new ItemStack(ModItems.THICK_IRON_ROD, 1), new ItemStack(ModItems.MOLTEN_THICK_IRON_ROD, 1), 0.5f);
	GameRegistry.addSmelting(new ItemStack(ModItems.SILVER_ROD, 1), new ItemStack(ModItems.MOLTEN_SILVER_ROD, 1), 1.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.METEORITE_ROD, 1), new ItemStack(ModItems.MOLTEN_METEORITE_ROD, 1), 2.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.BLADE, 1), new ItemStack(ModItems.MOLTEN_BLADE, 1), 0.5f);
	GameRegistry.addSmelting(new ItemStack(ModItems.SHORT_BLADE, 1), new ItemStack(ModItems.MOLTEN_SHORT_BLADE, 1), 0.5f);
	GameRegistry.addSmelting(new ItemStack(ModItems.LONG_BLADE, 1), new ItemStack(ModItems.MOLTEN_LONG_BLADE, 1), 2.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.SILVER_BLADE, 1), new ItemStack(ModItems.MOLTEN_SILVER_BLADE, 1), 1.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.METEORITE_BLADE, 1), new ItemStack(ModItems.MOLTEN_METEORITE_BLADE, 1), 2.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.BUBBLED_BLADE, 1), new ItemStack(Items.IRON_INGOT, 3), 1.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.BUBBLED_SILVER_BLADE, 1), new ItemStack(ModItems.SILVER_INGOT, 3), 3.0f);
	GameRegistry.addSmelting(new ItemStack(ModItems.BUBBLED_METEORITE_BLADE, 1), new ItemStack(ModItems.METEOR_IRON_INGOT, 3), 6.0f);

}


}

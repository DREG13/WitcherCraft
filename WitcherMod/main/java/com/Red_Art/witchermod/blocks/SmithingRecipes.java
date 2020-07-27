package com.Red_Art.witchermod.blocks;
import java.util.Map;
import java.util.Map.Entry;

import com.Red_Art.witchermod.init.ModItems;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.item.ItemStack;

public class SmithingRecipes {	
	private static final SmithingRecipes INSTANCE = new SmithingRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SmithingRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private SmithingRecipes() 
	{
	
		addSmithingRecipe(new ItemStack(ModItems.MOLTEN_IRON_ROD), new ItemStack(ModItems.BLADE), 5.0F);
		addSmithingRecipe(new ItemStack(ModItems.MOLTEN_METEORITE_ROD), new ItemStack(ModItems.METEORITE_BLADE), 10.0F);
		addSmithingRecipe(new ItemStack(ModItems.MOLTEN_SILVER_ROD), new ItemStack(ModItems.SILVER_BLADE), 7.0F);
		addSmithingRecipe(new ItemStack(ModItems.MOLTEN_THINN_IRON_ROD), new ItemStack(ModItems.SHORT_BLADE), 5.0F);
		addSmithingRecipe(new ItemStack(ModItems.MOLTEN_THICK_IRON_ROD), new ItemStack(ModItems.LONG_BLADE), 5.0F);
	}

	
	public void addSmithingRecipe(ItemStack input, ItemStack result, float experience) 
	{
		if(getSmithingResult(input) != ItemStack.EMPTY) return;
		this.smeltingList.put(input , result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getSmithingResult(ItemStack stack) 
	 {
	  for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet())
	        {
	            if (this.compareItemStacks(stack, entry.getKey()))
	            {
	                return entry.getValue();
	            }
	        }

	        return ItemStack.EMPTY;
	 
		
	
}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Map<ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public float getSmithingExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}

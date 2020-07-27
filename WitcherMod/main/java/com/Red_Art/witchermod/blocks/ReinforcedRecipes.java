package com.Red_Art.witchermod.blocks;

import java.util.Map;
import java.util.Map.Entry;

import com.Red_Art.witchermod.init.ModItems;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

public class ReinforcedRecipes {
	private static final ReinforcedRecipes INSTANCE = new ReinforcedRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static ReinforcedRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private ReinforcedRecipes() 
	{
	
		addReinforcedRecipe(new ItemStack(ModItems.MOLTEN_BLADE), new ItemStack(ModItems.REINFORCED_BLADE), 5.0F);
		addReinforcedRecipe(new ItemStack(ModItems.MOLTEN_METEORITE_BLADE), new ItemStack(ModItems.REINFORCED_METEORITE_BLADE), 10.0F);
		addReinforcedRecipe(new ItemStack(ModItems.MOLTEN_SILVER_BLADE), new ItemStack(ModItems.REINFORCED_SILVER_BLADE), 7.0F);
		addReinforcedRecipe(new ItemStack(ModItems.MOLTEN_SHORT_BLADE), new ItemStack(ModItems.REINFORCED_SHORT_BLADE), 5.0F);
		addReinforcedRecipe(new ItemStack(ModItems.MOLTEN_LONG_BLADE), new ItemStack(ModItems.REINFORCED_LONG_BLADE), 5.0F);
	}

	
	public void addReinforcedRecipe(ItemStack input, ItemStack result, float experience) 
	{
		if(getReinforcingResult(input) != ItemStack.EMPTY) return;
		this.smeltingList.put(input , result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getReinforcingResult(ItemStack stack) 
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
	
	public float getReinforcingExperience(ItemStack stack)
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

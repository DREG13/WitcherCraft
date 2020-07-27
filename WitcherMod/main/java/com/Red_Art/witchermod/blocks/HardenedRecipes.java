package com.Red_Art.witchermod.blocks;

import java.util.Map;
import java.util.Map.Entry;

import com.Red_Art.witchermod.init.ModItems;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;

public class HardenedRecipes {
	private static final HardenedRecipes INSTANCE = new HardenedRecipes();
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static HardenedRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private HardenedRecipes() 
	{
	
		addHardenedRecipe(new ItemStack(ModItems.MOLTEN_BLADE), new ItemStack(ModItems.HARDENED_BLADE), 5.0F);
		addHardenedRecipe(new ItemStack(ModItems.MOLTEN_SHORT_BLADE), new ItemStack(ModItems.HARDENED_SHORT_BLADE), 5.0F);
		addHardenedRecipe(new ItemStack(ModItems.MOLTEN_LONG_BLADE), new ItemStack(ModItems.HARDENED_LONG_BLADE), 5.0F);
	}

	
	public void addHardenedRecipe(ItemStack input, ItemStack result, float experience) 
	{
		if(getHardeningResult(input) != ItemStack.EMPTY) return;
		this.smeltingList.put(input , result);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getHardeningResult(ItemStack stack) 
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
	
	public float getHardenedExperience(ItemStack stack)
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

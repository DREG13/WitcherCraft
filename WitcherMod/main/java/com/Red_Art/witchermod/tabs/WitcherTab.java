package com.Red_Art.witchermod.tabs;

import com.Red_Art.witchermod.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class WitcherTab extends CreativeTabs
{
	public WitcherTab(String label) {
		super("witchertab");
		this.setBackgroundImageName("witcher.png");}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.METEORITE_SWORD);
	}
	
}

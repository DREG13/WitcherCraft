package com.Red_Art.witchermod.items;

import com.Red_Art.witchermod.Main;
import com.Red_Art.witchermod.init.ModItems;
import com.Red_Art.witchermod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
public ItemBase(String name) {
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(Main.witchertab);
	ModItems.ITEMS.add(this);
} 

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
			
	}

}

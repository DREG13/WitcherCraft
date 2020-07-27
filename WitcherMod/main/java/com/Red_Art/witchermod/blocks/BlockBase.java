package com.Red_Art.witchermod.blocks;

import com.Red_Art.witchermod.Main;
import com.Red_Art.witchermod.init.ModBlocks;
import com.Red_Art.witchermod.init.ModItems;
import com.Red_Art.witchermod.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel 
{
public BlockBase(String name, Material material)

	{
	super(material);
	setUnlocalizedName(name);
	setRegistryName(name);
	setCreativeTab(Main.witchertab);
	
	ModBlocks.BLOCKS.add(this);
	ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

@Override
public void registerModels() {
	Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	
}
	
}

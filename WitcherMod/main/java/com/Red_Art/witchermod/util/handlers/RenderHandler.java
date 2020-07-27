package com.Red_Art.witchermod.util.handlers;

import com.Red_Art.witchermod.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;

public class RenderHandler 
{
public static void registerCustomMeshesAndStatesCF()
{
	ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.COOLING_OIL_BLOCK), new ItemMeshDefinition()
	{
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack)
		{
			return new ModelResourceLocation("wm:cooling_oil_block", "fluid");
		}
	});
	ModelLoader.setCustomStateMapper(ModBlocks.COOLING_OIL_BLOCK, new StateMapperBase()
	{
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state)
		{
			return new ModelResourceLocation("wm:cooling_oil_block", "fluid");
		}
	});
}

	

}

package com.Red_Art.witchermod.util.handlers;

import com.Red_Art.witchermod.blocks.TileEntityCoolingVat;
import com.Red_Art.witchermod.blocks.TileEntitySmithingTable;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntitySmithingTable.class, "smithing_table");
		GameRegistry.registerTileEntity(TileEntityCoolingVat.class, "cooling_vat");
	}

}

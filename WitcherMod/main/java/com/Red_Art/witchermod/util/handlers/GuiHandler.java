package com.Red_Art.witchermod.util.handlers;

import com.Red_Art.witchermod.blocks.ContainerCoolingVat;
import com.Red_Art.witchermod.blocks.ContainerSmithingtable;
import com.Red_Art.witchermod.blocks.GuiCoolingVat;
import com.Red_Art.witchermod.blocks.GuiSmithingTable;
import com.Red_Art.witchermod.blocks.TileEntityCoolingVat;
import com.Red_Art.witchermod.blocks.TileEntitySmithingTable;
import com.Red_Art.witchermod.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SMITHING_TABLE) return new ContainerSmithingtable(player.inventory, (TileEntitySmithingTable)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_COOLING_VAT) return new ContainerCoolingVat(player.inventory, (TileEntityCoolingVat)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_SMITHING_TABLE) return new GuiSmithingTable(player.inventory, (TileEntitySmithingTable)world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == Reference.GUI_COOLING_VAT) return new GuiCoolingVat(player.inventory, (TileEntityCoolingVat)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

}

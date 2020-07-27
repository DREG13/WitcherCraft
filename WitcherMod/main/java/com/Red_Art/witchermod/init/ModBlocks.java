package com.Red_Art.witchermod.init;

import java.util.ArrayList;
import java.util.List;

import com.Red_Art.witchermod.blocks.BlockBase;
import com.Red_Art.witchermod.blocks.BlockFluid;
import com.Red_Art.witchermod.blocks.CoolingVat;
import com.Red_Art.witchermod.blocks.MetalBlock;
import com.Red_Art.witchermod.blocks.MeteorIronOre;
import com.Red_Art.witchermod.blocks.MeteorStone;
import com.Red_Art.witchermod.blocks.SilverOre;
import com.Red_Art.witchermod.blocks.SmithingTable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;



public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Block COOLING_VAT = new CoolingVat("cooling_vat",Material.IRON);
	public static final Block SMITHING_TABLE = new SmithingTable("smithing_table",Material.ANVIL);
	public static final Block METEOR_IRON_ORE = new MeteorIronOre("meteor_iron_ore",Material.ROCK);
	public static final Block METEOR_STONE = new MeteorStone("meteor_stone",Material.ROCK);
	public static final Block SILVER_ORE = new SilverOre("silver_ore",Material.ROCK);
	public static final Block METEORITE_BLOCK = new MetalBlock("meteorite_block",Material.IRON);
	public static final Block SILVER_BLOCK = new MetalBlock("silver_block",Material.IRON);
	
	//fluids
	
	public static final Block COOLING_OIL_BLOCK = new BlockFluid("cooling_oil_block", FluidInit.COOLING_OIL, Material.WATER);
	
	
}

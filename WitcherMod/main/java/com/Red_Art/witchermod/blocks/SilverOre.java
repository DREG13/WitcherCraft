package com.Red_Art.witchermod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SilverOre extends BlockBase {

	public SilverOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(1.0F);
		setHarvestLevel("pickaxe", 2);
	}

}

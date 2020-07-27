package com.Red_Art.witchermod.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MeteorStone extends BlockBase {

	public MeteorStone(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(10.0F);
		setResistance(30.0F);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(2.0F);
	}

}

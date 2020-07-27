package com.Red_Art.witchermod.fluids;

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidLiquid extends Fluid
{
 public FluidLiquid(String name, ResourceLocation still, ResourceLocation flow) 
 {
	 super(name, still, flow);
	 this.setUnlocalizedName(name);
	 this.setViscosity(3000);
 }
}

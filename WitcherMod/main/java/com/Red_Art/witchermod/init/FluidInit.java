package com.Red_Art.witchermod.init;

import com.Red_Art.witchermod.fluids.FluidLiquid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidInit {
	public static final Fluid COOLING_OIL = new FluidLiquid("cooling_oil", new ResourceLocation("wm:blocks/cooling_oil_still"), new ResourceLocation("wm:blocks/cooling_oil_flow"));
	
	public static void registerFluids()
	{
		registerFluid(COOLING_OIL);
		
	}
		
		public static void registerFluid(Fluid fluid)
		{
			FluidRegistry.registerFluid(fluid);
			FluidRegistry.addBucketForFluid(fluid);
		}

}

package com.Red_Art.witchermod;




import com.Red_Art.witchermod.proxy.CommonProxy;
import com.Red_Art.witchermod.tabs.WitcherTab;
import com.Red_Art.witchermod.util.Reference;
import com.Red_Art.witchermod.util.handlers.RegistryHandlers;
import com.Red_Art.witchermod.world.ModWorldGen;
import com.Red_Art.witchermod.world.WorldGenCustomStructures;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)

public class Main {
@Instance
public static Main instance;

public static final CreativeTabs witchertab = new WitcherTab ("witchertab");

@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS,serverSide = Reference.COMMON_PROXY_CLASS)
public static CommonProxy proxy;

static 
{
	FluidRegistry.enableUniversalBucket();
}

@EventHandler
public static void PreInit(FMLPreInitializationEvent event) 
{
	GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
	RegistryHandlers.PreInitRegistries();
}
@EventHandler
public static void init(FMLInitializationEvent event) 
{
	RegistryHandlers.initRegistries();
}
@EventHandler
public static void PostInit(FMLPostInitializationEvent event) 
{
}


}

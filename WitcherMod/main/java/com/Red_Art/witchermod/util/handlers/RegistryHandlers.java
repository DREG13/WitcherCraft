package com.Red_Art.witchermod.util.handlers;

import com.Red_Art.witchermod.Main;
import com.Red_Art.witchermod.init.FluidInit;
import com.Red_Art.witchermod.init.ModBlocks;
import com.Red_Art.witchermod.init.ModItems;
import com.Red_Art.witchermod.init.ModRecipes;
import com.Red_Art.witchermod.util.IHasModel;
import com.Red_Art.witchermod.world.WorldGenCustomStructures;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber
public class RegistryHandlers {
@SubscribeEvent
public static void onItemRegister(RegistryEvent.Register<Item> event) 
{event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));}

@SubscribeEvent
public static void onBlockRegister(RegistryEvent.Register<Block> event) 
{
	event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	TileEntityHandler.registerTileEntities();
}

@SubscribeEvent
public static void onModelRegister(ModelRegistryEvent event) {
	for(Item item : ModItems.ITEMS) {if (item instanceof IHasModel)
		((IHasModel)item).registerModels();}
	
	for(Block block : ModBlocks.BLOCKS) {if (block instanceof IHasModel)
		((IHasModel)block).registerModels();}
}
public static void PreInitRegistries()
	{
	FluidInit.registerFluids();
	GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
	RenderHandler.registerCustomMeshesAndStatesCF();
	
	}

	

public static void initRegistries()
	{
	ModRecipes.init();
	NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	SoundsHandler.registerSounds();
	}
}

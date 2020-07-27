package com.Red_Art.witchermod.util.handlers;

import com.Red_Art.witchermod.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
public static SoundEvent TILE_COOLING_VAT, ITEM_SMITHING_HAMMER;
	
	public static void registerSounds()
	{
		TILE_COOLING_VAT = registerSound("tile.cooling_vat.done");
		ITEM_SMITHING_HAMMER = registerSound("item.smithing-hammer.smithing");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}

}

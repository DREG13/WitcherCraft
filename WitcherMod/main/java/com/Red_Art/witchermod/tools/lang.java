package com.Red_Art.witchermod.tools;

import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class lang extends ToolSword{
	
	public lang(String name, ToolMaterial material) {
		super(name, material);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		if (slot == EntityEquipmentSlot.MAINHAND)
        {
             new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -3.5D, 0);
        }

        
		return super.getAttributeModifiers(slot, stack);
	}

}

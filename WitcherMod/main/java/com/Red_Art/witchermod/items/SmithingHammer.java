package com.Red_Art.witchermod.items;

import java.util.Random;
import java.util.UUID;

import com.Red_Art.witchermod.blocks.SmithingTable;
import com.Red_Art.witchermod.blocks.TileEntitySmithingTable;
import com.Red_Art.witchermod.util.handlers.SoundsHandler;
import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SmithingHammer extends ItemBase{
	public SmithingHammer(String name) {
		super(name);
		setMaxStackSize(1);
		
		
	}
	
	
	protected SoundEvent getsmithingSound() 
	{
		return SoundsHandler.ITEM_SMITHING_HAMMER;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		ItemStack item = player.getHeldItem(hand);
		Random random = new Random();
		if(!worldIn.isRemote) 
		{
			if(random.nextInt(3)==0) {
			TileEntity te = worldIn.getTileEntity(pos);
			if(te != null && te instanceof TileEntitySmithingTable)
			{
				TileEntitySmithingTable test = (TileEntitySmithingTable) te;
				
					if(test.canForge()) {
					test.setClicks(test.getClicks()+1);
					this.getsmithingSound();
					}
					
				}
			}
		
		
		
		
		}
		return EnumActionResult.SUCCESS;}
	
	
}
	
	
	
	
	
	

	


package com.Red_Art.witchermod.blocks;

import com.Red_Art.witchermod.blocks.slots.SlotSmithingTableOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCoolingVat extends Container{
	private final TileEntityCoolingVat tileentity;
	private int oil,water,brine,cooktime,totalCookTime;
	
	public ContainerCoolingVat(InventoryPlayer player, TileEntityCoolingVat tileentity)
	{
		this.tileentity = tileentity;
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17) );
		this.addSlotToContainer(new Slot(tileentity, 2, 55, 53) );
		this.addSlotToContainer(new SlotSmithingTableOutput(player.player, tileentity, 1, 116, 35) );
		
		
		for(int y =0;y<3;y++) {
			for(int x =0;x<9;x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x*18, 84 + y * 18));
			}
		}
		for(int x =0;x<9;x++)
		{
			this.addSlotToContainer(new Slot(player, x, 8 + x*18,142));
		}
	}
	
	
	
	
	
	

	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			
			if(this.oil != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.brine != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.cooktime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.totalCookTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
			if(this.water != this.tileentity.getField(4)) listener.sendWindowProperty(this, 4, this.tileentity.getField(4));

		}
		
		
		this.oil= this.tileentity.getField(0);
		this.brine = this.tileentity.getField(1);
		this.cooktime= this.tileentity.getField(2);
		this.totalCookTime = this.tileentity.getField(3);
		this.water= this.tileentity.getField(4);
		
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) 
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 1) 
			{
				if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 2 &&  index != 0) 
			{		
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if(!ReinforcedRecipes.getInstance().getReinforcingResult(stack1).isEmpty())
				{
					if(!this.mergeItemStack(stack1, 0, 2, false)) 
					{
						return ItemStack.EMPTY;
					}
					else if(TileEntityCoolingVat.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityCoolingVat.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityCoolingVat.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 2, 3, false)) return ItemStack.EMPTY;
					}
					else if(index >= 4 && index < 31)
					{
						if(!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
					}
					else if(index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false))
					{
						return ItemStack.EMPTY;
					}
				}
			} 
			else if(!this.mergeItemStack(stack1, 4, 40, false)) 
			{
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

}

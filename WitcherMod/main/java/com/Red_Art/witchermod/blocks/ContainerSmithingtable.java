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

public class ContainerSmithingtable extends Container
{
	private final TileEntitySmithingTable tileentity;
	private int clicks, Totalprogress;
	
	public ContainerSmithingtable(InventoryPlayer player, TileEntitySmithingTable tileentity)
	{
		this.tileentity = tileentity;
		this.addSlotToContainer(new Slot(tileentity, 0, 39, 51) );
		this.addSlotToContainer(new SlotSmithingTableOutput(player.player, tileentity, 1, 119, 51) );
		
		
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
			
			
			if(this.clicks != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.Totalprogress != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			
		}
		
		
		this.clicks = this.tileentity.getField(0);
		this.Totalprogress = this.tileentity.getField(1);
		
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
			else if(index != 0) 
			{		
Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if(!SmithingRecipes.getInstance().getSmithingResult(stack1).isEmpty())
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

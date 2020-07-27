package com.Red_Art.witchermod.blocks;

import com.Red_Art.witchermod.init.ModBlocks;
import com.Red_Art.witchermod.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.internal.Trees.This;
import net.minecraftforge.items.ItemStackHandler;
import com.Red_Art.witchermod.blocks.SmithingTable;

public class TileEntitySmithingTable extends TileEntity implements IInventory, ITickable{
	private String customName;
	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private ItemStackHandler handler = new ItemStackHandler(2);
	private int Totalprogress = 10;
	private int clicks = 0;
	private ItemStack smelting = ItemStack.EMPTY;
	public boolean hasCustomName() 
	
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.smithing_table");
	}

	@Override
	public int getSizeInventory() {
		
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty())return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		
	}

	@Override
	public int getInventoryStackLimit() {
		
		return 4;
	}
	
	public int inProgress() 
	{
		return  this.getField(0);
	}
	
	public static boolean InProgress(TileEntitySmithingTable tileentity) 
	{
		return  tileentity.clicks>0;
	}
	
	public void setClicks(int clicks) {
		this.clicks = clicks;
		this.markDirty();
	}
	
	public int getClicks() {
		return this.clicks;
	}
	
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.clicks = compound.getInteger("clicks");
		this.Totalprogress = compound.getInteger("TProgress");
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("clicks", (short)this.clicks);
		compound.setInteger("TProgress", (short)this.Totalprogress);
		compound.setTag("Inventory", this.handler.serializeNBT());
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean canForge() 
	{
		if(((ItemStack)this.getStackInSlot(0)).isEmpty()) return false;
		else 
		{
			ItemStack result = SmithingRecipes.getInstance().getSmithingResult((ItemStack)this.getStackInSlot(0));	
			if(result.isEmpty()) return false;
			else
			{
				return true;
				
			}
		}
	}

	@Override
	public void update() 
{	
	this.clicks = this.inProgress();
	ItemStack input1 = getStackInSlot(0);
	ItemStack output1 = SmithingRecipes.getInstance().getSmithingResult(input1);
	ItemStack smelting = output1;
	
	
	if(this.getStackInSlot(0).isEmpty()) {
		clicks = 0;
	}
	if(this.getStackInSlot(1).isEmpty()&&!this.getStackInSlot(0).isEmpty())
	{
		if(this.canForge())
		{((SmithingTable) ModBlocks.SMITHING_TABLE).setState(true, false, world, pos);
	
				if(clicks == Totalprogress) {
					this.getStackInSlot(0).shrink(1);
					this.inventory.set(1, smelting);
					

					smelting = ItemStack.EMPTY;
					input1 = ItemStack.EMPTY;
					output1 = ItemStack.EMPTY;
					clicks = 0;
				}else
				{
					smelting = ItemStack.EMPTY;
					input1 = ItemStack.EMPTY;
					output1 = ItemStack.EMPTY;
					}
			}else
		{
		smelting = ItemStack.EMPTY;
		input1 = ItemStack.EMPTY;
		output1 = ItemStack.EMPTY;
		}
		}else if(!this.getStackInSlot(1).isEmpty()) {((SmithingTable) ModBlocks.SMITHING_TABLE).setState(false, true, world, pos);
		smelting = ItemStack.EMPTY;
		input1 = ItemStack.EMPTY;
		output1 = ItemStack.EMPTY;
		}
		else((SmithingTable) ModBlocks.SMITHING_TABLE).setState(false, false, world, pos);
		smelting = ItemStack.EMPTY;
		input1 = ItemStack.EMPTY;
		output1 = ItemStack.EMPTY;
	}

	


	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	
	@Override
	public int getField(int id) {
		
		switch(id) 
		{
		case 0:
			return this.clicks;
		case 1:
			return this.Totalprogress;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) {
		
		switch(id) 
		{
		case 0:
			this.clicks = value;
			break;
		case 1:
			this.Totalprogress = value;
		}
	}

	@Override
	public int getFieldCount() {
		
		return 2;
	}

	@Override
	public void clear() {
		this.inventory.clear();
		
	}

	@Override
	public String getName() {
		return this.getName();
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	


}

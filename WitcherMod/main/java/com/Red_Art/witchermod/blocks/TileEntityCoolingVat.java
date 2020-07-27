package com.Red_Art.witchermod.blocks;

import java.util.Random;

import com.Red_Art.witchermod.init.ModBlocks;
import com.Red_Art.witchermod.init.ModItems;
import com.Red_Art.witchermod.util.handlers.SoundsHandler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityCoolingVat extends TileEntity implements IInventory,ITickable{

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private String customName;
	private ItemStackHandler handler = new ItemStackHandler(3);
	private ItemStack reinforcing = ItemStack.EMPTY;
	private ItemStack bubbling = ItemStack.EMPTY;
	private ItemStack hardening = ItemStack.EMPTY;
	private int oil;
	private int water;
	private int brine;
	private int cookTime;
	private int totalCookTime = 200;

	
	
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
		return this.hasCustomName() ? new TextComponentString(this.customName) : new TextComponentTranslation("container.cooling_vat");
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
	
	protected SoundEvent getcoolingSound() 
	{
		return SoundsHandler.TILE_COOLING_VAT;
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
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.handler.deserializeNBT(compound.getCompoundTag("Inventory"));
		this.oil=compound.getInteger("Oil");
		this.water=compound.getInteger("Water");
		this.brine=compound.getInteger("Brine");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("Oil", (short)this.oil);
		compound.setInteger("Water", (short)this.water);
		compound.setInteger("Brine", (short)this.brine);
		compound.setInteger("CookTime", (short)this.cookTime);
		compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
		compound.setTag("Inventory", this.handler.serializeNBT());
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}
	
	public boolean isOil() 
	{
		return this.oil > 0;
	}
	
	
	
	public boolean isWater() 
	{
		return this.water > 0;
	}
	
	
	public boolean isBrine() 
	{
		return this.brine > 0;
	}
	
	
	
	
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(TileEntityCoolingVat te) 
	{
			return te.getField(0) > 0||te.getField(1) > 0||te.getField(4) > 0;
	}
	@Override
	public void update() 
	{	
		
		int brine = this.brine;
		int water = this.water;
		int oil = this.oil;
		ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE, 1);
		ItemStack input1 = getStackInSlot(0);
		ItemStack fuel = getStackInSlot(2);
		ItemStack output1 = HardenedRecipes.getInstance().getHardeningResult(input1);
		ItemStack hardening = output1;
		ItemStack output2 = ReinforcedRecipes.getInstance().getReinforcingResult(input1);
		ItemStack reinforcing = output2;
		ItemStack output3 = BubbledRecipes.getInstance().getBubblingResult(input1);
		ItemStack bubbling = output3;
		Random random = new Random();
		if(isItemFuel(fuel)) {
			if(getItemBurnTime(fuel)==1) {
				brine=13;
				this.brine=brine;
				CoolingVat.setState(false, false, true, world, pos);
				water=0;
				oil=0;
				this.getStackInSlot(2).shrink(1);
				this.inventory.set(2, bottle);
			}
			
			if(getItemBurnTime(fuel)==2) {
				oil=13;
				this.oil=oil;
				CoolingVat.setState(true, false, false, world, pos);
				brine=0;
				water=0;
				this.getStackInSlot(2).shrink(1);
				this.inventory.set(2, bottle);
			}
			
			if(getItemBurnTime(fuel)==3) {
				water=13;
				this.water=water;
				CoolingVat.setState(false, true, false, world, pos);
				oil=0;
				brine=0;
				this.getStackInSlot(2).shrink(1);
				this.inventory.set(2, bottle);
			}
		}
		
		if(!this.getStackInSlot(0).isEmpty()&&this.canSmelt())
		{
			
			if(this.oil==13) {
				if(random.nextInt(19)!=0) {
				this.getStackInSlot(0).shrink(1);
				this.inventory.set(1, reinforcing);
				
				this.getcoolingSound();
				reinforcing = ItemStack.EMPTY;
				input1 = ItemStack.EMPTY;
				output2 = ItemStack.EMPTY;
				this.oil = 0;
				oil=0;}else {this.getStackInSlot(0).shrink(1);
				this.inventory.set(1, bubbling);
				this.oil = 0;
				oil=0;
				this.getcoolingSound();
				bubbling = ItemStack.EMPTY;
				input1 = ItemStack.EMPTY;
				output3 = ItemStack.EMPTY;
				}
			}
			
			if(this.water==13) {
				if(random.nextInt(9)!=0) {
				this.getStackInSlot(0).shrink(1);
				this.inventory.set(1, hardening);
				
				this.getcoolingSound();
				hardening = ItemStack.EMPTY;
				input1 = ItemStack.EMPTY;
				output1 = ItemStack.EMPTY;
				this.water = 0;
				water = 0;}else {
				this.getStackInSlot(0).shrink(1);
				
				this.getcoolingSound();
				this.water = 0;
				water = 0;
				hardening = ItemStack.EMPTY;
				input1 = ItemStack.EMPTY;
				output2 = ItemStack.EMPTY;}
			}
			
			if(this.brine==13) {
				this.getStackInSlot(0).shrink(1);
				this.inventory.set(1, hardening);
				
				this.getcoolingSound();
				hardening = ItemStack.EMPTY;
				input1 = ItemStack.EMPTY;
				output1 = ItemStack.EMPTY;
				this.brine = 0;
				brine = 0;
			}
			
			if(this.brine==0&&this.oil==0&&this.water==0) {
				CoolingVat.setState(false, false, false, world, pos);
			}
		}
		
		
		}
	
	private boolean canSmelt() 
	{
		if(((ItemStack)this.getStackInSlot(0)).isEmpty()) return false;
		else 
		{
			ItemStack result = ReinforcedRecipes.getInstance().getReinforcingResult((ItemStack)this.getStackInSlot(0));	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.getStackInSlot(1);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= 64 && res <= output.getMaxStackSize();
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();

			if (item == ModItems.BOTTLE_BRINE) return 1;
			if (item == ModItems.BOTTLE_COOLING_OIL) return 2;
			if (item == Items.POTIONITEM) return 3;

			return GameRegistry.getFuelValue(fuel);
		}
	}
		
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemBurnTime(fuel) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.oil;
		case 1:
			return this.brine;
		case 2:
			return this.cookTime;
		case 3:
			return this.totalCookTime;
		case 4:
			return this.water;
		default:
			return 0;
		}
	}

	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.oil = value;
			break;
		case 1:
			this.brine = value;
			break;
		case 2:
			this.cookTime = value;
			break;
		case 3:
			this.totalCookTime = value;
			break;
		case 4:
			this.water = value;
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}

package com.Red_Art.witchermod.blocks;

import java.util.Random;

import com.Red_Art.witchermod.Main;
import com.Red_Art.witchermod.init.ModBlocks;
import com.Red_Art.witchermod.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CoolingVat extends BlockBase implements ITileEntityProvider{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool OIL = PropertyBool.create("oil");
	public static final PropertyBool WATER = PropertyBool.create("water");
	public static final PropertyBool BRINE = PropertyBool.create("brine");
	public CoolingVat(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 1);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OIL, false).withProperty(WATER, false).withProperty(BRINE, false));
	}
	
	public static final AxisAlignedBB COOLING_VAT_AABB = new AxisAlignedBB(0.1875D, 0D, 0.1875D, 0.8125D, 1D, 0.8125D);
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
		
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
		
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return COOLING_VAT_AABB;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
	
		return Item.getItemFromBlock(ModBlocks.COOLING_VAT);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {

		return new ItemStack(ModBlocks.COOLING_VAT);
	}
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote) 
		{
			playerIn.openGui(Main.instance, Reference.GUI_COOLING_VAT, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		
		return true;
	}
	
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {


		if(worldIn.isRemote)
		{
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			
			if(face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
			else if(face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
			else if(face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
			else if(face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);

		}
	}
	
	public static void setState(boolean oil, boolean water, boolean brine, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(oil) worldIn.setBlockState(pos, ModBlocks.COOLING_VAT.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(OIL, true).withProperty(WATER, false).withProperty(BRINE, false), 3);
		else if(water)worldIn.setBlockState(pos, ModBlocks.COOLING_VAT.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(OIL, false).withProperty(WATER, true).withProperty(BRINE, false), 3);
		else if(brine)worldIn.setBlockState(pos, ModBlocks.COOLING_VAT.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(OIL, false).withProperty(WATER, false).withProperty(BRINE, true), 3);
		else worldIn.setBlockState(pos, ModBlocks.COOLING_VAT.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(OIL, false).withProperty(WATER, false).withProperty(BRINE, false), 3);
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
			
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEntityCoolingVat();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityCoolingVat tilenetity = (TileEntityCoolingVat)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tilenetity);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		
		return new BlockStateContainer(this, new IProperty[]{FACING, OIL, WATER, BRINE});
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}
}




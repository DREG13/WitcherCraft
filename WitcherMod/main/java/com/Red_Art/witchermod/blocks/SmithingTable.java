package com.Red_Art.witchermod.blocks;

import java.util.Random;

import com.Red_Art.witchermod.Main;
import com.Red_Art.witchermod.init.ModBlocks;
import com.Red_Art.witchermod.init.ModItems;
import com.Red_Art.witchermod.items.SmithingHammer;
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

public class SmithingTable extends BlockBase implements ITileEntityProvider{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool SMITHING = PropertyBool.create("smithing");
	public static final PropertyBool DONE = PropertyBool.create("done");

	public SmithingTable(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.ANVIL);
		setHardness(10.0F);
		setResistance(1200.0F);
		setHarvestLevel("pickaxe", 2);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(SMITHING, false).withProperty(DONE, false));
	}
	
	public static final AxisAlignedBB ST_north = new AxisAlignedBB(0.125D, 0D, 1.3125D, 0.875D, 1D, -0.1875D);
	public static final AxisAlignedBB ST_south = new AxisAlignedBB(0.125D, 0D, -0.3125D, 0.875D, 1D, 1.1875D);
	public static final AxisAlignedBB St_east = new AxisAlignedBB(1.1875D, 0D, 0.125D, -0.3125D, 1D, 0.875D);
	public static final AxisAlignedBB ST_west = new AxisAlignedBB(-0.1875D, 0D, 0.125D, 1.3125D, 1D, 0.875D);
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
	
		return Item.getItemFromBlock(ModBlocks.SMITHING_TABLE);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {

		return new ItemStack(ModBlocks.SMITHING_TABLE);
	}
	

	
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote) 
		{
			
			TileEntity te = worldIn.getTileEntity(pos);
			if(te != null && te instanceof TileEntitySmithingTable)
			{
				TileEntitySmithingTable test = (TileEntitySmithingTable) te;
				if(!playerIn.isSneaking()) {
				 playerIn.openGui(Main.instance, Reference.GUI_SMITHING_TABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
				}
			}
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
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing face = (EnumFacing)state.getValue(FACING);
		if(face == EnumFacing.NORTH) {return ST_north;}else
			if(face == EnumFacing.EAST) {return St_east;}else
				if(face == EnumFacing.SOUTH) {return ST_south ;}else
					if(face == EnumFacing.WEST) {return ST_west;}else
		return null;
	}
	
	public static void setState(boolean isLit, boolean isDone, World worldIn, BlockPos pos)
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		
		if(isLit) worldIn.setBlockState(pos, ModBlocks.SMITHING_TABLE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(SMITHING, true).withProperty(DONE, false), 3);
		else {if(isDone) 
			worldIn.setBlockState(pos, ModBlocks.SMITHING_TABLE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(SMITHING, false).withProperty(DONE, true), 3);
			else worldIn.setBlockState(pos, ModBlocks.SMITHING_TABLE.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(SMITHING, false).withProperty(DONE, false), 3);
		}
		
		if(tileentity != null)
		{
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
			
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEntitySmithingTable();
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
		TileEntitySmithingTable tilenetity = (TileEntitySmithingTable)worldIn.getTileEntity(pos);
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
		
		return new BlockStateContainer(this, new IProperty[]{FACING, SMITHING, DONE});
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

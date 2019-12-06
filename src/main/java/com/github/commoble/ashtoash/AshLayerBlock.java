package com.github.commoble.ashtoash;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class AshLayerBlock extends Block
{
	public static final IntProperty LAYERS = Properties.LAYERS;
	
	// from SnowBlock
	public static final VoxelShape[] LAYERS_TO_SHAPE = new VoxelShape[] {
		VoxelShapes.empty(),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
		Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
		};
	
	public AshLayerBlock(Block.Settings settings)
	{
		super(settings);
		this.setDefaultState(this.stateFactory.getDefaultState().with(LAYERS, 1));
	}

	@Override
	public boolean canPlaceAtSide(BlockState world, BlockView view, BlockPos pos, BlockPlacementEnvironment env)
	{
		switch (env)
		{
			case LAND:
				return world.get(LAYERS) < 5;
			case WATER:
				return false;
			case AIR:
				return false;
			default:
				return false;
		}
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos)
	{
		return LAYERS_TO_SHAPE[state.get(LAYERS)];
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos)
	{
		return LAYERS_TO_SHAPE[state.get(LAYERS) - 1];
	}

	@Override
	public boolean hasSidedTransparency(BlockState state)
	{
		return true;
	}

	@Override
	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos)
	{
		BlockState blockState = world.getBlockState(pos.down());
		
		return Block.isFaceFullSquare(blockState.getCollisionShape(world, pos.down()), Direction.UP)
			|| (blockState.getProperties().contains(LAYERS) && blockState.get(LAYERS) == 8);

	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		return !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, facing, neighborState, world, pos, neighborPos);
	}

	@Override
	public void onScheduledTick(BlockState state, World world, BlockPos pos, Random random)
	{
//		if (world.getLightLevel(LightType.BLOCK, pos) > 11)
//		{
//			dropStacks(state, world, pos);
//			world.clearBlockState(pos, false);
//		}

	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext ctx)
	{
		int i = state.get(LAYERS);
		if (ctx.getStack().getItem() == this.asItem() && i < 8)
		{
			if (ctx.canReplaceExisting())
			{
				return ctx.getSide() == Direction.UP;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return i == 1;
		}
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx)
	{
		BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
		if (blockState.getBlock() == this)
		{
			int i = blockState.get(LAYERS);
			return blockState.with(LAYERS, Math.min(8, i + 1));
		}
		else
		{
			return super.getPlacementState(ctx);
		}
	}

	@Override
	protected void appendProperties(StateFactory.Builder<Block, BlockState> builder)
	{
		builder.add(LAYERS);
	}
}
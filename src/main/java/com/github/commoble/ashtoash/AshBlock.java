package com.github.commoble.ashtoash;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class AshBlock extends FallingBlock
{
	public AshBlock(Block.Settings settings)
	{
		super(settings);
	}

	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext ctx)
	{
		return super.canReplace(state, ctx);//ctx.getWorld().getBlockState(ctx.getBlockPos()).getBlock() == BlockRegistrar.ASH || super.canReplace(state, ctx);
	}

	@Override
	public boolean canPlaceAt(BlockState state, ViewableWorld world, BlockPos pos)
	{
		BlockState targetState = world.getBlockState(pos);
		return (world.getBlockState(pos).getBlock() == BlockRegistrar.ASH && targetState.get(AshLayerBlock.LAYERS) < 8) || super.canPlaceAt(state, world, pos);
	}

	@Override
	public void onDestroyedOnLanding(World world, BlockPos pos)
	{
		System.out.println("poof");
//		BlockState state = world.getBlockState(pos);
//		System.out.println(pos);
//		if (state.getBlock() == BlockRegistrar.ASH)
//		{
//			world.setBlockState(pos, BlockRegistrar.ASH_BLOCK.getDefaultState());
//		}
	}

	@Override
	protected void configureFallingBlockEntity(FallingBlockEntity entity)
	{
		entity.dropItem = false;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public int getColor(BlockState state)
	{
		return -8356741;
	}
}

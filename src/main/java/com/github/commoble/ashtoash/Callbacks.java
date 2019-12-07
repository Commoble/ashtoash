package com.github.commoble.ashtoash;

import java.util.Random;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Callbacks
{
	public static void onTrySpreadingFireCallsClearBlockStateCallback(World world, BlockPos pos, int spreadFactor, Random rand, int currentAge, CallbackInfo info)
	{
		BlockState state = world.getBlockState(pos);
		if (BlockTags.getContainer().get(Identifiers.ASHABLES).contains(state.getBlock()))
		{
			if (state.isFullOpaque(world, pos))
			{
				world.setBlockState(pos, BlockRegistrar.ASH_BLOCK.getDefaultState());
			}
			else
			{
				world.clearBlockState(pos, false);
				AshLayerBlock.tryToGenerateAsh(world, pos);
			}
			info.cancel();
		}
	}
}

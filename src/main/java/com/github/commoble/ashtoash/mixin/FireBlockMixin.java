package com.github.commoble.ashtoash.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.commoble.ashtoash.Callbacks;

import net.minecraft.block.FireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FireBlock.class)
public class FireBlockMixin
{
	@Inject(
		method = "trySpreadingFire",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;clearBlockState(Lnet/minecraft/util/math/BlockPos;Z)Z"),
		cancellable = true,
		require = 0)
	public void onTrySpreadingFireCallsClearBlockState(World world, BlockPos pos, int spreadFactor, Random rand, int currentAge, CallbackInfo info)
	{
		Callbacks.onTrySpreadingFireCallsClearBlockStateCallback(world, pos, spreadFactor, rand, currentAge, info);
	}
}

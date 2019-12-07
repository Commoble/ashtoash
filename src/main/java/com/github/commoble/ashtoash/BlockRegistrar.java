package com.github.commoble.ashtoash;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class BlockRegistrar
{
	public static final AshLayerBlock ASH = new AshLayerBlock(FabricBlockSettings.of(Material.SNOW).strength(0.1F, 0.1F).ticksRandomly().breakByHand(true).sounds(BlockSoundGroup.SAND).build());
	public static final Block ASH_BLOCK = new Block(FabricBlockSettings.of(Material.SNOW_BLOCK).strength(0.2F, 0.2F).breakByHand(true).sounds(BlockSoundGroup.SAND).build());
	
	public static void registerBlocks()
	{
		Registry.register(Registry.BLOCK, Identifiers.ASH, ASH);
		Registry.register(Registry.BLOCK, Identifiers.ASH_BLOCK, ASH_BLOCK);
	}
}

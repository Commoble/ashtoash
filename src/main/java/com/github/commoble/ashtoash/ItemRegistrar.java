package com.github.commoble.ashtoash;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

public class ItemRegistrar
{
	public static final BlockItem ASH = new BlockItem(BlockRegistrar.ASH, new Item.Settings().group(ItemGroup.DECORATIONS));
	public static final BlockItem ASH_BLOCK = new BlockItem(BlockRegistrar.ASH_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));;
	public static final Item ASH_CLUMP = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

	public static void registerItems()
	{
		Registry.register(Registry.ITEM, Identifiers.ASH, ASH);
		Registry.register(Registry.ITEM, Identifiers.ASH_BLOCK, ASH_BLOCK);
		Registry.register(Registry.ITEM, Identifiers.ASH_CLUMP, ASH_CLUMP);
	}
}

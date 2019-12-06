package com.github.commoble.ashtoash;

import net.fabricmc.api.ModInitializer;

public class AshToAshInitializer implements ModInitializer {
	
	public static final String MODID = "ashtoash";
	
	@Override
	public void onInitialize()
	{
		BlockRegistrar.registerBlocks();
		ItemRegistrar.registerItems();
	}
}

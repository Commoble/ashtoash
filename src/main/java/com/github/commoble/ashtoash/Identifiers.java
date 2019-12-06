package com.github.commoble.ashtoash;

import net.minecraft.util.Identifier;

public class Identifiers
{

	public static final Identifier ASHABLES = getModRL("ashables");
	public static final Identifier ASH = getModRL("ash");
	public static final Identifier ASH_BLOCK = getModRL("ash_block");
	public static final Identifier ASH_CLUMP = getModRL("ash_clump");
	
	public static Identifier getModRL(String name)
	{
		return new Identifier(AshToAshInitializer.MODID, name);
	}

}

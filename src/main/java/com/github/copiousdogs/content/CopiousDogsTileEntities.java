package com.github.copiousdogs.content;

import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.common.registry.GameRegistry;

public class CopiousDogsTileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityDogDish.class, "tileEntityDogDish");
	}
}

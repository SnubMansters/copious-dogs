package com.github.copiousdogs.content;

import net.minecraft.block.Block;

import com.github.copiousdogs.block.BlockDogDish;
import com.github.copiousdogs.item.ItemDogDish;

import cpw.mods.fml.common.registry.GameRegistry;

public class CopiousDogsBlocks
{
	public static final Block dogDish = new BlockDogDish();
	
	public static void init() {
		
		GameRegistry.registerBlock(dogDish, ItemDogDish.class, "dogDish");
	}
}

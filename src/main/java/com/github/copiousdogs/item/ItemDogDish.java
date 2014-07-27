package com.github.copiousdogs.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemDogDish extends ItemBlock 
{

	private final static String[] subNames = 
		{
			"white", "orange",  "magenta", "lightBlue", "yellow", "lightGreen",
			"pink", "darkGrey", "lightGrey", "cyan", "purple", "blue", "brown",
			"green", "red", "black"
		};
		
		public ItemDogDish(Block block)
		{
			super(block);
			setHasSubtypes(true);
		}
		
		@Override
		public int getMetadata(int damageValue)
		{
			return damageValue;
		}
}

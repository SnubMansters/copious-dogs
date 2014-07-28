package com.github.copiousdogs.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
		
		@Override
		@SideOnly(Side.CLIENT)
		public void getSubItems(Item item, CreativeTabs tab, List list) 
		{
			for (int i = 0; i < 16; i++) 
			{
				list.add(new ItemStack(this, 1, i));
			}
		}
}

package com.github.copiousdogs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.copiousdogs.block.BlockDogDish;
import com.github.copiousdogs.content.CopiousDogsBlocks;
import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.item.ItemDogCollar;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CommonProxy 
{

	@SideOnly(Side.CLIENT)
	public void registerRenderers()
	{
		
	}
	
	public void registerRecipes()
	{
		GameRegistry.addRecipe(new ItemStack(CopiousDogsItems.dogBiscuit), " M ", "MBM", " M ", 
				'M', Item.itemRegistry.getObject("porkchop"), 'B', Item.itemRegistry.getObject("bone"));
		GameRegistry.addRecipe(new ItemStack(CopiousDogsItems.dogCollar), "SSS", "S S", "SSI",
				'S', Item.itemRegistry.getObject("string"), 'I', Item.itemRegistry.getObject("iron_ingot"));
		GameRegistry.addRecipe(new ItemStack(CopiousDogsBlocks.dogDish, 1, 0), "III", "IBI", "III",
				'I', Item.itemRegistry.getObject("iron_ingot"), 'B', Item.itemRegistry.getObject("bucket"));
		
		for (int i = 0; i < 16; i++) {
			
			GameRegistry.addShapelessRecipe(new ItemStack(CopiousDogsItems.dogCollar, 1, i), 
					new ItemStack((Item)Item.itemRegistry.getObject("dye"), 1, ItemDogCollar.getDyeFromItem(i)), new ItemStack(CopiousDogsItems.dogCollar, 1, OreDictionary.WILDCARD_VALUE));
			GameRegistry.addShapelessRecipe(new ItemStack(CopiousDogsBlocks.dogDish, 1, i), new ItemStack(CopiousDogsBlocks.dogDish, 1, OreDictionary.WILDCARD_VALUE),
					new ItemStack((Item)Item.itemRegistry.getObject("dye"), 1, BlockDogDish.getDyeFromBlock(i)));
		}
	}
}

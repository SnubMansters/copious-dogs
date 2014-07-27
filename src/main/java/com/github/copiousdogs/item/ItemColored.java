package com.github.copiousdogs.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.github.copiousdogs.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemColored extends ItemCopiousDogs 
{

	private IIcon[] itemIcons;
	
	public ItemColored() 
	{
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcons = new IIcon[16];

		for (int i = 0; i < 16; i++)
		{
			itemIcons[i] = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().lastIndexOf(".") + 1) + "_" + ItemDye.field_150921_b[getItemFromDye(i)]);
		}
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
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par0)
	{
		return itemIcons[par0];
	}
	
	public static int getItemFromDye(int par0)
	{
		return 15 - par0;
	}
	
	public static int getDyeFromItem(int par0)
	{
		return 15 - par0;
	}
}

package com.github.copiousdogs.block;

import com.github.copiousdogs.CopiousDogs;
import com.github.copiousdogs.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;

public class BlockCopiousDogs extends Block
{
	
	protected BlockCopiousDogs(Material material)
	{
		super(material);
		setCreativeTab(CopiousDogs.tabCopiousDogs);	
	}
	
	@Override
	public String getUnlocalizedName() 
	{
		return String.format("item.%s.%s", Reference.MOD_ID.toLowerCase(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) 
	{
		blockIcon = register.registerIcon(Reference.MOD_ID + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().lastIndexOf(".") + 1));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
	}
}

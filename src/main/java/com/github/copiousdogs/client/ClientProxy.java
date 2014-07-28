package com.github.copiousdogs.client;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;

import com.github.copiousdogs.CommonProxy;
import com.github.copiousdogs.client.render.item.ItemDogDishRenderer;
import com.github.copiousdogs.client.render.tileentity.TileEntityDogDishRenderer;
import com.github.copiousdogs.content.CopiousDogsBlocks;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy 
{

	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerRenderers()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDogDish.class, new TileEntityDogDishRenderer());
		MinecraftForgeClient.registerItemRenderer(new ItemStack(CopiousDogsBlocks.dogDish).getItem(), new ItemDogDishRenderer());
	}
}

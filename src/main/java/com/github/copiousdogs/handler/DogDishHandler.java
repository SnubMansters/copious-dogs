package com.github.copiousdogs.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import com.github.copiousdogs.block.BlockDogDish;
import com.github.copiousdogs.network.MessageDogDish;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class DogDishHandler implements IMessageHandler<MessageDogDish, IMessage>
{

	@Override
	public IMessage onMessage(MessageDogDish message, MessageContext ctx)
	{
		int x = message.x;
		int y = message.y;
		int z = message.z;
		
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		
		if (side == Side.SERVER)
		{
			World[] worlds = MinecraftServer.getServer().worldServers;
				
			for (int i = 0; i < worlds.length; i++) 
			{
				World world = worlds[i];
				if (world.blockExists(x, y, z) && world.getBlock(x, y, z) instanceof BlockDogDish) 
				{
					TileEntityDogDish te = (TileEntityDogDish)world.getTileEntity(x, y, z);
					te.setFoodLevel(message.food);
				}
			}
		}
		else if (side == Side.CLIENT)
		{
			World world = Minecraft.getMinecraft().theWorld;
			
			if (world.blockExists(x, y, z) && world.getBlock(x, y, z) instanceof BlockDogDish) 
			{
				TileEntityDogDish te = (TileEntityDogDish)world.getTileEntity(x, y, z);
				te.setFoodLevel(message.food);
			}
		}
			
		return null;
	}
}

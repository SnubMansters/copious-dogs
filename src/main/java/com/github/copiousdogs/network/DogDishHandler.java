package com.github.copiousdogs.network;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import com.github.copiousdogs.block.BlockDogDish;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class DogDishHandler implements IMessageHandler<MessageDogDish, IMessage>
{

	@Override
	public IMessage onMessage(MessageDogDish message, MessageContext ctx)
	{
		int x = message.x;
		int y = message.y;
		int z = message.z;
		
		World world = Minecraft.getMinecraft().theWorld;
		if (world.blockExists(x, y, z) && world.getBlock(x, y, z) instanceof BlockDogDish) {
			
			TileEntityDogDish te = (TileEntityDogDish)world.getTileEntity(x, y, z);
			te.setFoodLevel(message.food);
		}
		
		return null;
	}
}

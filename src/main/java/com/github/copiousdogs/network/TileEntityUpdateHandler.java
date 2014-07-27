package com.github.copiousdogs.network;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class TileEntityUpdateHandler implements IMessageHandler<MessageTileEntity, IMessage>
{

	@Override
	public IMessage onMessage(MessageTileEntity message, MessageContext ctx)
	{
		NBTTagCompound t = message.tag;
		
		int x = t.getInteger("x");
		int y = t.getInteger("y");
		int z = t.getInteger("z");
		
		return null;
	}

}

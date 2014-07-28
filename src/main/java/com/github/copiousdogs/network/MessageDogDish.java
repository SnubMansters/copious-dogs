package com.github.copiousdogs.network;

import io.netty.buffer.ByteBuf;

import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageDogDish implements IMessage
{
	public int x, y, z, food;
	
	public MessageDogDish() {}
	
	public MessageDogDish(TileEntityDogDish t) 
	{
		x = t.xCoord;
		y = t.yCoord;
		z = t.zCoord;
		food = t.getFoodLevel();
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		food = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(food);
	}
}

package com.github.copiousdogs.network;

import io.netty.buffer.ByteBuf;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageTileEntity implements IMessage
{
	public NBTTagCompound tag;
	
	public MessageTileEntity() {}
	
	public MessageTileEntity(TileEntity t)
	{
		NBTTagCompound tag = new NBTTagCompound();
		t.writeToNBT(tag);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			byte[] data = buf.array();
			tag = CompressedStreamTools.readCompressed(new ByteArrayInputStream(data));
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		try
		{
			byte[] data = CompressedStreamTools.compress(tag);
			buf.writeBytes(data);
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

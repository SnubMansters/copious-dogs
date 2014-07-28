package com.github.copiousdogs.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils
{
	public static byte[] nbtToByteArray(NBTTagCompound tag)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		System.out.println(tag + " " + dos);
		
		try 
		{
			CompressedStreamTools.write(tag, dos);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return baos.toByteArray();
	}
	
	public static NBTTagCompound byteArrayToNBT(byte[] bytes) 
	{
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		
		NBTTagCompound result = null;
		
		try
		{
			result = CompressedStreamTools.read(dis);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return result;
	}
}

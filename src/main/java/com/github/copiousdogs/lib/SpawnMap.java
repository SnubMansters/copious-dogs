package com.github.copiousdogs.lib;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.EntityLiving;
import net.minecraftforge.common.BiomeDictionary.Type;

public class SpawnMap
{
	static HashMap<Type, ArrayList<Class<? extends EntityLiving>>> spawnMap = new HashMap<Type, ArrayList<Class<? extends EntityLiving>>>();
	
	public static void registerSpawnBiomes(Class<? extends EntityLiving> clazz, Type... types)
	{
		for (int i = 0; i < types.length; i++)
		{
			Type type = types[i];
			ArrayList<Class<? extends EntityLiving>> list = spawnMap.get(type);
			
			if (list == null)
			{
				list = new ArrayList<Class<? extends EntityLiving>>();
			}
			
			list.add(clazz);
			spawnMap.put(type, list);
		}
	}
	
	public static ArrayList<Class<? extends EntityLiving>> getClassesFromType(Type type)
	{
		return spawnMap.get(type);
	}
}

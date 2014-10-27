package com.github.copiousdogs.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.github.copiousdogs.lib.Reference;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static boolean INDIVIDUAL_TRAITS;
	public static int DOG_SPAWN_PROB;
	
	public static Configuration configuration;
	
	public static void init(File configFile)
	{
		
		if (configuration == null) {
			
			configuration = new Configuration(configFile);
		}
		
		load();
	}
	
	public static void load() {

		INDIVIDUAL_TRAITS = configuration.get(Configuration.CATEGORY_GENERAL, "randomized traits", true, "Tells wether the dogs should have randomized individual traits or not").getBoolean(true);
		DOG_SPAWN_PROB = configuration.get(Configuration.CATEGORY_GENERAL, "dog spawn probability", 10, "The weighted probability value for dog spawning. Higher value means more frequent spawning. Set to 0 to disable spawning.").getInt();

		if (configuration.hasChanged()) {
				
			configuration.save();
		}
	}
}

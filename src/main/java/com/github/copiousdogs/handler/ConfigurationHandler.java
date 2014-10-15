package com.github.copiousdogs.handler;

import java.io.File;

import com.github.copiousdogs.lib.Reference;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
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

			
		INDIVIDUAL_TRAITS = configuration.getBoolean("randomized traits", Configuration.CATEGORY_GENERAL, true, "Tells wether the dogs should have randomized individual traits or not");
		DOG_SPAWN_PROB = configuration.getInt("dog spawn probability", Configuration.CATEGORY_GENERAL, 10, 0, 100, "The weighted probability value for dog spawning. Higher value means more frequent spawning. Set to 0 to disable spawning.");

		if (configuration.hasChanged()) {
				
			configuration.save();
		}
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent event) {
		
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			
			load();
		}
	}
}
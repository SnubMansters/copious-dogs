package com.github.copiousdogs.client.gui;

import java.util.List;

import com.github.copiousdogs.handler.ConfigurationHandler;
import com.github.copiousdogs.lib.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;

public class GuiConfigCopiousDogs extends GuiConfig {

	public GuiConfigCopiousDogs(GuiScreen parentScreen) {
		
		super(parentScreen, 
				new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
				Reference.MOD_ID,
				false,
				false,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	}
}

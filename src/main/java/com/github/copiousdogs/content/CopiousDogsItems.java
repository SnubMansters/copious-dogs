package com.github.copiousdogs.content;

import com.github.copiousdogs.item.ItemCopiousDogs;
import com.github.copiousdogs.item.ItemDogBiscuit;
import com.github.copiousdogs.item.ItemDogCollar;
import com.github.copiousdogs.item.ItemLeash;

import cpw.mods.fml.common.registry.GameRegistry;

public class CopiousDogsItems {

	public static ItemCopiousDogs dogBiscuit = new ItemDogBiscuit();
	public static ItemCopiousDogs dogCollar = new ItemDogCollar();
	public static ItemCopiousDogs leash = new ItemLeash();
	
	public static void init() {
		
		GameRegistry.registerItem(dogBiscuit, "dogBiscuit");
		GameRegistry.registerItem(dogCollar, "dogCollar");
		GameRegistry.registerItem(leash, "leash");
	}
}

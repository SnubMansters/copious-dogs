package com.github.copiousdogs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.item.Item;

import com.github.copiousdogs.client.model.entity.ModelDog;
import com.github.copiousdogs.content.CopiousDogsBlocks;
import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.content.CopiousDogsTileEntities;
import com.github.copiousdogs.entity.EntityBeagle;
import com.github.copiousdogs.entity.EntityBerneseMountain;
import com.github.copiousdogs.lib.Reference;
import com.github.copiousdogs.network.DogDishHandler;
import com.github.copiousdogs.network.MessageDogDish;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=Reference.MOD_ID, name="Copious Dogs", version=Reference.VERSION)
public class CopiousDogs 
{

	@Instance("copious_dogs")
	public static CopiousDogs instance;
	
	@SidedProxy(clientSide = "com.github.copiousdogs.client.ClientProxy",
			serverSide = "com.github.copiousdogs.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs tabCopiousDogs = new CreativeTabs("tabCopiousDogs")
	{
		@Override
		public Item getTabIconItem() 
		{
			return CopiousDogsItems.dogBiscuit;
		}
	};
	
	public static SimpleNetworkWrapper snw;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.CHANNEL_NAME);
		snw.registerMessage(DogDishHandler.class, MessageDogDish.class, 0, Side.CLIENT);
		
		CopiousDogsItems.init();
		CopiousDogsBlocks.init();
		CopiousDogsTileEntities.init();
		
		ModelDog.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{	
		EntityRegistry.registerModEntity(EntityBeagle.class, "beagle", 0, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(0, EntityBeagle.class);
		EntityList.entityEggs.put(0, new EntityEggInfo(0, 0xCE935F, 0x685043));
		
		EntityRegistry.registerModEntity(EntityBerneseMountain.class, "bernese_mountain", 1, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(1, EntityBerneseMountain.class);
		EntityList.entityEggs.put(1, new EntityEggInfo(1, 0xC7A087, 0x9E7F6B));
		
		proxy.registerRenderers();
		proxy.registerRecipes();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		
	}
}

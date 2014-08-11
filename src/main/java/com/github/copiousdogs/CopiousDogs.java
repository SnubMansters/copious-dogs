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
import com.github.copiousdogs.entity.EntityChihuahua;
import com.github.copiousdogs.entity.EntityDalmatian;
import com.github.copiousdogs.entity.EntityFrenchBulldog;
import com.github.copiousdogs.entity.EntityGermanShepherd;
import com.github.copiousdogs.entity.EntityGoldenRetriever;
import com.github.copiousdogs.entity.EntityHusky;
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
		registerEntities();
		
		proxy.registerRenderers();
		proxy.registerRecipes();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		
	}
	
	private void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityBeagle.class, "beagle", 0, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(0, EntityBeagle.class);
		EntityList.entityEggs.put(0, new EntityEggInfo(0, 0xCE935F, 0x685043));
		
		EntityRegistry.registerModEntity(EntityBerneseMountain.class, "bernese_mountain", 1, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(1, EntityBerneseMountain.class);
		EntityList.entityEggs.put(1, new EntityEggInfo(1, 0xC7A087, 0x9E7F6B));
		
		EntityRegistry.registerModEntity(EntityGoldenRetriever.class, "golden_retriever", 2, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(2, EntityGoldenRetriever.class);
		EntityList.entityEggs.put(2, new EntityEggInfo(2, 0xBC8E5F, 0xDDCDB6));
		
		EntityRegistry.registerModEntity(EntityHusky.class, "husky", 3, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(3, EntityHusky.class);
		EntityList.entityEggs.put(3, new EntityEggInfo(3, 0x2B2E2D, 0x7E807D));
		
		EntityRegistry.registerModEntity(EntityChihuahua.class, "chihuahua", 4, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(4, EntityChihuahua.class);
		EntityList.entityEggs.put(4, new EntityEggInfo(4, 0xC7A087, 0x9E7F6B));
		
		EntityRegistry.registerModEntity(EntityFrenchBulldog.class, "french_bulldog", 5, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(5, EntityFrenchBulldog.class);
		EntityList.entityEggs.put(5, new EntityEggInfo(5, 0x151618, 0xBDBDB7));
		
		EntityRegistry.registerModEntity(EntityGermanShepherd.class, "german_shepherd", 6, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(6, EntityGermanShepherd.class);
		EntityList.entityEggs.put(6, new EntityEggInfo(6, 0xAD754F, 0x17141B));
		
		EntityRegistry.registerModEntity(EntityDalmatian.class, "dalmatian", 7, this, 40, 1, true);
		EntityList.IDtoClassMapping.put(7, EntityDalmatian.class);
		EntityList.entityEggs.put(7, new EntityEggInfo(7, 0xFFFFFF, 0x000000));
	}
}

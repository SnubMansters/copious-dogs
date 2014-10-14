package com.github.copiousdogs;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import com.github.copiousdogs.client.model.entity.ModelDog;
import com.github.copiousdogs.content.CopiousDogsBlocks;
import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.content.CopiousDogsTileEntities;
import com.github.copiousdogs.entity.EntityBeagle;
import com.github.copiousdogs.entity.EntityBerneseMountain;
import com.github.copiousdogs.entity.EntityBoxer;
import com.github.copiousdogs.entity.EntityCardiganCorgi;
import com.github.copiousdogs.entity.EntityChihuahua;
import com.github.copiousdogs.entity.EntityCollie;
import com.github.copiousdogs.entity.EntityDalmatian;
import com.github.copiousdogs.entity.EntityDoberman;
import com.github.copiousdogs.entity.EntityFrenchBulldog;
import com.github.copiousdogs.entity.EntityGermanShepherd;
import com.github.copiousdogs.entity.EntityGoldenRetriever;
import com.github.copiousdogs.entity.EntityGreatDane;
import com.github.copiousdogs.entity.EntityHusky;
import com.github.copiousdogs.entity.EntityPomeranian;
import com.github.copiousdogs.entity.EntityPoodle;
import com.github.copiousdogs.entity.EntityPug;
import com.github.copiousdogs.entity.EntitySaintBernard;
import com.github.copiousdogs.entity.EntityYorkshire;
import com.github.copiousdogs.lib.ConfigInfo;
import com.github.copiousdogs.lib.Reference;
import com.github.copiousdogs.lib.SpawnMap;
import com.github.copiousdogs.network.DogDishHandler;
import com.github.copiousdogs.network.MessageDogDish;

import cpw.mods.fml.common.FMLCommonHandler;
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
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		ConfigInfo.INDIVIDUAL_TRAITS = config.get("Dog Behavior", "randomized traits", true, 
				"Tells wether the dogs should have randomized individual traits or not").getBoolean(true);
		ConfigInfo.DOG_SPAWN_PROB = config.get("Spawning", "dog spawn probability", 10, 
				"The weighted probability value for dog spawning. Higher value means more frequent spawning. Set to 0 to disable spawning.").getInt();
		
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.CHANNEL_NAME);
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) 
		{
			snw.registerMessage(DogDishHandler.class, MessageDogDish.class, 0, Side.CLIENT);
		}
		
		CopiousDogsItems.init();
		CopiousDogsBlocks.init();
		CopiousDogsTileEntities.init();
		
		ModelDog.init();
		
		SpawnMap.registerSpawnBiomes(EntityGermanShepherd.class, Type.PLAINS);
		SpawnMap.registerSpawnBiomes(EntityChihuahua.class, Type.PLAINS);
		SpawnMap.registerSpawnBiomes(EntityFrenchBulldog.class, Type.PLAINS);
		SpawnMap.registerSpawnBiomes(EntityGoldenRetriever.class, Type.PLAINS);
		SpawnMap.registerSpawnBiomes(EntityBoxer.class, Type.PLAINS);
		SpawnMap.registerSpawnBiomes(EntityYorkshire.class, Type.PLAINS);
		SpawnMap.registerSpawnBiomes(EntityCollie.class, Type.PLAINS, Type.FOREST, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityPoodle.class, Type.PLAINS, Type.FOREST, Type.HILLS);
		SpawnMap.registerSpawnBiomes(EntityBeagle.class, Type.FOREST);
		SpawnMap.registerSpawnBiomes(EntityDalmatian.class, Type.FOREST);
		SpawnMap.registerSpawnBiomes(EntityDoberman.class, Type.FOREST);
		SpawnMap.registerSpawnBiomes(EntityPomeranian.class, Type.FOREST);
		SpawnMap.registerSpawnBiomes(EntityPug.class, Type.FOREST);
		SpawnMap.registerSpawnBiomes(EntityBerneseMountain.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityGreatDane.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityCardiganCorgi.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntitySaintBernard.class, Type.HILLS, Type.MOUNTAIN);
		SpawnMap.registerSpawnBiomes(EntityHusky.class, Type.FROZEN);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{	
		registerEntities();
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			proxy.registerRenderers();
		}
		proxy.registerRecipes();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		BiomeDictionary.registerAllBiomes();
		
		for (Type type : Type.values())
		{
			BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(type);
			ArrayList<Class<? extends EntityLiving>> classes = SpawnMap.getClassesFromType(type);
			
			if (classes != null) 
			{
				for (BiomeGenBase biome : biomes)
				{
					for (Class<? extends EntityLiving> clazz : classes)
					{
						EntityRegistry.addSpawn(clazz, ConfigInfo.DOG_SPAWN_PROB, 2, 6, EnumCreatureType.creature, biome);
					}
				}
			}
		}
	}
	
	private void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityBeagle.class, "beagle", 0, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(0, EntityBeagle.class);
		EntityList.entityEggs.put(0, new EntityEggInfo(0, 0xCE935F, 0x685043));
		
		EntityRegistry.registerModEntity(EntityBerneseMountain.class, "bernese_mountain", 1, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(1, EntityBerneseMountain.class);
		EntityList.entityEggs.put(1, new EntityEggInfo(1, 0x0B0C12, 0x723510));
		
		EntityRegistry.registerModEntity(EntityGoldenRetriever.class, "golden_retriever", 2, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(2, EntityGoldenRetriever.class);
		EntityList.entityEggs.put(2, new EntityEggInfo(2, 0xBC8E5F, 0xDDCDB6));
		
		EntityRegistry.registerModEntity(EntityHusky.class, "husky", 3, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(3, EntityHusky.class);
		EntityList.entityEggs.put(3, new EntityEggInfo(3, 0x2B2E2D, 0x7E807D));
		
		EntityRegistry.registerModEntity(EntityChihuahua.class, "chihuahua", 4, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(4, EntityChihuahua.class);
		EntityList.entityEggs.put(4, new EntityEggInfo(4, 0xC7A087, 0x9E7F6B));
		
		EntityRegistry.registerModEntity(EntityFrenchBulldog.class, "french_bulldog", 5, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(5, EntityFrenchBulldog.class);
		EntityList.entityEggs.put(5, new EntityEggInfo(5, 0x151618, 0xBDBDB7));
		
		EntityRegistry.registerModEntity(EntityGermanShepherd.class, "german_shepherd", 6, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(6, EntityGermanShepherd.class);
		EntityList.entityEggs.put(6, new EntityEggInfo(6, 0xAD754F, 0x17141B));
		
		EntityRegistry.registerModEntity(EntityDalmatian.class, "dalmatian", 7, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(7, EntityDalmatian.class);
		EntityList.entityEggs.put(7, new EntityEggInfo(7, 0xFFFFFF, 0x000000));
		
		EntityRegistry.registerModEntity(EntityGreatDane.class, "great_dane", 8, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(8, EntityGreatDane.class);
		EntityList.entityEggs.put(8, new EntityEggInfo(8, 0xDFB188, 0xC79B69));
		
		EntityRegistry.registerModEntity(EntityBoxer.class, "boxer", 9, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(9, EntityBoxer.class);
		EntityList.entityEggs.put(9, new EntityEggInfo(9, 0x866331, 0xB7B1A8));
		
		EntityRegistry.registerModEntity(EntityCardiganCorgi.class, "cardigan_corgi", 10, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(10, EntityCardiganCorgi.class);
		EntityList.entityEggs.put(10, new EntityEggInfo(10, 0xA46F43, 0x827A72));
		
		EntityRegistry.registerModEntity(EntityCollie.class, "collie", 11, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(11, EntityCollie.class);
		EntityList.entityEggs.put(11, new EntityEggInfo(11, 0x9F653D, 0xDAD9DB));
		
		EntityRegistry.registerModEntity(EntityDoberman.class, "doberman", 12, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(12, EntityDoberman.class);
		EntityList.entityEggs.put(12, new EntityEggInfo(12, 0x1C1B1B, 0x7D462D));
		
		EntityRegistry.registerModEntity(EntityPomeranian.class, "pomeranian", 13, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(13, EntityPomeranian.class);
		EntityList.entityEggs.put(13, new EntityEggInfo(13, 0x9C531B, 0xC0854A));
		
		EntityRegistry.registerModEntity(EntityPoodle.class, "poodle", 14, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(14, EntityPoodle.class);
		EntityList.entityEggs.put(14, new EntityEggInfo(14, 0xC2C7D4, 0xD7DADF));
		
		EntityRegistry.registerModEntity(EntityPug.class, "pug", 15, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(15, EntityPug.class);
		EntityList.entityEggs.put(15, new EntityEggInfo(15, 0xCDA27F, 0xD2B094));
		
		EntityRegistry.registerModEntity(EntitySaintBernard.class, "saint_bernard", 16, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(16, EntitySaintBernard.class);
		EntityList.entityEggs.put(16, new EntityEggInfo(16, 0x793F1F, 0xC6BEAA));
		
		EntityRegistry.registerModEntity(EntityYorkshire.class, "yorkshire", 17, this, 40, 3, true);
		EntityList.IDtoClassMapping.put(17, EntityYorkshire.class);
		EntityList.entityEggs.put(17, new EntityEggInfo(17, 0x1F1E1D, 0x805234));
	}
}

package com.github.copiousdogs.client.model.entity;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import com.github.copiousdogs.entity.EntityDog;

public class ModelDog extends ModelBase
{
	private static HashMap<String, ModelDog> breedModels = new HashMap<String, ModelDog>();
	public ArrayList<ModelRenderer> parts = new ArrayList<ModelRenderer>();
	
	ModelRenderer WolfHead;
	ModelRenderer Body;
	ModelRenderer Mane;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Tail;
	ModelRenderer Ear1;
	ModelRenderer Ear2;
	ModelRenderer Nose;
	
	public static void init()
	{
		new ModelBeagle();
		new ModelBeaglePup();
		new ModelBerneseMountain();
		new ModelBerneseMountainPup();
		new ModelChihuahua();
		new ModelChihuahuaPup();
		new ModelDalmatian();
		new ModelDalmatianPup();
		new ModelFrenchBulldog();
		new ModelFrenchBulldogPup();
		new ModelGermanShepherd();
		new ModelGermanShepherdPup();
		new ModelGoldenRetriever();
		new ModelGoldenRetrieverPup();
		new ModelGreatDane();
		new ModelGreatDanePup();
		new ModelHusky();
		new ModelHuskyPup();
	}
	
	public ModelDog() {};
	
	public ModelDog(String breed)
	{
		breedModels.put(breed, this);
		parts.add(WolfHead);
		parts.add(Body);
		parts.add(Mane);
		parts.add(Leg1);
		parts.add(Leg2);
		parts.add(Leg3);
		parts.add(Leg4);
		parts.add(Tail);
		parts.add(Ear1);
		parts.add(Ear2);
		parts.add(Nose);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		if (entity instanceof EntityDog)
		{
			EntityDog dog = (EntityDog) entity;
			breedModels.get(dog.getTextureName()).render(entity, f, f1, f2, f3, f4, f5);
		}	
	}

	protected void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setLivingAnimations(EntityDog entity, float walkTime,
			float isWalking, float random)
	{
		ModelDog model = breedModels.get(entity.getTextureName());
		
		if (entity.isTailAnimated())
		{
			model.Tail.rotateAngleY = MathHelper.cos(entity.ticksExisted * 0.8F) * 0.6F;
		}
		else
		{
			model.Tail.rotateAngleY = 0;
		}
		
		model.Leg1.rotateAngleX = MathHelper.cos(walkTime * 0.6662F) * 1.4F * isWalking;
		model.Leg2.rotateAngleX = MathHelper.cos(walkTime * 0.6662F + (float) Math.PI) * 1.4F * isWalking;
		model.Leg3.rotateAngleX = MathHelper.cos(walkTime * 0.6662F + (float) Math.PI) * 1.4F * isWalking;
		model.Leg4.rotateAngleX = MathHelper.cos(walkTime * 0.6662F) * 1.4F * isWalking;
		
		super.setLivingAnimations(entity, walkTime, isWalking, random);
	}
	
	public static ModelDog getModelFromBreed(String breed)
	{
		return breedModels.get(breed);
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float walkTime,
			float isWalking, float random)
	{
		setLivingAnimations((EntityDog)entity, walkTime, isWalking, random);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}

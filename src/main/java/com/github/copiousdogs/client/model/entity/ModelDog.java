package com.github.copiousdogs.client.model.entity;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import com.github.copiousdogs.entity.EntityDog;

public class ModelDog extends ModelBase
{
	private static HashMap<String, ModelDog> breedModels = new HashMap<String, ModelDog>();
	
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
	
	float tailRotX, tailRotY, tailRotZ;
	float headRotX, headRotY, headRotZ;
	float offsetX, offsetY, offsetZ;
	
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
		new ModelBoxer();
		new ModelBoxerPup();
		new ModelCardiganCorgi();
		new ModelCardiganCorgiPup();
		new ModelCollie();
		new ModelColliePup();
		new ModelDoberman();
		new ModelDobermanPup();
		new ModelPomeranian();
		new ModelPomeranianPup();
		new ModelPoodle();
		new ModelPoodlePup();
		new ModelPug();
		new ModelPugPup();
		new ModelSaintBernard();
		new ModelSaintBernardPup();
		new ModelYorkshire();
		new ModelYorkshirePup();
	}
	
	public ModelDog() {};
	
	public ModelDog(String breed)
	{
		breedModels.put(breed, this);
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
			model.tailRotY = MathHelper.cos(entity.ticksExisted * 0.8F) * 0.6F;
		}
		else
		{
			model.tailRotY = 0;
		}
		
		if (entity.isBegging())
		{
			model.headRotZ = (float)Math.PI / 8.0f;
		}
		else
		{
			model.headRotZ = 0f;
		}
		
		if (entity.isSitting()) {

			ModelBox box = (ModelBox)model.Leg1.cubeList.get(0);
			
			model.offsetY = (box.posY2 - box.posY1) - 1;
			
			model.Leg1.rotateAngleX = -90f / 180f * (float)Math.PI;
			model.Leg2.rotateAngleX = -90f / 180f * (float)Math.PI;
			model.Leg3.rotateAngleX = -90f / 180f * (float)Math.PI;
			model.Leg4.rotateAngleX = -90f / 180f * (float)Math.PI;
			model.Leg1.rotateAngleY = (float)Math.PI / 8f;
			model.Leg2.rotateAngleY = -(float)Math.PI / 8f;
		}
		else {
			
			model.offsetY = 0;
			
			model.Leg1.rotateAngleX = MathHelper.cos(walkTime * 0.6662F) * 1.4F * isWalking;
			model.Leg2.rotateAngleX = MathHelper.cos(walkTime * 0.6662F + (float) Math.PI) * 1.4F * isWalking;
			model.Leg3.rotateAngleX = MathHelper.cos(walkTime * 0.6662F + (float) Math.PI) * 1.4F * isWalking;
			model.Leg4.rotateAngleX = MathHelper.cos(walkTime * 0.6662F) * 1.4F * isWalking;
			model.Leg1.rotateAngleY = 0f;
			model.Leg2.rotateAngleY = 0f;
		}
			
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
		
		ModelDog model = breedModels.get(((EntityDog)entity).getTextureName());
		model.headRotX = f4 / (180f / (float) Math.PI);
		model.headRotY = f3 / (180f / (float) Math.PI);
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}

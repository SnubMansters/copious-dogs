package com.github.copiousdogs.client.model.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGermanShepherdPup extends ModelDog
{
	public ModelGermanShepherdPup()
	{
		super("german_shepherd_pup");
		textureWidth = 64;
		textureHeight = 32;

		WolfHead = new ModelRenderer(this, 0, 0);
		WolfHead.addBox(-3F, -3F, -2F, 6, 6, 4);
		WolfHead.setRotationPoint(0.5F, 16.5F, -7F);
		WolfHead.setTextureSize(64, 32);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 22, 11);
		Body.addBox(-4F, -2F, -3F, 3, 7, 3);
		Body.setRotationPoint(3F, 17F, -4F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 1.570796F, 0F, 0F);
		Mane = new ModelRenderer(this, 21, 0);
		Mane.addBox(-4F, -3F, -4F, 3, 5, 1);
		Mane.setRotationPoint(3F, 17F, -3F);
		Mane.setTextureSize(64, 32);
		Mane.mirror = true;
		setRotation(Mane, 1.570796F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 0, 19);
		Leg1.addBox(-1F, 0F, -1F, 1, 4, 1);
		Leg1.setRotationPoint(0F, 20F, 0.5F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 0, 19);
		Leg2.addBox(-1F, 0F, -1F, 1, 4, 1);
		Leg2.setRotationPoint(2F, 20F, 0.5F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 0, 19);
		Leg3.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg3.setRotationPoint(0F, 21F, -4F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 0, 19);
		Leg4.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg4.setRotationPoint(2F, 21F, -4F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 9, 19);
		Tail.addBox(-1F, 0F, -1F, 1, 5, 1);
		Tail.setRotationPoint(1F, 17.3F, 1F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 0.5723901F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 15, 14);
		Ear1.addBox(-3F, -5F, 0F, 2, 2, 1);
		Ear1.setRotationPoint(0.5F, 16.5F, -7F);
		Ear1.setTextureSize(64, 32);
		Ear1.mirror = true;
		setRotation(Ear1, 0F, 0F, 0F);
		Ear2 = new ModelRenderer(this, 15, 14);
		Ear2.addBox(1F, -5F, 0F, 2, 2, 1);
		Ear2.setRotationPoint(0.5F, 16.5F, -7F);
		Ear2.setTextureSize(64, 32);
		Ear2.mirror = true;
		setRotation(Ear2, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 11);
		Nose.addBox(-2F, 0F, -5F, 3, 3, 4);
		Nose.setRotationPoint(1F, 16.5F, -7F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5)
	{
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		WolfHead.render(f5);
		Body.render(f5);
		Mane.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Tail.render(f5);
		Ear1.render(f5);
		Ear2.render(f5);
		Nose.render(f5);
	}
}

package com.github.copiousdogs.client.model.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFrenchBulldog extends ModelDog
{
	public ModelFrenchBulldog()
	{
		super("french_bulldog");
		textureWidth = 64;
		textureHeight = 32;

		WolfHead = new ModelRenderer(this, 0, 0);
		WolfHead.addBox(-3F, -3F, -2F, 6, 5, 4);
		WolfHead.setRotationPoint(-1F, 17.5F, -7F);
		WolfHead.setTextureSize(64, 32);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 21, 0);
		Body.addBox(-4F, -2F, -3F, 6, 7, 5);
		Body.setRotationPoint(0F, 17F, -3F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 1.570796F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 0, 15);
		Leg1.addBox(-1F, 0F, -1F, 2, 5, 2);
		Leg1.setRotationPoint(-2.5F, 19F, 1F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 0, 15);
		Leg2.addBox(-1F, 0F, -1F, 2, 5, 2);
		Leg2.setRotationPoint(0.5F, 19F, 1F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 0, 15);
		Leg3.addBox(-1F, 0F, -1F, 2, 5, 2);
		Leg3.setRotationPoint(-2.5F, 19F, -4F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 0, 15);
		Leg4.addBox(-1F, 0F, -1F, 2, 5, 2);
		Leg4.setRotationPoint(0.5F, 19F, -4F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 9, 14);
		Tail.addBox(-1F, 0F, -1F, 2, 4, 2);
		Tail.setRotationPoint(-1F, 16.5F, 0F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 1.762106F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 9, 10);
		Ear1.addBox(-3F, -5F, 0F, 2, 2, 1);
		Ear1.setRotationPoint(-1F, 17.5F, -7F);
		Ear1.setTextureSize(64, 32);
		Ear1.mirror = true;
		setRotation(Ear1, 0F, 0F, 0F);
		Ear2 = new ModelRenderer(this, 9, 10);
		Ear2.addBox(1F, -5F, 0F, 2, 2, 1);
		Ear2.setRotationPoint(-1F, 17.5F, -7F);
		Ear2.setTextureSize(64, 32);
		Ear2.mirror = true;
		setRotation(Ear2, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 10);
		Nose.addBox(-2F, 0F, -5F, 3, 3, 1);
		Nose.setRotationPoint(-0.5F, 16.5F, -5F);
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
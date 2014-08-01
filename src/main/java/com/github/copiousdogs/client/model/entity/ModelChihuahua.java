package com.github.copiousdogs.client.model.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChihuahua extends ModelDog
{
	public ModelChihuahua()
	{
		super("chihuahua");
		textureWidth = 64;
		textureHeight = 32;

		WolfHead = new ModelRenderer(this, 0, 0);
		WolfHead.addBox(-3F, -3F, -2F, 4, 4, 3);
		WolfHead.setRotationPoint(1F, 19F, -3F);
		WolfHead.setTextureSize(64, 32);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 16, 6);
		Body.addBox(-4F, -2F, -3F, 4, 6, 3);
		Body.setRotationPoint(2F, 18F, -1F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 1.570796F, 0F, 0F);
		Mane = new ModelRenderer(this, 15, 0);
		Mane.addBox(-4F, -3F, -3F, 4, 4, 1);
		Mane.setRotationPoint(2F, 19F, 0F);
		Mane.setTextureSize(64, 32);
		Mane.mirror = true;
		setRotation(Mane, 1.570796F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 0, 13);
		Leg1.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg1.setRotationPoint(-0.5F, 21F, 2.5F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 0, 13);
		Leg2.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg2.setRotationPoint(1.5F, 21F, 2.5F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 0, 13);
		Leg3.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg3.setRotationPoint(-0.5F, 21F, -1F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 0, 13);
		Leg4.addBox(-1F, 0F, -1F, 1, 3, 1);
		Leg4.setRotationPoint(1.5F, 21F, -1F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 5, 13);
		Tail.addBox(-1F, 0F, -1F, 1, 3, 1);
		Tail.setRotationPoint(0.5F, 18.4F, 3F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 1.130069F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 10, 11);
		Ear1.addBox(-3F, -5F, 0F, 1, 2, 1);
		Ear1.setRotationPoint(2F, 18.7F, -4.5F);
		Ear1.setTextureSize(64, 32);
		Ear1.mirror = true;
		setRotation(Ear1, 0F, 0F, -0.3520644F);
		Ear2 = new ModelRenderer(this, 9, 8);
		Ear2.addBox(1F, -5F, 0F, 2, 1, 1);
		Ear2.setRotationPoint(5.1F, 19.4F, -4.5F);
		Ear2.setTextureSize(64, 32);
		Ear2.mirror = true;
		setRotation(Ear2, 0F, 0F, -1.189716F);
		Nose = new ModelRenderer(this, 0, 8);
		Nose.addBox(-2F, 0F, -5F, 2, 2, 2);
		Nose.setRotationPoint(1F, 18F, -1.5F);
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

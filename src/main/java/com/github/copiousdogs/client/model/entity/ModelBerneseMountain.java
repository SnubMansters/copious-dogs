package com.github.copiousdogs.client.model.entity;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBerneseMountain extends ModelDog
{
	public ModelBerneseMountain()
	{
		super("bernese_mountain");
		textureWidth = 64;
		textureHeight = 32;

		WolfHead = new ModelRenderer(this, 0, 20);
		WolfHead.addBox(-3F, -3F, -2F, 6, 6, 6);
		WolfHead.setRotationPoint(-1F, 8.5F, -9F);
		WolfHead.setTextureSize(64, 32);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 32, 17);
		Body.addBox(-4F, -2F, -3F, 8, 7, 8);
		Body.setRotationPoint(-1F, 13.8F, 5F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 1.570796F, 0F, 0F);
		Mane = new ModelRenderer(this, 0, 0);
		Mane.addBox(-4F, -3F, -3F, 8, 9, 10);
		Mane.setRotationPoint(-1F, 15F, -3F);
		Mane.setTextureSize(64, 32);
		Mane.mirror = true;
		setRotation(Mane, 1.570796F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 51, 6);
		Leg1.addBox(-1F, 0F, -1F, 3, 8, 3);
		Leg1.setRotationPoint(-3.5F, 16F, 8F);
		Leg1.setTextureSize(64, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 51, 6);
		Leg2.addBox(-1F, 0F, -1F, 3, 8, 3);
		Leg2.setRotationPoint(0.5F, 16F, 8F);
		Leg2.setTextureSize(64, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 51, 6);
		Leg3.addBox(-1F, 0F, -1F, 3, 8, 3);
		Leg3.setRotationPoint(-3.5F, 16F, -4F);
		Leg3.setTextureSize(64, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 51, 6);
		Leg4.addBox(-1F, 0F, -1F, 3, 8, 3);
		Leg4.setRotationPoint(0.5F, 16F, -4F);
		Leg4.setTextureSize(64, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 23, 20);
		Tail.addBox(-1F, 0F, -1F, 2, 10, 2);
		Tail.setRotationPoint(-1F, 9.7F, 9F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 0.6095687F, 0F, 0F);
		Ear1 = new ModelRenderer(this, 56, 0);
		Ear1.addBox(-3F, -5F, 0F, 1, 3, 3);
		Ear1.setRotationPoint(-2F, 10.5F, -9F);
		Ear1.setTextureSize(64, 32);
		Ear1.mirror = true;
		setRotation(Ear1, 0F, 0F, 0.0174533F);
		Ear2 = new ModelRenderer(this, 56, 0);
		Ear2.addBox(1F, -5F, 0F, 1, 3, 3);
		Ear2.setRotationPoint(1F, 10.5F, -9F);
		Ear2.setTextureSize(64, 32);
		Ear2.mirror = true;
		setRotation(Ear2, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 36, 0);
		Nose.addBox(-2F, 0F, -5F, 3, 3, 4);
		Nose.setRotationPoint(-0.5F, 8.5F, -9F);
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

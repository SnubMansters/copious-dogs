package com.github.copiousdogs.client.render.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import com.github.copiousdogs.entity.EntityDog;
import com.github.copiousdogs.item.ItemDogCollar;
import com.github.copiousdogs.lib.Reference;

public class RenderDog extends RenderLiving
{

	public RenderDog(ModelBase model, ModelBase renderPass, float f)
	{
		super(model, f);
		this.setRenderPassModel(renderPass);
	}

	@Override
	protected int inheritRenderPass(EntityLivingBase entity, int i0, float f0)
	{
		if (entity instanceof EntityDog)
		{
			EntityDog dog = (EntityDog) entity;
			
			float f1;
			if (i0 == 1 && dog.isTamed() && dog.hasCollar())
			{
				bindTexture(getDogCollarTexture(dog));
				f1 = 1.0f;
				int j = ItemDogCollar.getItemFromDye(dog.getCollarColor());
				float[] color = EntitySheep.fleeceColorTable[j];
				GL11.glColor3f(f1 * color[0], f1 * color[1], f1 * color[2]);
				return 1;
			}
		}
		return -1;
	}
	
	@Override
	public void doRender(EntityLiving entity, double d0, double d1,
			double d2, float f0, float f1)
	{
		super.doRender(entity, d0, d1, d2, f0, f1);
		
		EntityDog dog = (EntityDog) entity;
	}
	
	@Override
	protected int shouldRenderPass(EntityLivingBase entity, int i0, float f0)
	{
		return this.inheritRenderPass((EntityDog)entity, i0, f0);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if (entity instanceof EntityDog)
		{
			return new ResourceLocation(Reference.MOD_ID + ":textures/entities/" + ((EntityDog)entity).getTextureName() + ".png");
		}
		return null;
	}
	
	protected ResourceLocation getDogCollarTexture(EntityDog entity)
	{
		return new ResourceLocation(Reference.MOD_ID + ":textures/entities/" + ((EntityDog)entity).getTextureName() + "_collar.png");
	}
}

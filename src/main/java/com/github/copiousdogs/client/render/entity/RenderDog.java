package com.github.copiousdogs.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

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
	public void doRender(EntityLiving entity, double d0, double d1, double d2,
			float f0, float f1)
	{
		super.doRender(entity, d0, d1, d2, f0, f1);

		EntityDog dog = (EntityDog) entity;
		
		if (dog.hasLeash()) 
		{
			
			float f9 = ((EntityLivingBase) dog.getOwner()).getSwingProgress(f1);
	        float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * (float)Math.PI);

	        Vec3 vec3 = Vec3.createVectorHelper(-.03D, 0D, -.03D);
	        vec3.rotateAroundX(-(dog.getOwner().prevRotationPitch + (dog.getOwner().rotationPitch - dog.getOwner().prevRotationPitch) * f1) * (float)Math.PI / 180.0F);
	        vec3.rotateAroundY(-(dog.getOwner().prevRotationYaw + (dog.getOwner().rotationYaw - dog.getOwner().prevRotationYaw) * f1) * (float)Math.PI / 180.0F);
	        vec3.rotateAroundY(f10 * 0.5F);
	        vec3.rotateAroundX(-f10 * 0.7F);
			
			double d3 = dog.getOwner().prevPosX + (dog.getOwner().posX - dog.getOwner().prevPosX) * (double)f1 + vec3.xCoord;
	        double d4 = dog.getOwner().prevPosY + (dog.getOwner().posY - dog.getOwner().prevPosY) * (double)f1 + vec3.yCoord;
	        double d5 = dog.getOwner().prevPosZ + (dog.getOwner().posZ - dog.getOwner().prevPosZ) * (double)f1 + vec3.zCoord;
			
			double d9 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)f1;
	        double d10 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)f1 + 0.5D;
	        double d11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)f1;
	        double d12 = (double)((float)(d3 - d9));
	        double d13 = (double)((float)(d4 - d10));
	        double d14 = (double)((float)(d5 - d11));
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        
	        Tessellator tessellator = Tessellator.instance;
	 
	        tessellator.startDrawing(3);
	        tessellator.setColorOpaque_I(0);
	        byte b2 = 16;
	
	        for (int i = 0; i <= b2; ++i)
	        {
	            float f12 = (float)i / (float)b2;
	            tessellator.addVertex(d0 + d12 * (double)f12, d1 + d13 * (double)(f12 * f12 + f12) * 0.5D + 0.25D, d2 + d14 * (double)f12);
	        }
	        
	        tessellator.draw();
	        
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glEnable(GL11.GL_LIGHTING);
		}
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entity, int i0, float f0)
	{
		return this.inheritRenderPass((EntityDog) entity, i0, f0);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		if (entity instanceof EntityDog)
		{
			return new ResourceLocation(Reference.MOD_ID
					+ ":textures/entities/"
					+ ((EntityDog) entity).getTextureName() + ".png");
		}
		return null;
	}

	protected ResourceLocation getDogCollarTexture(EntityDog entity)
	{
		return new ResourceLocation(Reference.MOD_ID + ":textures/entities/"
				+ ((EntityDog) entity).getTextureName() + "_collar.png");
	}
}

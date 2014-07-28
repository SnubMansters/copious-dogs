package com.github.copiousdogs.client.model.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.github.copiousdogs.block.BlockDogDish;
import com.github.copiousdogs.lib.Reference;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.client.FMLClientHandler;

public class ModelDogDish extends ModelBase
{
	private IModelCustom model;
	
	public ModelDogDish()
	{
		model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.MOD_ID + ":" + "models/dog_dish_model.obj"));
	}
	
	public void render()
	{
		model.renderPart("Dish");
	}
	
	public void render(TileEntityDogDish tileEntity, float par2, float par3, float par4)
	{
		if (tileEntity.blockMetadata < 0 || tileEntity.blockMetadata >= 16) return;
		GL11.glPushMatrix();
		GL11.glTranslatef(par2, par3, par4 + 1f);
		GL11.glScalef(1, 1, 1);
		
		float[] colors = EntitySheep.fleeceColorTable[tileEntity.blockMetadata];
		GL11.glColor3f(colors[0], colors[1], colors[2]);
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(
				new ResourceLocation(Reference.MOD_ID + ":textures/blocks/" + BlockDogDish.getTexture() + ".png"));
		
		render();
		
		if (tileEntity.getFoodLevel() > 0)
		{
			FMLClientHandler.instance().getClient().renderEngine.bindTexture(
					new ResourceLocation(Reference.MOD_ID + ":textures/blocks/food.png"));
			
			float var0 = -0.2f * (1f - (((float) tileEntity.getFoodLevel() - 1f) / (float) tileEntity.getMaxFoodLevel()));
			
			GL11.glTranslatef(0, var0, 0);
			GL11.glColor3f(1f, 1f, 1f);
			
			model.renderPart("Food");
		}
		
		GL11.glPopMatrix();
	}
}

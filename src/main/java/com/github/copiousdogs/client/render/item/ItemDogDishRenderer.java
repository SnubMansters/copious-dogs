package com.github.copiousdogs.client.render.item;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.github.copiousdogs.block.BlockDogDish;
import com.github.copiousdogs.client.model.block.ModelDogDish;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemDogDishRenderer implements IItemRenderer
{
	private ModelDogDish model;
	
	public ItemDogDishRenderer() 
	{
		model = new ModelDogDish();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch(type)
		{
			case ENTITY:
			{
				renderDogDish(item.getItemDamage(), 1f, 0f, - .5f, 2f);
				return;
			}
			case EQUIPPED:
			{
				renderDogDish(item.getItemDamage(), 1f, .5f, .25f, 1.5f);
				return;
			}
			case INVENTORY:
			{
				renderDogDish(item.getItemDamage(), 1f, .25f, 0f, 1f);
				return;
			}
			case EQUIPPED_FIRST_PERSON:
			{
				renderDogDish(item.getItemDamage(), 0f, 1f, .15f, 1f);
				return;
			}
			
			default: return;
		}
	}

	private void renderDogDish(int meta, float x, float y, float z, float scale)
	{
		GL11.glPushMatrix();
		
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glTranslatef(x, y, z);
		GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(180, 0, 1, 0);
		
		float[] colors = EntitySheep.fleeceColorTable[meta];
		GL11.glColor3f(colors[0], colors[1], colors[2]);
		
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(
				new ResourceLocation("copiousdogs:textures/blocks/" + BlockDogDish.getTexture() + ".png"));
		
		model.render();
		
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}

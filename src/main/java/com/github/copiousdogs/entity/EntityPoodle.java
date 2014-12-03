package com.github.copiousdogs.entity;

import com.github.copiousdogs.lib.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityPoodle extends EntityDog {
	
	public EntityPoodle(World p_i1604_1_) {
		
		super(p_i1604_1_, 0.5f, "poodle");
		setSize(0.6f, 0.8f);
		
		
	}
	
	public ResourceLocation getColorTexture() {
		
		return new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":textures/entities/"
				+ getTextureName() + "_colored.png");
	}
	
	@Override
	protected void entityInit() {
		
		super.entityInit();
		this.dataWatcher.addObject(22, 0);
	}
	
	public int getColor() {
		
		return this.dataWatcher.getWatchableObjectInt(22);
	}
	
	@Override
	public boolean interact(EntityPlayer player) {
		
		ItemStack stack = player.getCurrentEquippedItem();
		
		if (stack != null && this.getOwner() != null) {
			if (this.getOwner().getUniqueID().equals(player.getUniqueID())) {
				if (stack.getItem().equals(Item.itemRegistry.getObject("dye"))) {
					
					this.dataWatcher.updateObject(22, 15 - stack.getItemDamage());
					return true;
				}
			}
		}
		
		return super.interact(player);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag) {
		
		super.writeEntityToNBT(tag);
		tag.setInteger("Color", getColor());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag) {
		
		this.dataWatcher.updateObject(22, tag.getInteger("Color"));
		super.readEntityFromNBT(tag);
	}
}

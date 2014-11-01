package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.entity.EntityDog;

public class EntityAIBegBiscuit extends EntityAIBase {

	private EntityDog dog;
	private float radius;
	private EntityPlayer player;
	private World worldObject;
	private int ticksLeft;
	
	public EntityAIBegBiscuit(EntityDog dog, float radius) {
		
		this.dog = dog;
		this.worldObject = dog.worldObj;
		this.radius = radius;
	}

	@Override
	public boolean shouldExecute() {

		this.player = worldObject.getClosestPlayerToEntity(dog, (double) radius);
		return player == null ? false : isPlayerHoldingBiscuit(player) && !dog.isTamed();
	}
	
	private boolean isPlayerHoldingBiscuit(EntityPlayer player) {
		
		ItemStack stack = player.getCurrentEquippedItem();
		return stack == null ? false:stack.getItem() == CopiousDogsItems.dogBiscuit;
	}

	@Override
	public boolean continueExecuting() {
		
		return !player.isEntityAlive() ? false : (dog.getDistanceSqToEntity(player)) > (double)(Math.pow(radius, 2)) ? false: ticksLeft > 0 && isPlayerHoldingBiscuit(player) && !dog.isTamed();
	}
	
	@Override
	public void updateTask() {
		this.dog.getLookHelper().setLookPosition(this.player.posX, this.player.posY + (double)this.player.getEyeHeight(), this.player.posZ, 10.0F, (float)this.dog.getVerticalFaceSpeed());
		--this.ticksLeft;
	}
	
	@Override
	public void startExecuting() {
		
		dog.setBegging(true);
		ticksLeft = 40 + dog.getRNG().nextInt(40);
	}
	
	@Override
	public void resetTask() {

		dog.setBegging(false);
		this.player = null;
	}
}
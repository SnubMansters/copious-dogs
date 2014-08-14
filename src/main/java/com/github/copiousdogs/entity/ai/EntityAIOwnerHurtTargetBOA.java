package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIOwnerHurtTargetBOA extends EntityAIOwnerHurtTarget
{
	EntityDog dog;
	
	public EntityAIOwnerHurtTargetBOA(EntityDog dog)
	{
		super(dog);
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() >= 6;
	}
}

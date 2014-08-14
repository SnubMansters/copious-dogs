package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIOwnerHurtByTargetBOA extends EntityAIOwnerHurtByTarget
{
	EntityDog dog;
	
	public EntityAIOwnerHurtByTargetBOA(EntityDog dog)
	{
		super(dog);
		this.dog = dog;
	}

	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() > 3;
	}
}

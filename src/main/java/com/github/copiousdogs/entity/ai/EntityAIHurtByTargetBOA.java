package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIHurtByTarget;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIHurtByTargetBOA extends EntityAIHurtByTarget
{
	EntityDog dog;
	
	public EntityAIHurtByTargetBOA(EntityDog dog, boolean par0)
	{
		super(dog, par0);
		this.dog = dog;
	}

	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() >= 8;
	}
}

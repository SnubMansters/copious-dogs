package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIFollowOwner;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIFollowOwnerLeashed extends EntityAIFollowOwner
{
	EntityDog dog;
	
	public EntityAIFollowOwnerLeashed(EntityDog dog,
			double p_i1625_2_, float p_i1625_4_, float p_i1625_5_)
	{
		super(dog, p_i1625_2_, p_i1625_4_, p_i1625_5_);
		this.dog = dog;
	}

	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.hasLeash();
	}
	
	@Override
	public boolean continueExecuting()
	{
		return super.continueExecuting() && dog.hasLeash();
	}
}

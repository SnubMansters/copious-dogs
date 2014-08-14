package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAITargetNonTamed;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAITargetNonTamedBOA extends EntityAITargetNonTamed
{
	EntityDog dog;
	
	public EntityAITargetNonTamedBOA(EntityDog dog,
			Class p_i1666_2_, int p_i1666_3_, boolean p_i1666_4_)
	{
		super(dog, p_i1666_2_, p_i1666_3_, p_i1666_4_);
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute()
	{
		return super.shouldExecute() && dog.getAggressiveness() >= 8;
	}
}

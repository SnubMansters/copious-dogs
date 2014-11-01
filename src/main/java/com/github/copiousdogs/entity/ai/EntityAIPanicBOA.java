package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIPanic;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIPanicBOA extends EntityAIPanic {

	EntityDog dog;
	
	public EntityAIPanicBOA(EntityDog dog, double speed) {
		
		super(dog, speed);
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute() {

		return super.shouldExecute() && dog.getAggressiveness() < 6 && !dog.isTamed();
	}
}

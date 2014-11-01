package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIPanic;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIPanicBOA extends EntityAIPanic {

	EntityDog dog;
	
	public EntityAIPanicBOA(EntityDog p_i1645_1_, double p_i1645_2_) {
		
		super(p_i1645_1_, p_i1645_2_);
		this.dog = p_i1645_1_;
	}

	@Override
	public boolean shouldExecute() {
		
		return super.shouldExecute() && dog.getAggressiveness() < 6 && dog.isTamed();
	}
}

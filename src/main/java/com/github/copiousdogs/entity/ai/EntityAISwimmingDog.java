package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAISwimming;

import com.github.copiousdogs.entity.EntityDog;


public class EntityAISwimmingDog extends EntityAISwimming {

	private EntityDog theEntity;
	
	public EntityAISwimmingDog(EntityDog dog) {
		
		super(dog);
		theEntity = dog;
	}
	
	@Override
	public boolean shouldExecute() {
		
		boolean b = super.shouldExecute();
		System.out.println(b);
		return b;
	}
	
	public void updateTask()
    {
		System.out.println(true);
        this.theEntity.getJumpHelper().setJumping();
    }
}

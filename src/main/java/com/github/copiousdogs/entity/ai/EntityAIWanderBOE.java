package com.github.copiousdogs.entity.ai;

import com.github.copiousdogs.entity.EntityDog;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIWanderBOE extends EntityAIBase {

	private EntityDog dog;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed, walkSpeed, runSpeed;
	
    public EntityAIWanderBOE(EntityDog entity, double walkSpeed, double runSpeed) {
    	
    	this.dog = entity;
    	this.walkSpeed = walkSpeed;
    	this.runSpeed = runSpeed;
    	setMutexBits(1);
    }
    
	@Override
	public boolean shouldExecute() {
		
		if (this.dog.getAge() >= 100)
        {
            return false;
        }
        else if (this.dog.getRNG().nextInt(120) >= dog.getEnergy() * 1.5)
        {
            return false;
        }
        else
        {
        	if (this.dog.getRNG().nextInt(11) - 10 + dog.getEnergy() >= 0) {
        		
        		this.speed = runSpeed;
        	}
        	else {
        		
        		this.speed = walkSpeed;
        	}
        	
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.dog, 10, 7);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.xPosition = vec3.xCoord;
                this.yPosition = vec3.yCoord;
                this.zPosition = vec3.zCoord;
                return true;
            }
        }
	}

	public boolean continueExecuting()
    {
        return !this.dog.getNavigator().noPath();
    }
	
    public void startExecuting()
    {
        this.dog.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}

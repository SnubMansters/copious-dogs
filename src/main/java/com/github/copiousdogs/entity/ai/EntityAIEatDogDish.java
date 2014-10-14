package com.github.copiousdogs.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;

import com.github.copiousdogs.entity.EntityDog;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

public class EntityAIEatDogDish extends EntityAIBase
{
	EntityDog dog;
	int radius;
	float moveSpeed;
	TileEntityDogDish dish;
	
	int eatingTicks;
	
	public EntityAIEatDogDish(EntityDog dog, int radius, float moveSpeed)
	{
		this.dog = dog;
		this.radius = radius;
		this.moveSpeed = moveSpeed;
	}
	
	@Override
	public boolean shouldExecute()
	{
		if (dog.getHealth() < dog.getMaxHealth()) 
		{
			TileEntityDogDish d = getNearbyDogDish();
			
			if (d != null && d.canEat(1f) && !dog.isSitting())
			{
				if (dog.getNavigator().tryMoveToXYZ(d.xCoord, d.yCoord, d.zCoord, moveSpeed)) 
				{
					dish = d;
					dog.getNavigator().setPath(dog.getNavigator().getPathToXYZ(d.xCoord, d.yCoord, d.zCoord), moveSpeed);
					eatingTicks = (int)(dog.getMaxHealth() - dog.getHealth()) * 2;
					
					System.out.println(dog.getHealth() + " " + dog.getMaxHealth() + " " + eatingTicks);
					
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean continueExecuting()
	{
		return dog.getHealth() < dog.getMaxHealth() && !dog.isDead && eatingTicks > 0 &&
				dog.worldObj.getTileEntity(dish.xCoord, dish.yCoord, dish.zCoord) instanceof TileEntityDogDish && dish.canEat(1);		
	}
	
	@Override
	public void updateTask()
	{
		if (dog.getDistanceSq(dish.xCoord, dish.yCoord, dish.zCoord) < 1.5f)
		{
			dog.getNavigator().clearPathEntity();
			dog.setEating(true);
			dog.getLookHelper().setLookPosition(dish.xCoord, dish.yCoord - .8F, dish.zCoord, 10F, dog.getVerticalFaceSpeed());
			eatingTicks--;
			
			if (eatingTicks % 2 == 0) {
				dog.heal(dish.eat(1));
			}
			
			if (dog.getHealth() == dog.getMaxHealth()) dog.setEating(false);
		}
	}
	
	@Override
	public void resetTask()
	{
		dog.setEating(false);
		dish = null;
		eatingTicks = 0;
	}
	
	private TileEntityDogDish getNearbyDogDish()
	{
		for (int x = 0; x < radius * 2; x++)
		{
			for (int y = 0; y < radius * 2; y++)
			{
				for (int z = 0; z < radius * 2; z++)
				{
					TileEntity entity = dog.worldObj.getTileEntity((int)dog.posX + x - radius,
							(int)dog.posY + y - radius, (int)dog.posZ + z - radius);
					
					if (entity != null && entity instanceof TileEntityDogDish)
					{
						return (TileEntityDogDish) entity;
					}
				}
			}
		}
		return null;
	}
}

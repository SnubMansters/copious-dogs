package com.github.copiousdogs.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.world.World;

import com.github.copiousdogs.entity.EntityDog;

public class EntityAIMateNearTorch extends EntityAIBase
{
	private EntityDog dog;
	private World world;
	private EntityDog mate;
	private float radius;
	
	int spawnBabyDelay;
	double speed;
	
	public EntityAIMateNearTorch(EntityDog dog, double speed, float radius)
	{
		this.dog = dog;
		this.world = dog.worldObj;
		this.speed = speed;
		this.setMutexBits(3);
		this.radius = radius;
	}
	
	@Override
	public boolean shouldExecute()
	{
		if (this.dog.isInLove())
		{
			mate = this.getNearbyMate();
			return mate != null && isNearHeat();
		}
		
		return false;
	}
	
	private boolean isNearHeat()
	{
		for (int x = 0; x < radius * 2; x++)
		{
			for (int y = 0; y < radius * 2; y++)
			{
				for (int z = 0; z < radius * 2; z++)
				{	
					Block block = world.getBlock((int)(dog.posX + x - radius), (int)(dog.posY + y - radius), (int)(dog.posZ + z - radius));
					if (block == Block.blockRegistry.getObject("fire") || block == Block.blockRegistry.getObject("torch"))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private EntityDog getNearbyMate()
	{
		double d = 15;
		List list = world.getEntitiesWithinAABB(dog.getClass(), dog.boundingBox.expand(d, d, d));
		double d0 = Double.MAX_VALUE;
		EntityDog animal = null;
		Iterator iterator = list.iterator();
		
		while (iterator.hasNext())
		{
			EntityDog animal1 = (EntityDog)iterator.next();
			if (dog.canMateWith(animal1) && dog.getDistanceSqToEntity(animal1) < d0)
			{
				animal = animal1;
				d0 = dog.getDistanceSqToEntity(animal1);
			}
		}
		
		return animal;
	}
	
	public boolean continueExecuting()
	{
		return mate.isEntityAlive() && mate.isInLove() && spawnBabyDelay < 60;
	}
	
	public void resetTask()
	{
		mate = null;
		spawnBabyDelay = 0;
	}
	
	@Override
	public void updateTask()
	{
		dog.getLookHelper().setLookPositionWithEntity(mate, 10f, (float)dog.getVerticalFaceSpeed());
		dog.getNavigator().tryMoveToEntityLiving(mate, speed);
		++spawnBabyDelay;
		
		if (spawnBabyDelay >= 60 && dog.getDistanceSqToEntity(mate) < 9)
		{
			spawnBaby();
		}
	}
	
	private void spawnBaby()
	{
		EntityAgeable entity = dog.createChild(mate);
		
		System.out.println(entity);
		if (entity != null)
		{
			dog.setGrowingAge(6000);
			mate.setGrowingAge(6000);
			dog.resetInLove();
			mate.resetInLove();
			entity.setGrowingAge(-2000);
			entity.setLocationAndAngles(dog.posX, dog.posY, dog.posZ, 0f, 0f);
			world.spawnEntityInWorld(entity);
			Random random = dog.getRNG();
			
			for (int i = 0; i < 7; i++)
			{
				double d0 = random.nextGaussian() * 0.02;
				double d1 = random.nextGaussian() * 0.02;
				double d2 = random.nextGaussian() * 0.02;
				world.spawnParticle("heart", this.dog.posX + (double)(random.nextFloat() * this.dog.width * 2.0F) - (double)this.dog.width, this.dog.posY + 0.5D + (double)(random.nextFloat() * this.dog.height),
						this.dog.posZ + (double)(random.nextFloat() * this.dog.width * 2.0F) - (double)this.dog.width, d0, d1, d2);
			}
			
			world.spawnEntityInWorld(new EntityXPOrb(world, dog.posX, dog.posY, dog.posZ, random.nextInt(7) + 1));
		}
	}
}

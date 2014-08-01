package com.github.copiousdogs.entity;

import java.lang.reflect.Constructor;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.entity.ai.EntityAIMateNearTorch;
import com.github.copiousdogs.item.ItemDogCollar;

public class EntityDog extends EntityTameable
{
	public float speed;
	private String breed;
	
	public EntityDog(World p_i1604_1_, float speed, String breed)
	{
		super(p_i1604_1_);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15);
		this.speed = speed;
		this.breed = breed;
		
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIMateNearTorch(this, speed, 10f));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, speed));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, .75f, true));
		this.tasks.addTask(7, new EntityAIWander(this, speed));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 0f));
		this.tasks.addTask(9, new EntityAILookIdle(this));
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(18, (byte)0);
		this.dataWatcher.addObject(19, this.getHealth());
		this.dataWatcher.addObject(20, 0);
		this.dataWatcher.addObject(21, (byte)-1);
	}
	
	public String getTextureName() 
	{
		return this.isChild() ? breed + "_pup":breed;
	}
	
	public boolean hasCollar()
	{
		return (this.dataWatcher.getWatchableObjectByte(18) & 1) != 0;
	}
	
	public void setHasCollar(boolean par0)
	{
		if (hasCollar() != par0)
		{
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 1: -1)));
		}
	}
	
	public boolean isTailAnimated()
	{
		return (this.dataWatcher.getWatchableObjectByte(18) & 2) != 0;
	}
	
	public void setTailAnimated(boolean par0)
	{
		if (isTailAnimated() != par0)
		{
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 2: -2)));
		}
	}
	
	public byte getCollarColor()
	{
		return this.dataWatcher.getWatchableObjectByte(21);
	}
	
	public void setCollarColor(byte color)
	{
		this.dataWatcher.updateObject(21, color);
	}
	
	public String getBreed() 
	{
		return breed;
	}
	
	public void setTameValue(int value)
	{
		this.dataWatcher.updateObject(20, value);
	}
	
	public int getTameValue() 
	{
		return this.dataWatcher.getWatchableObjectInt(20);
	}
	 
	public void tryToTame(EntityPlayer player) 
	{
		if (getTameValue() >= 10)
		{
			setTamed(true);
			func_152115_b(player.getUniqueID().toString());
			playTameEffect(true);
		}
		else
		{
			playTameEffect(false);
		}
	}
	
	@Override
	public void setTamed(boolean p_70903_1_)
	{
		super.setTamed(p_70903_1_);
		
		setTailAnimated(p_70903_1_);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_)
	{
		try
		{
			Class<?> clazz = Class.forName(p_90011_1_.getClass().getName());
			Constructor<?> construct = clazz.getConstructor(World.class);
			return (EntityAgeable) construct.newInstance(p_90011_1_.worldObj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean canMateWith(EntityAnimal animal)
	{
		if (animal instanceof EntityDog)
		{
			EntityDog dog = (EntityDog) animal;
			
			return dog == this ? false : this.isInLove() && dog.isInLove()
					&& breed.equals(dog.getBreed());
		}
		
		return false;
	}
	
	@Override
	public boolean interact(EntityPlayer player)
	{
		if (!this.worldObj.isRemote)
		{
			ItemStack stack = player.getCurrentEquippedItem();
			
			if (stack != null)
			{
				if (this.isBreedingItem(stack) && this.getGrowingAge() == 0 && !this.isInLove() 
						&& this.isTamed() && this.getOwner().getUniqueID().equals(player.getUniqueID()))
				{
					player.swingItem();
					
					if (!player.capabilities.isCreativeMode)
					{
						--stack.stackSize;
					}
					
					this.func_146082_f(player);
					return true;
				}
				if (stack.getItem() == CopiousDogsItems.dogBiscuit)
				{
					if (!isTamed())
					{
						player.swingItem();
						
						if (!player.capabilities.isCreativeMode)
						{
							setTameValue(getTameValue() + 3 + getRNG().nextInt(8));
							tryToTame(player);
							stack.stackSize--;
						}
						else 
						{
							setTameValue(11);
							tryToTame(player);
						}
						
						return true;
					}
				}
				if (stack.getItem() == CopiousDogsItems.dogCollar)
				{
					if (this.isTamed() && getOwner().getUniqueID().equals(player.getUniqueID()))
					{
						player.swingItem();
						byte color = getCollarColor();
						
						if (!hasCollar())
						{
							setHasCollar(true);
						}
						setCollarColor((byte) ItemDogCollar.getDyeFromItem(stack.getItemDamage()));
						
						if (!player.capabilities.isCreativeMode)
						{
							stack.stackSize--;
							
							if (color > -1)
							{
								Random r = getRNG();
								
								ItemStack collar = new ItemStack(CopiousDogsItems.dogCollar, 1, ItemDogCollar.getItemFromDye(color));
								EntityItem item = entityDropItem(collar, 1f);
								item.motionX += rand.nextFloat() * .5f;
								item.motionY += (rand.nextFloat() - rand.nextFloat()) * .1f;
								item.motionZ += (rand.nextFloat() - rand.nextFloat()) * .1f;
								
								player.worldObj.spawnEntityInWorld(item);
							}
						}
						
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack item)
	{
		return item.getItem() == Item.itemRegistry.getObject("cookie");
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 2;
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength(par1Entity));
	}
}

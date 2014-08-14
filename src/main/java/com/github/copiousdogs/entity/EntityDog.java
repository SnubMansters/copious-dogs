package com.github.copiousdogs.entity;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.github.copiousdogs.content.CopiousDogsItems;
import com.github.copiousdogs.entity.ai.EntityAIEatDogDish;
import com.github.copiousdogs.entity.ai.EntityAIFollowOwnerLeashed;
import com.github.copiousdogs.entity.ai.EntityAIHurtByTargetBOA;
import com.github.copiousdogs.entity.ai.EntityAIMateNearTorch;
import com.github.copiousdogs.entity.ai.EntityAIOwnerHurtByTargetBOA;
import com.github.copiousdogs.entity.ai.EntityAIOwnerHurtTargetBOA;
import com.github.copiousdogs.entity.ai.EntityAITargetNonTamedBOA;
import com.github.copiousdogs.item.ItemDogCollar;
import com.github.copiousdogs.lib.ConfigInfo;

public class EntityDog extends EntityTameable
{
	public float walkSpeed;
	public float runSpeed;
	private String breed;
	
	public static HashMap<Type, Class<? extends EntityLiving>> spawnMap = new HashMap<Type, Class<? extends EntityLiving>>();
	
	public EntityDog(World p_i1604_1_, float speed, String breed)
	{
		super(p_i1604_1_);
		
		if (ConfigInfo.INDIVIDUAL_TRAITS)
		{
			setAggressiveness(this.getRNG().nextInt(9) + 1);
			setEnergy(this.getRNG().nextInt(9) + 1);
		}
		
		float speedModifier = 0.75f + (getEnergy() / 10) * 2f;
		
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15);
		this.walkSpeed = speed * speedModifier;
		this.runSpeed = speed * 1.2f  * speedModifier;
		this.breed = breed;
		
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, runSpeed));
		this.tasks.addTask(2, new EntityAIMateNearTorch(this, walkSpeed, 10f));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, .75f, true));
		this.tasks.addTask(5, new EntityAIEatDogDish(this, 5, walkSpeed));
		this.tasks.addTask(6, new EntityAIFollowOwnerLeashed(this, runSpeed, 2f, 5f));
		this.tasks.addTask(7, new EntityAIWander(this, runSpeed));
		this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 0f));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIOwnerHurtByTargetBOA(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtTargetBOA(this));
		this.targetTasks.addTask(2, new EntityAIHurtByTargetBOA(this, false));
		this.targetTasks.addTask(3, new EntityAITargetNonTamedBOA(this, EntitySheep.class, 200, true));
	}

	@Override
	protected float getSoundPitch()
	{
		return 2f - this.height;
	}
	
	@Override
	public float getShadowSize()
	{
		return width;
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
		this.dataWatcher.addObject(22, 5);
		this.dataWatcher.addObject(23, 5);
	}
	
	@Override
	protected String getHurtSound()
	{
		return "mob.wolf.hurt";
	}
	
	@Override
	protected String getLivingSound()
	{
		return "mob.wolf.bark";
	}
	
	@Override
	protected String getDeathSound()
	{
		return "mob.wolf.death";
	}
	
	public String getTextureName() 
	{
		return this.isChild() ? breed + "_pup":breed;
	}
	
	public boolean hasCollar()
	{
		return getCollarColor() > -1;
	}
	
	public boolean isTailAnimated()
	{
		return (this.dataWatcher.getWatchableObjectByte(18) & 1) != 0;
	}
	
	public void setTailAnimated(boolean par0)
	{
		if (isTailAnimated() != par0)
		{
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 1: -1)));
		}
	}
	
	public boolean hasLeash()
	{
		return (this.dataWatcher.getWatchableObjectByte(18) & 2) != 0;
	}
	
	public void setHasLeash(boolean par0)
	{
		if (hasLeash() != par0)
		{
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 2: -2)));
		}
	}
	
	public int getAggressiveness()
	{
		return this.dataWatcher.getWatchableObjectInt(22);
	}
	
	public void setAggressiveness(int par0)
	{
		if (par0 < 0) par0 = 0;
		if (par0 > 10) par0 = 10;
		
		this.dataWatcher.updateObject(22, par0);
	}
	
	public int getEnergy()
	{
		return this.dataWatcher.getWatchableObjectInt(23);
	}
	
	public void setEnergy(int par0)
	{
		if (par0 < 0) par0 = 0;
		if (par0 > 10) par0 = 10;
		
		this.dataWatcher.updateObject(23, par0);
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
	
	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		
		Entity entity = p_70097_1_.getEntity();

        if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
        {
            p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
        }
        return super.attackEntityFrom(p_70097_1_, p_70097_2_);
	}
	
	public void tryToTame(EntityPlayer player)
	{
		int value = 10 + getAggressiveness();
		
		if (getTameValue() >= 10)
		{
			setTamed(true);
			func_152115_b(player.getUniqueID().toString());
			System.out.println(true);
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
			System.out.println(this.getAggressiveness() + " " + this.getEnergy());
			
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
				
				if (stack.getItem() == CopiousDogsItems.leash)
				{
					if (hasCollar() && !hasLeash() && this.getOwner().getUniqueID().equals(player.getUniqueID()))
					{
						player.swingItem();
						setHasLeash(true);
						
						if (!player.capabilities.isCreativeMode)
						{
							stack.stackSize--;
						}
						
						return true;
					}
				}
			}
			else {
				if (hasLeash())
				{
					setHasLeash(false);
						
					if (!player.capabilities.isCreativeMode)
					{
						player.inventory.addItemStackToInventory(new ItemStack(CopiousDogsItems.leash, 1));
					}
				}
				else if (hasCollar() && player.isSneaking())
				{
					byte color = getCollarColor();
						
					setCollarColor((byte) -1);
						
					if (!player.capabilities.isCreativeMode)
					{
						if (color > -1)
						{
							Random r = getRNG();
								
							player.inventory.addItemStackToInventory(new ItemStack(CopiousDogsItems.dogCollar, 1, ItemDogCollar.getItemFromDye(color)));
						}
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
		return isTamed() ? 4 : 2;
	}
	
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength(par1Entity));
	}
	
	public int getPrimaryEggColor()
	{
		return 0;
	}
	
	public int getSecondaryEggColor()
	{
		return 0;
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		super.writeEntityToNBT(tag);
		
		tag.setBoolean("TailAnimated", isTailAnimated());
		tag.setBoolean("HasLeash", hasLeash());
		tag.setByte("CollarColor", getCollarColor());
		
		tag.setInteger("Aggressiveness", getAggressiveness());
		tag.setInteger("Energy", getEnergy());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		super.readEntityFromNBT(tag);
		
		setTailAnimated(tag.getBoolean("TailAnimated"));
		setHasLeash(tag.getBoolean("HasLeash"));
		setCollarColor(tag.getByte("CollarColor"));
		
		setEnergy(tag.getInteger("Energy"));
		setAggressiveness(tag.getInteger("Aggressiveness"));
	}
}

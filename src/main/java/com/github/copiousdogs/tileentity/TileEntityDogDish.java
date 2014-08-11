package com.github.copiousdogs.tileentity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

import com.github.copiousdogs.CopiousDogs;
import com.github.copiousdogs.network.MessageDogDish;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class TileEntityDogDish extends TileEntity
{
	private int foodLevel = 0;
	private int prevFoodLevel = 0;

	private int maxFoodLevel = 30;
	private int ticks = 0;
	
	public TileEntityDogDish()
	{
		foodLevel = 0;
	}

	public int getFoodLevel()
	{
		return foodLevel;
	}

	public void setFoodLevel(int par0)
	{
		foodLevel = par0;
		sendChange();
	}

	public int getMaxFoodLevel()
	{
		return maxFoodLevel;
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		sendChange();
		return super.getDescriptionPacket();
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (foodLevel != prevFoodLevel)
		{
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			sendChange();
		}
		
		if (this.blockMetadata != worldObj.getBlockMetadata(xCoord, yCoord, zCoord)) 
		{
			this.blockMetadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		}

		prevFoodLevel = foodLevel;
	}

	private int getFoodModValue(Item item)
	{

		if (item == Item.itemRegistry.getObject("beef") || item == Item.itemRegistry.getObject("porkchop"))
			return 10;
		else if (item == Item.itemRegistry.getObject("chicken"))
			return 5;
		else
			return 0;
	}

	public void sendChange()
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();

		if (side == Side.SERVER)
		{
			markDirty();
			CopiousDogs.snw.sendToAll(new MessageDogDish(this));
		}
	}
	
	public boolean addFood(ItemStack stack)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{

			if (stack == null)
				return false;

			int foodAmount = getFoodModValue(stack.getItem());

			if (foodAmount != 0)
			{

				if (foodLevel >= maxFoodLevel)
				{
					sendChange();
					return false;
				} 
				else if (foodLevel + foodAmount > maxFoodLevel)
				{

					foodLevel = maxFoodLevel;
					sendChange();
					return true;
				} 
				else
				{

					foodLevel += foodAmount;
					sendChange();
					return true;
				}
			}
		}

		return false;
	}

	public float eat(float amount)
	{

		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			if (foodLevel != 0)
			{

				if (foodLevel - amount < 0)
				{

					foodLevel = 0;
					sendChange();
					return foodLevel;
				} else
				{

					foodLevel -= amount;
					sendChange();
					return amount;
				}
			}
		}

		return -1;
	}

	public boolean canEat(float amount)
	{
		return foodLevel - amount >= 0;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.writeToNBT(par1nbtTagCompound);

		par1nbtTagCompound.setInteger("FoodLevel", foodLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.readFromNBT(par1nbtTagCompound);

		foodLevel = par1nbtTagCompound.getInteger("FoodLevel");
	}
}

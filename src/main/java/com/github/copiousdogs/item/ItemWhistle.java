package com.github.copiousdogs.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.github.copiousdogs.entity.EntityDog;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ItemWhistle extends ItemCopiousDogs {

	public ItemWhistle() {
		
		setUnlocalizedName("whistle");
		setMaxStackSize(1);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			
			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(player.posX - 60.0, player.posY - 60.0, player.posZ - 60.0, 
					player.posX + 60.0, player.posY + 60.0, player.posZ + 60.0);
			
			List list = world.getEntitiesWithinAABB(EntityDog.class, box);
			
			for (Object o : list) {
				
				EntityDog dog = (EntityDog) o;
				
				if (dog.getOwner() != null && dog.getOwner().getUniqueID().equals(player.getUniqueID())) {
					
					if (dog.getDistanceToEntity(dog.getOwner()) < 20f || dog.isSitting()) {
						
						continue;
					}
					
					int i = MathHelper.floor_double(dog.getOwner().posX) - 2;
                    int j = MathHelper.floor_double(dog.getOwner().posZ) - 2;
                 	int k = MathHelper.floor_double(dog.getOwner().boundingBox.minY);

                  	for (int l = 0; l <= 4; ++l)
                   	{
                     	for (int i1 = 0; i1 <= 4; ++i1)
                     	{
                           	if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(world, i + l, k - 1, j + i1) && !world.getBlock(i + l, k, j + i1).isNormalCube() && !world.getBlock(i + l, k + 1, j + i1).isNormalCube())
                          	{
                               	dog.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), dog.rotationYaw, dog.rotationPitch);
                             	dog.getNavigator().clearPathEntity();
                          	}
                       	}
                  	}
				}
			}
		}

		return stack;
	}
}

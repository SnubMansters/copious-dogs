package com.github.copiousdogs.block;

import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.github.copiousdogs.CopiousDogs;
import com.github.copiousdogs.lib.Reference;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDogDish extends BlockContainer
{
	private Icon[] icons;
	
	public BlockDogDish()
	{
		super(Material.leaves);
		setBlockBounds(3f / 16f, 0f, 3f / 16f, 1f - 3f / 16f, .25f,
				1f - 3f / 16f);
		setCreativeTab(CopiousDogs.tabCopiousDogs);
		setBlockName("dogDish");
		setLightOpacity(1);
		setTickRandomly(true);
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s.%s", Reference.MOD_ID.toLowerCase(),
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(Reference.MOD_ID + ":dogDish");
	}

	public static String getTexture() {
		
		return "dogDish";
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityDogDish();
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer entity, int par6, float par7, float par8,
			float par9)
	{

		TileEntityDogDish tileEntity = (TileEntityDogDish) par1World
				.getTileEntity(par2, par3, par4);

		ItemStack stack = entity.getCurrentEquippedItem();
		
		if (stack != null)
		{
			boolean foodAdded = tileEntity.addFood(stack);

			if (foodAdded && !entity.capabilities.isCreativeMode)
			{

				stack.stackSize -= 1;
			}

			if (stack.getItem() == Item.itemRegistry.getObject("dye"))
			{

				par1World.setBlock(par2, par3, par4, this,
						getBlockFromDye(stack.getItemDamage()), 2);
				par1World.setTileEntity(par2, par3, par4, tileEntity);

				if (!entity.capabilities.isCreativeMode)
					stack.stackSize -= 1;
			}
		}

		return super.onBlockActivated(par1World, par2, par3, par4, entity,
				par6, par7, par8, par9);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}

	public static int getBlockFromDye(int par0)
	{
		return 15 - par0;
	}

	public static int getDyeFromBlock(int par0)
	{
		return 15 - par0;
	}

	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs,
			List par3List)
	{
		for (int j = 0; j < 16; ++j)
		{
			par3List.add(new ItemStack(this, 1, j));
		}
	}
}

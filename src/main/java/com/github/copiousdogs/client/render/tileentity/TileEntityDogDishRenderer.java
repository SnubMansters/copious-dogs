package com.github.copiousdogs.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.github.copiousdogs.client.model.block.ModelDogDish;
import com.github.copiousdogs.tileentity.TileEntityDogDish;

public class TileEntityDogDishRenderer extends TileEntitySpecialRenderer
{
	private ModelDogDish model = new ModelDogDish();

	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double d0,
			double d1, double d2, float f)
	{
		if (p_147500_1_ instanceof TileEntityDogDish)
		{
			model.render((TileEntityDogDish) p_147500_1_, (float) d0, (float) d1, (float) d2);
		}
	}
}

package com.Nuklear.SeedableDispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.FakePlayer;
import net.minecraftforge.common.FakePlayerFactory;

final class DispenserBehaviorSeeds extends BehaviorDefaultDispenseItem
{
	private boolean field_96461_b = true;

	/**
	 * Dispense the specified stack, play the dispense sound and spawn particles.
	 */
	protected ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
	{
		EnumFacing enumfacing = BlockDispenser.func_100009_j_(par1IBlockSource.func_82620_h());
		World world = par1IBlockSource.getWorld();
		int i = par1IBlockSource.getXInt() + enumfacing.getFrontOffsetX();
		int j = par1IBlockSource.getYInt() + enumfacing.func_96559_d();
		int k = par1IBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
		FakePlayer pl = FakePlayerFactory.getMinecraft(world);

		if (par2ItemStack.getItem().onItemUse(par2ItemStack, pl, world, i, j  , k, 1, 0.5F, 0.5F, 0.5F) ||
			par2ItemStack.getItem().onItemUse(par2ItemStack, pl, world, i, j-1, k, 1, 0.5F, 0.5F, 0.5F) )
		{
			if (!world.isRemote)
			{
				world.playAuxSFX(2005, i, j, k, 0);
			}
		}
		else
		{
			this.field_96461_b = false;
			//return super.dispenseStack(par1IBlockSource, par2ItemStack);
		}

		return par2ItemStack;
	}

	/**
	 * Play the dispense sound from the specified block.
	 */
	protected void playDispenseSound(IBlockSource par1IBlockSource)
	{
		if (this.field_96461_b)
		{
			par1IBlockSource.getWorld().playAuxSFX(1000, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(),
					par1IBlockSource.getZInt(), 0);
		}
		else
		{
			par1IBlockSource.getWorld().playAuxSFX(1001, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(),
					par1IBlockSource.getZInt(), 0);
		}
	}
}

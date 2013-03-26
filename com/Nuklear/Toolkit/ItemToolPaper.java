package com.Nuklear.Toolkit;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolPaper extends Item
{
	public ItemToolPaper(int par1)
	{
		super(par1);
	}

	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister ir)
	{
		iconIndex = ir.func_94245_a("Nuklear/Toolkit:Paper");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack ist, World world, EntityPlayer pl)
	{
		return ist;/*
		if(ToolKit.UseWorkBench)
		{
			return ist;
		}
		return getAllTools(ist, world, pl);*/
	}

	private ItemStack getAllTools(ItemStack ist, World world, EntityPlayer pl)
	{
		InventoryPlayer inv = pl.inventory;
		for(int i = 0; ist.stackSize > 0 && i < inv.getSizeInventory()-4; i++)
		{
			InventoryCrafting craft = new InventoryCrafting(pl.inventoryContainer, 2, 2);
			craft.setInventorySlotContents(0, inv.getStackInSlot(i));
			craft.setInventorySlotContents(2, ist);
			ItemStack ist0 = CraftingManager.getInstance().findMatchingRecipe(craft, world);
			ItemStack ista = ist0 == null ? null : ist0.copy();
			if(ista != null)
			{
				ista.stackSize = 1;

				ist.stackSize--;
				inv.decrStackSize(i, 1);

				inv.addItemStackToInventory(ista);
			}
		}
		return ist;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		return false;
		/*
		if(ToolKit.UseWorkBench && world.getBlockId(x, y, z) == Block.workbench.blockID)
		{
			stack = getAllTools(stack, world, player);
			return true;
		}
        return false;*/
    }
}

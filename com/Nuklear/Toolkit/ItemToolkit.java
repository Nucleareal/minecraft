package com.Nuklear.Toolkit;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemToolkit extends Item
{
	@SideOnly(Side.CLIENT)
	private Icon[] field_94594_d;

	private List<Icon> OverrideTex;
	private List<String> Names;
	private List<Item> Tools;
	private Icon BaseIcon;

	public ItemToolkit(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setMaxDamage(0);

		Names = new ArrayList<String>();
		OverrideTex = new ArrayList<Icon>();
		Tools = new ArrayList<Item>();
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return OverrideTex.get(par1);
	}

	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return super.getUnlocalizedName() + "." + Names.get(par1ItemStack.getItemDamage());
	}

	@Override
	public ItemStack onItemRightClick(ItemStack ist, World world, EntityPlayer pl)
	{
		Item tool = Tools.get(ist.getItemDamage());
		pl.inventory.addItemStackToInventory(new ItemStack(tool));
		--ist.stackSize;

		if(ToolKit.NoDecreaseMode)
		{
			pl.inventory.addItemStackToInventory(new ItemStack(ToolKit.Paper));
		}

		return ist;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int j = 0; j < OverrideTex.size(); ++j)
		{
			par3List.add(new ItemStack(par1, 1, j));
		}
	}

	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister ir)
	{
		BaseIcon = ir.func_94245_a("Nuklear/Toolkit:Paper");
		iconIndex = BaseIcon;
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	public Icon getIconFromDamageForRenderPass(int par1, int pass)
	{
		return pass > 0 ? Tools.get(par1).getIconIndex(new ItemStack(Tools.get(par1))) : BaseIcon;
	}

	public void addOverrideIcon(Icon icon)
	{
		OverrideTex.add(icon);
	}

	public void addName(String name)
	{
		Names.add(name);
	}

	public void addTool(Item tool)
	{
		Tools.add(tool);
	}
}

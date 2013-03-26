package com.Nuklear.Toolkit;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabToolkit extends CreativeTabs
{
	public TabToolkit(String label)
	{
		super(label);
	}

	public TabToolkit(int par1, String par2Str)
	{
		super(par1, par2Str);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return ToolKit.Paper.itemID;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Tool Kits";
	}
}

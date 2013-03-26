package com.Nuklear.ChangeableCoalSprites;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemCoal;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemChangeableCoal extends ItemCoal
{
	private Icon[] Sprite;

	public ItemChangeableCoal(int par1)
	{
		super(par1);
		Sprite = new Icon[2];
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		return Sprite[par1];
	}

	@SideOnly(Side.CLIENT)
	public void func_94581_a(IconRegister ir)
	{
		Sprite[0] = ir.func_94245_a("Nuklear/ChangeableCoalSprites:Coal");
		Sprite[1] = ir.func_94245_a("Nuklear/ChangeableCoalSprites:Charcoal");
	}
}

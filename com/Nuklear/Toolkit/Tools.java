package com.Nuklear.Toolkit;

import net.minecraft.item.Item;

public class Tools
{
	public Tools(Item ... items)
	{
		_tools = items;
	}

	public Item[] Tools()
	{
		return _tools;
	}

	private Item[] _tools;
}

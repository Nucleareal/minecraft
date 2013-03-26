package com.Nuklear.ChangeableCoalSprites;

import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "ChangeableCoalSprites", name = "Changeable Coal Sprites", version = "1.51_1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ChangeableCoalSprites
{
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		Item coal = Item.coal;
		Item.itemsList[coal.itemID] = null;
		Item.itemsList[coal.itemID] = new ItemChangeableCoal(coal.itemID);
	}
}

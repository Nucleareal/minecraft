package com.Nuklear.SeedableDispenser;

import net.minecraft.block.BlockDispenser;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "SeedableDispenser", name = "Seedable Dispenser", version = "1.51_1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Main
{
	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		BlockDispenser.dispenseBehaviorRegistry.putObject(Item.seeds, new DispenserBehaviorSeeds());
	}
}

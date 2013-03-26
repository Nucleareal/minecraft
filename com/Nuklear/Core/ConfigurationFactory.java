package com.Nuklear.Core;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationFactory
{
	public static Configuration create(String name, FMLPreInitializationEvent ev)
	{
		Configuration conf = new Configuration(ev.getSuggestedConfigurationFile());
		conf.load();
		return conf;
	}
}

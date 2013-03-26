package com.Nuklear.Core;

import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Core
{
	public static void ConfigError(String ModName)
	{
		FMLLog.log(Level.SEVERE, "Cannot Load or Save Configuration File on "+ModName);
	}

	public static void Configuration(FMLPreInitializationEvent ev, IConfiguration conf)
	{
		Configuration cfg = ConfigurationFactory.create(ev.toString(), ev);
		try
		{
			conf.Configuration(cfg);
		}
		catch(Exception e)
		{
			ConfigError(ev.toString());
		}
		finally
		{
			cfg.save();
		}
	}
}

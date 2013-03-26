package com.Nuklear.Toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.Nuklear.Core.Core;
import com.Nuklear.Core.IConfiguration;
import com.Nuklear.Core.ObjectPair;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Toolkit", name = "Toolkit", version = "1.51_1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ToolKit
{
	public static Item Paper;
	public static List<ItemToolkit> _Toolkits;
	public static List<ObjectPair<String, Tools>> _Materials;
	public static int cursor = 13444;
	public static boolean NoDecreaseMode = false;
	public static boolean UseWorkBench = true;
	private static final CreativeTabs tabToolkit = new CreativeTabs("ToolKits");

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Core.Configuration(event, new IConfiguration()
		{
			@Override
			public void Configuration(Configuration cfg)
			{
				Property kitProp = cfg.getItem("ItemToolKitID", cursor);
				kitProp.comment   = "ItemID of Toolkit";
				cursor = kitProp.getInt();

				Property isWB = cfg.get("General", "UseWorkBench", UseWorkBench);
				Property isND = cfg.get("General", "NoDecreaseMode", NoDecreaseMode);
				isWB.comment = "Use WorkBench(Right Click) on Tool All-Contain Mode";
				isND.comment = "Tool Paper No Longer Decrease";
				NoDecreaseMode = isND.getBoolean(NoDecreaseMode);
				UseWorkBench = isWB.getBoolean(UseWorkBench);
			}
		});
	}

	@Mod.Init
	public void init(FMLInitializationEvent event)
	{
		Paper = new ItemToolPaper(cursor++).setUnlocalizedName("ToolPaper").setCreativeTab(tabToolkit);
		GameRegistry.registerItem(Paper, "ToolPaper");
		LanguageRegistry.addName(Paper, "Tool Paper");
		LanguageRegistry.instance().addNameForObject(Paper, "ja_JP", "ツールペーパー");

		for(int i = 0; i < _Materials.size(); i++)
		{
			ObjectPair<String, Tools> pair = _Materials.get(i);
			Item[] tools = pair.getV2().Tools();

			String unname = "ToolKit."+pair.getV1().toString();


			ItemToolkit toolkit = new ItemToolkit(cursor);
			toolkit.setUnlocalizedName(unname).setCreativeTab(tabToolkit);

			GameRegistry.registerItem(toolkit, unname);
			//LanguageRegistry.addName(toolkit, "Tool Kit");
			//LanguageRegistry.instance().addNameForObject(toolkit, "ja_JP", "ツールキット");

			for(int j = 0; j < tools.length; j++)
			{
				Icon icon = tools[j].getIconIndex(new ItemStack(tools[j]));
				toolkit.addOverrideIcon(icon);
				toolkit.addName(getSeparatedLastName(tools[j].getUnlocalizedName()));
				toolkit.addTool(tools[j]);

				GameRegistry.addRecipe(new ItemStack(toolkit, 1, j), new Object[]
				{
					"T",
					"P",
					Character.valueOf('T'), new ItemStack(tools[j], 1),
					Character.valueOf('P'), new ItemStack(Paper, 1),
				});

				//LanguageRegistry.instance().addStringLocalization(toolkit.getUnlocalizedName(new ItemStack(toolkit, 1, j)), "Tool Kit");

				LanguageRegistry.instance().addStringLocalization(
						new StringBuilder().append(toolkit.getUnlocalizedName(new ItemStack(toolkit, 1, j))).append(".name").toString(),
						new StringBuilder().append("ToolKit ").append(getName(tools[j].getUnlocalizedName())).toString());

				LanguageRegistry.instance().addStringLocalization(
						new StringBuilder().append(toolkit.getUnlocalizedName(new ItemStack(toolkit, 1, j))).append(".name").toString(),
						"ja_JP",
						new StringBuilder().append(getName(tools[j].getUnlocalizedName())).append("のツールキット").toString());
			}

			//LanguageRegistry.addName(itemSample, "Sample Item");
			//LanguageRegistry.instance().addNameForObject(itemSample, "ja_JP", "サンプルアイテム");

			_Toolkits.add(toolkit);

			cursor++;
		}

		GameRegistry.addRecipe(new ItemStack(Paper, 4, 0),  new Object[]
		{
			"S",
			"P",
			Character.valueOf('S'), Block.stone,
			Character.valueOf('P'), Block.thinGlass,
		});
		GameRegistry.addRecipe(new ItemStack(Paper, 64, 0),  new Object[]
		{
			"GGG",
			"SSS",
			"GGG",
			Character.valueOf('G'), Block.glass,
			Character.valueOf('S'), Block.stone,
		});

		LanguageRegistry.instance().addStringLocalization("itemGroup.ToolKits", "Tool Kits");
	}

	private Object getName(String unlocalizedName)
	{
		return StatCollector.translateToLocal(unlocalizedName+".name");
	}

	private String getSeparatedLastName(String unlocalizedName)
	{
		StringTokenizer sz = new StringTokenizer(unlocalizedName, ".");
		String name = null;
		while(sz.hasMoreTokens())
		{
			name = sz.nextToken();
		}
		return name;
	}

	public static void registry(String name, Tools tools)
	{
		_Materials.add(new ObjectPair<String, Tools>(name, tools));
	}

	static
	{
		_Toolkits = new ArrayList<ItemToolkit>();
		_Materials = new ArrayList<ObjectPair<String, Tools>>();
		registry("All", new Tools(Item.shovelWood, Item.pickaxeWood, Item.axeWood, Item.swordWood, Item.hoeWood,
				Item.helmetLeather, Item.plateLeather, Item.legsLeather, Item.bootsLeather, Item.shears, Item.flintAndSteel, Item.bow, Item.saddle,
				Item.doorWood, Item.doorSteel, Item.boat, Item.shovelStone, Item.pickaxeStone, Item.axeStone, Item.swordStone, Item.hoeStone,
				Item.helmetChain, Item.plateChain, Item.legsChain, Item.bootsChain, Item.bucketWater, Item.bucketLava, Item.bucketMilk,
				Item.minecartEmpty, Item.minecartCrate, Item.minecartPowered, Item.pocketSundial, Item.shovelSteel, Item.pickaxeSteel, Item.axeSteel, Item.swordSteel, Item.hoeSteel,
				Item.helmetSteel, Item.plateSteel, Item.legsSteel, Item.bootsSteel, Item.field_94582_cb, Item.field_96600_cc, Item.fishingRod,
				Item.map, Item.emptyMap, Item.bed, Item.shovelGold, Item.pickaxeGold, Item.axeGold, Item.swordGold, Item.hoeGold,
				Item.helmetGold, Item.plateGold, Item.legsGold, Item.bootsGold, Item.shovelDiamond, Item.pickaxeDiamond, Item.axeDiamond, Item.swordDiamond, Item.hoeDiamond,
				Item.helmetDiamond, Item.plateDiamond, Item.legsDiamond, Item.bootsDiamond));
	}
}

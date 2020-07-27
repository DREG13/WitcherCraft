package com.Red_Art.witchermod.init;

import java.util.ArrayList;
import java.util.List;
import com.Red_Art.witchermod.items.ItemBase;
import com.Red_Art.witchermod.items.SmithingHammer;
import com.Red_Art.witchermod.items.SubParts;
import com.Red_Art.witchermod.items.Unstacktable;
import com.Red_Art.witchermod.tools.ToolSword;
import com.Red_Art.witchermod.tools.krieg;
import com.Red_Art.witchermod.tools.lang;
import com.Red_Art.witchermod.tools.swei;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.item.Item;
public class ModItems {
public static final List<Item> ITEMS = new ArrayList<Item>();  


public static final Item BOTTLE_BRINE = new Unstacktable("bottle_brine");
public static final Item BOTTLE_COOLING_OIL = new Unstacktable("bottle_cooling_oil");
public static final Item SALT = new ItemBase("salt");
public static final Item SILVER_INGOT = new ItemBase("silver_ingot");
public static final Item METEOR_IRON_INGOT = new ItemBase("meteor_iron_ingot");
public static final Item BLADE = new Unstacktable("blade");
public static final Item BUBBLED_BLADE = new SubParts("bubbled_blade");
public static final Item GUARD = new SubParts("guard");
public static final Item HANDLE = new SubParts("handle");
public static final Item HILT = new Unstacktable("hilt");
public static final Item IRON_ROD = new SubParts("iron_rod");
public static final Item LARGE_GUARD = new SubParts("large_guard");
public static final Item LONG_BLADE = new Unstacktable("long_blade");
public static final Item LONG_HANDLE = new SubParts("long_handle");
public static final Item LONG_HILT = new Unstacktable("long_hilt");
public static final Item METEORITE_BLADE = new Unstacktable("meteorite_blade");
public static final Item BUBBLED_METEORITE_BLADE = new SubParts("bubbled_meteorite_blade");
public static final Item METEORITE_ROD = new SubParts("meteorite_rod");
public static final Item MOLTEN_BLADE = new Unstacktable("molten_blade");
public static final Item MOLTEN_IRON_ROD = new Unstacktable("molten_iron_rod");
public static final Item MOLTEN_LONG_BLADE = new Unstacktable("molten_long_blade");
public static final Item MOLTEN_METEORITE_BLADE = new Unstacktable("molten_meteorite_blade");
public static final Item MOLTEN_METEORITE_ROD = new Unstacktable("molten_meteorite_rod");
public static final Item MOLTEN_SHORT_BLADE = new Unstacktable("molten_short_blade");
public static final Item MOLTEN_SILVER_BLADE = new Unstacktable("molten_silver_blade");
public static final Item MOLTEN_SILVER_ROD = new Unstacktable("molten_silver_rod");
public static final Item MOLTEN_THICK_IRON_ROD = new Unstacktable("molten_thick_iron_rod");
public static final Item MOLTEN_THINN_IRON_ROD = new Unstacktable("molten_thinn_iron_rod");
public static final Item POMMEL = new SubParts("pommel");
public static final Item REINFORCED_BLADE = new Unstacktable("reinforced_blade");
public static final Item REINFORCED_LONG_BLADE = new Unstacktable("reinforced_long_blade");
public static final Item REINFORCED_METEORITE_BLADE = new Unstacktable("reinforced_meteorite_blade");
public static final Item REINFORCED_SHORT_BLADE = new Unstacktable("reinforced_short_blade");
public static final Item HARDENED_BLADE = new Unstacktable("hardened_blade");
public static final Item HARDENED_LONG_BLADE = new Unstacktable("hardened_long_blade");
public static final Item HARDENED_SHORT_BLADE = new Unstacktable("hardened_short_blade");
public static final Item REINFORCED_SILVER_BLADE = new Unstacktable("reinforced_silver_blade");
public static final Item SHORT_BLADE = new Unstacktable("short_blade");
public static final Item SHORT_HANDLE = new SubParts("short_handle");
public static final Item SILVER_BLADE = new Unstacktable("silver_blade");
public static final Item BUBBLED_SILVER_BLADE = new SubParts("bubbled_silver_blade");
public static final Item SILVER_ROD = new SubParts("silver_rod");
public static final Item SMALL_GUARD = new SubParts("small_guard");
public static final Item SMALL_HILT = new Unstacktable("small_hilt");
public static final Item THICK_IRON_ROD = new SubParts("thick_iron_rod");
public static final Item THINN_IRON_ROD = new SubParts("thinn_iron_rod"); 
public static final Item SMITHING_HAMMER = new SmithingHammer("smithing_hammer");
//Material

public static final ToolMaterial TOOL_KRIEGSSCHWERT = EnumHelper.addToolMaterial("tool_kriegsschwert", 0, 250, 4.0F, 3.0F, 10);
public static final ToolMaterial TOOL_LANGSCHWERT = EnumHelper.addToolMaterial("tool_langschwert", 0, 500, 3.0F, 4.0F, 12);
public static final ToolMaterial TOOL_ZWEIHANDER = EnumHelper.addToolMaterial("tool_zweihander", 0, 1500, 5.0F, 11.0F, 14);
public static final ToolMaterial TOOL_METEORITE = EnumHelper.addToolMaterial("tool_meteorite", 0, 2500, 4.0F, 6.0F, 16);
public static final ToolMaterial TOOL_SILVER = EnumHelper.addToolMaterial("tool_silver", 0, 1200, 0F, 2.5F, 8);
public static final ToolMaterial TOOL_REINFORCED_KRIEGSSCHWERT = EnumHelper.addToolMaterial("tool_reinforced_kriegsschwert", 0, 500, 4.0F, 4.0F, 10);
public static final ToolMaterial TOOL_REINFORCED_LANGSCHWERT = EnumHelper.addToolMaterial("tool_reinforced_langschwert", 0, 1000, 3.0F, 5.0F, 12);
public static final ToolMaterial TOOL_REINFORCED_ZWEIHANDER = EnumHelper.addToolMaterial("tool_reinforced_zweihander", 0, 3000, 5.0F, 12.0F, 14);
public static final ToolMaterial TOOL_HARDENED_KRIEGSSCHWERT = EnumHelper.addToolMaterial("tool_hardened_kriegsschwert", 0, 350, 4.0F, 3.5F, 10);
public static final ToolMaterial TOOL_HARDENED_LANGSCHWERT = EnumHelper.addToolMaterial("tool_hardened_langschwert", 0, 750, 3.0F, 4.5F, 12);
public static final ToolMaterial TOOL_HARDENED_ZWEIHANDER = EnumHelper.addToolMaterial("tool_hardened_zweihander", 0, 2000, 5.0F, 11.5F, 14);

//Tools

public static final Item KRIEGSSCHWERT = new krieg("kriegsschwert", TOOL_KRIEGSSCHWERT);
public static final Item LANGSCHWERT = new lang("langschwert", TOOL_LANGSCHWERT);
public static final Item ZWEIHANDER = new swei("zweihander", TOOL_ZWEIHANDER);
public static final Item METEORITE_SWORD = new lang("meteorite_sword", TOOL_METEORITE);
public static final Item SILVER_SWORD = new lang("silver_sword", TOOL_SILVER);
public static final Item REINFORCED_KRIEGSSCHWERT = new krieg("reinforced_kriegsschwert", TOOL_REINFORCED_KRIEGSSCHWERT);
public static final Item REINFORCED_LANGSCHWERT = new lang("reinforced_langschwert", TOOL_REINFORCED_LANGSCHWERT);
public static final Item REINFORCED_ZWEIHANDER = new swei("reinforced_zweihander", TOOL_REINFORCED_ZWEIHANDER);
public static final Item HARDENED_KRIEGSSCHWERT = new krieg("hardened_kriegsschwert", TOOL_HARDENED_KRIEGSSCHWERT);
public static final Item HARDENED_LANGSCHWERT = new lang("hardened_langschwert", TOOL_HARDENED_LANGSCHWERT);
public static final Item HARDENED_ZWEIHANDER = new swei("hardened_zweihander", TOOL_HARDENED_ZWEIHANDER);

}

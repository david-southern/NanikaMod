package com.spekisoftware.NanikaMod;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemManager
{
    public static Item nanikaStar;
    public static Item meekerSeeker;
    
    public static void preInit()
    {        
        nanikaStar = new NanikaStar();
        meekerSeeker = new MeekerSeeker();
        
        GameRegistry.registerItem(nanikaStar, "NankiaStar");
        GameRegistry.registerItem(meekerSeeker, "MeekerSeeker");
        
        int modEntityId = 0;
        
        EntityRegistry.registerModEntity(EntityNanikaStar.class, "Nankias Star", ++modEntityId, ModMain.instance, 64, 3, true);
        EntityRegistry.registerModEntity(EntityMeekerSeeker.class, "Meekers Seeker", ++modEntityId, ModMain.instance, 64, 3, true);
    }
    
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(nanikaStar, 8), 
                "BGB",
                "GCG",
                "BGB",
                'B', Blocks.iron_bars,
                'G', Items.glowstone_dust,
                'C', Blocks.gold_block);
        
        GameRegistry.addShapelessRecipe(new ItemStack(meekerSeeker, 1), nanikaStar, Items.redstone, Items.redstone, Items.redstone, Items.redstone);
    }
}

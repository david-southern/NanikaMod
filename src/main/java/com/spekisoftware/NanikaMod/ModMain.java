package com.spekisoftware.NanikaMod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.ModInfo.MODID, name = Constants.ModInfo.MODNAME, version = Constants.ModInfo.MODVERSION)
public class ModMain
{
    @SidedProxy(clientSide = "com.spekisoftware.NanikaMod.ClientProxy", serverSide = "com.spekisoftware.NanikaMod.CommonProxy")
    public static CommonProxy proxy;

    @Instance("Speki_ModMain")
    public static ModMain instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        
        ItemManager.preInit();
        
        proxy.registerRendering();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ItemManager.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
}

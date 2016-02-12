package com.spekisoftware.NanikaMod;

import net.minecraft.creativetab.CreativeTabs;

public final class Constants
{

    public static final class ModInfo
    {
        public static final String MODID = "spekisoftwarenanika";
        public static final String MODNAME = "Nanika's Super Mod";
        public static final String MODVERSION = "1.7.10-0.0.1";
    }

    public static final class Blocks
    {
        public static String BlockName(String baseName)
        {
            return ModInfo.MODID + "_" + baseName;
        }
        
        public static final class Faces
        {
        	public static final int DOWN = 0;
        	public static final int UP = 1;
        	public static final int NORTH = 2;
        	public static final int SOUTH = 3;
        	public static final int WEST = 4;
        	public static final int EAST = 5;
        }
    }

	public static final class Textures
	{
		public static String TextureName(String baseName)
		{
			return ModInfo.MODID + ":" + baseName;
		}
	}
}

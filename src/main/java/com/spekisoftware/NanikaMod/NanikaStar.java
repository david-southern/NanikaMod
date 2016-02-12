package com.spekisoftware.NanikaMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NanikaStar extends Item
{
    private static String BASE_NAME = "nanika_star";
    
    public NanikaStar()
    {
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName(BASE_NAME);
        this.setTextureName(Constants.Textures.TextureName(BASE_NAME));
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World theWorld, EntityPlayer thePlayer)
    {
        if (!thePlayer.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        theWorld.playSoundAtEntity(thePlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!theWorld.isRemote)
        {
            theWorld.spawnEntityInWorld(new EntityNanikaStar(theWorld, thePlayer));
        }

        return itemStack;
    }
}

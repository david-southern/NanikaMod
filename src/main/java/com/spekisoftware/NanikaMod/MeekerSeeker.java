package com.spekisoftware.NanikaMod;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class MeekerSeeker extends Item
{
    private static String BASE_NAME = "meeker_seeker";

    public MeekerSeeker()
    {
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.setUnlocalizedName(BASE_NAME);
        this.setTextureName(Constants.Textures.TextureName(BASE_NAME));
    }

    /**
     * Called whenever this item is equipped and the right mouse button is
     * pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World theWorld,
            EntityPlayer thePlayer)
    {
        if (!thePlayer.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }

        theWorld.playSoundAtEntity(thePlayer, "random.bow", 0.5F,
                0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        List<Entity> nearbyEntities = theWorld
                .getEntitiesWithinAABBExcludingEntity(thePlayer,
                        thePlayer.boundingBox.expand(50.0D, 20.0D, 50.0D));

        double minDistance = 9999;
        Entity minEntity = null;

        Vec3 lookVec = thePlayer.getLookVec();
        // Vec3 posVec = thePlayer.getPosition(1.0F);
        
        Vec3 posVec = Vec3.createVectorHelper(thePlayer.posX, thePlayer.posY, thePlayer.posZ);
        
        for (Entity checkEnt : nearbyEntities)
        {
            if (checkEnt instanceof IMob)
            {
                Vec3 mobPos = Vec3.createVectorHelper(checkEnt.posX, checkEnt.posY, checkEnt.posZ);

                Vec3 deltaVec = mobPos.subtract(posVec);

                if (lookVec.dotProduct(deltaVec) > 0)
                {
                    // With the way I did the deltaVec, dot product > 0
                    // indicates the mob is behind me
                    continue;
                }

                if (deltaVec.lengthVector() < minDistance)
                {
                    minDistance = deltaVec.lengthVector();
                    minEntity = checkEnt;

                }
            }
        }

        if (minEntity != null)
        {
            System.out.format("Picking seeker target %s%n", minEntity
                    .getClass().getName());
        }

        if (!theWorld.isRemote)
        {
            EntityMeekerSeeker seeker = new EntityMeekerSeeker(minEntity, theWorld,
                    thePlayer);

            theWorld.spawnEntityInWorld(seeker);
        }

        return itemStack;
    }

}

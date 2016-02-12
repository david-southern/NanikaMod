package com.spekisoftware.NanikaMod;

import java.io.Console;
import java.util.List;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSword;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityMeekerSeeker extends EntityThrowable
{
    private static String BASE_NAME = "entity_meeker_seeker";

    Entity myTarget = null;
    
    public EntityMeekerSeeker(World theWorld)
    {
        super(theWorld);
    }

    public EntityMeekerSeeker(World theWorld, EntityLivingBase theEntity)
    {
        super(theWorld, theEntity);
    }

    public EntityMeekerSeeker(World theWorld, double x, double y, double z)
    {
        super(theWorld, x, y, z);
    }


    public EntityMeekerSeeker(Entity mobTarget, World theWorld, EntityLivingBase theEntity)
    {
        super(theWorld, theEntity);
        myTarget = mobTarget;
        
        // Re-do motion calcs to take our target status into account
        this.setLocationAndAngles(theEntity.posX, theEntity.posY + (double)theEntity.getEyeHeight(), theEntity.posZ, theEntity.rotationYaw, theEntity.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        float f = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * (float)Math.PI) * f);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition thisPos)
    {
        if (thisPos.entityHit != null && thisPos.entityHit instanceof IMob)
        {
            DamageSource damageSource = DamageSource
                    .causePlayerDamage((EntityPlayer) this.getThrower());

            byte damage = 6;

            // if (thisPos.entityHit instanceof EntityEnderman)
            // {
            // damage = 120;
            // }

            // What the heck, let's KILL EVERYTHING!
            // damage = 120;

            if (myTarget == thisPos.entityHit)
            {
                damage = 120;
            }

            thisPos.entityHit.attackEntityFrom(damageSource, (float) damage);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("crit", this.posX, this.posY,
                    this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    int seekerAge = 0;

    @Override
    public void onUpdate()
    {
        if(!this.worldObj.isRemote)
        {
            seekerAge++;
            
            if(seekerAge > 200)
            {
                this.setDead();
            }
        }
        
        if (myTarget != null)
        {
            Vec3 myPos = Vec3.createVectorHelper(posX, posY, posZ);
            System.out.format("My pos: %s%n", myPos);
            Vec3 mobPos = Vec3.createVectorHelper(myTarget.posX, myTarget.posY,
                    myTarget.posZ);
            System.out.format("Mob pos: %s%n", mobPos);
            Vec3 deltaVec = myPos.subtract(mobPos);
            System.out.format("Delta: %s%n", deltaVec);

            motionX += deltaVec.xCoord * 0.03;
            motionY += deltaVec.yCoord * 0.03;
            motionZ += deltaVec.zCoord * 0.03;
        }

        super.onUpdate();
    }

    @Override
    protected float getGravityVelocity()
    {
        if(myTarget == null)
        {
            return 0.03F;
        }
        
        return 0.0F;
    }

    @Override
    protected float func_70182_d()
    {
        if(myTarget == null)
        {
            return 1.5F;
        }
        
        // This is the projectile speed
        return 0.0F;
    }
}

package com.spekisoftware.NanikaMod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityNanikaStar extends EntityThrowable
{
    private static String BASE_NAME = "entity_nanika_star";

    public EntityNanikaStar(World theWorld)
    {
        super(theWorld);
    }

    public EntityNanikaStar(World theWorld, EntityLivingBase theEntity)
    {
        super(theWorld, theEntity);
    }

    public EntityNanikaStar(World theWorld, double x, double y, double z)
    {
        super(theWorld, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */

    @Override
    protected void onImpact(MovingObjectPosition thisPos)
    {
        if (thisPos.entityHit != null)
        {
            DamageSource damageSource = DamageSource.causePlayerDamage((EntityPlayer) this.getThrower());

            byte damage = 6;
            
            if (thisPos.entityHit instanceof EntityEnderman)
            {
                damage = 120;
            }

            // What the heck, let's KILL EVERYTHING!
            // damage = 120;
            
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

    @Override
    protected float getGravityVelocity()
    {
        return 0.03F;
    }

    @Override
    protected float func_70182_d()
    {
        // This is the projectile speed
        return 1.5F;
    }
}
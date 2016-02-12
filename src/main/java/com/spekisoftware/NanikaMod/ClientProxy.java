package com.spekisoftware.NanikaMod;

import net.minecraft.client.renderer.entity.RenderSnowball;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRendering() 
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityNanikaStar.class, new RenderSnowball(ItemManager.nanikaStar));
        RenderingRegistry.registerEntityRenderingHandler(EntityMeekerSeeker.class, new RenderSnowball(ItemManager.meekerSeeker));
    }
}

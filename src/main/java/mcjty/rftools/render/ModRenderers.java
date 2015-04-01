package mcjty.rftools.render;

import mcjty.rftools.blocks.endergen.EndergenicRenderer;
import mcjty.rftools.blocks.endergen.EndergenicTileEntity;
import mcjty.rftools.blocks.environmental.EnvironmentalControllerRenderer;
import mcjty.rftools.blocks.environmental.EnvironmentalControllerTileEntity;
import mcjty.rftools.blocks.logic.LogicSlabBlock;
import mcjty.rftools.blocks.logic.LogicSlabRenderer;
import mcjty.rftools.blocks.screens.ScreenRenderer;
import mcjty.rftools.blocks.screens.ScreenTileEntity;
import mcjty.rftools.blocks.shield.SolidShieldBlock;
import mcjty.rftools.blocks.shield.SolidShieldBlockRenderer;
import mcjty.rftools.blocks.spawner.MatterBeamerRenderer;
import mcjty.rftools.blocks.spawner.MatterBeamerTileEntity;
import mcjty.rftools.blocks.teleporter.BeamRenderer;
import mcjty.rftools.blocks.teleporter.MatterTransmitterTileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public final class ModRenderers {

    public static void init() {
        LogicSlabBlock.RENDERID_LOGICSLAB = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(LogicSlabBlock.RENDERID_LOGICSLAB, new LogicSlabRenderer());

        SolidShieldBlock.RENDERID_SHIELDBLOCK = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(SolidShieldBlock.RENDERID_SHIELDBLOCK, new SolidShieldBlockRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(EndergenicTileEntity.class, new EndergenicRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(MatterBeamerTileEntity.class, new MatterBeamerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(ScreenTileEntity.class, new ScreenRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(EnvironmentalControllerTileEntity.class, new EnvironmentalControllerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(MatterTransmitterTileEntity.class, new BeamRenderer());
    }
}
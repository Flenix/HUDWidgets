package co.uk.silvania.hudwidgets;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import co.uk.silvania.hudwidgets.client.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid=HUDWidgets.modid, name="HUDWidgets", version="0.1.0")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)
public class HUDWidgets {
	
	public static final String modid = "hudwidgets";
	public static String configPath;
	
    @Instance(HUDWidgets.modid)
    public static HUDWidgets instance;
	
    @SidedProxy(clientSide="co.uk.silvania.hudwidgets.client.ClientProxy", serverSide="co.uk.silvania.hudwidgets.CommonProxy")
    public static CommonProxy proxy;
    
	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
	}
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	configPath = event.getModConfigurationDirectory() + "/";
    	HUDWidgetsConfig.init(configPath);
    	GuiWidgetHealth.defaultResolution();
    	//proxy.init();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {}
    
    @EventHandler
    public void postInit(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetBase(Minecraft.getMinecraft()));
    	
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetArmour(Minecraft.getMinecraft())); //TODO 1
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetCompass(Minecraft.getMinecraft())); //TODO 2
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetExp(Minecraft.getMinecraft())); //TODO 1
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetGamemode(Minecraft.getMinecraft())); //TODO 2
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetHealth(Minecraft.getMinecraft())); 
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetHorseHealth(Minecraft.getMinecraft())); //TODO 2
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetHorseJump(Minecraft.getMinecraft())); //TODO 2
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetHotbar(Minecraft.getMinecraft())); //TODO 1
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetHunger(Minecraft.getMinecraft())); //TODO 1
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetName(Minecraft.getMinecraft())); //TODO 2
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetOxygen(Minecraft.getMinecraft())); //TODO 2
    	//MinecraftForge.EVENT_BUS.register(new GuiWidgetPotionDock(Minecraft.getMinecraft())); //TODO 2
    	MinecraftForge.EVENT_BUS.register(new GuiWidgetTime(Minecraft.getMinecraft())); //TODO 2
    	//MinecraftForge.EVENT_BUS.register(new GuiWidgetWallet(Minecraft.getMinecraft())); //TODO 2
    	//Notifications
    	//MOTD
    	//TODO Boss Health Bar, maybe a target health bar too.
    }

}

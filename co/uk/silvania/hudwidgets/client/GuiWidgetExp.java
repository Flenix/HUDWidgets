package co.uk.silvania.hudwidgets.client;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.hudwidgets.HUDWidgets;
import co.uk.silvania.hudwidgets.HUDWidgetsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiWidgetExp extends GuiWidgetBase {

	public GuiWidgetExp(Minecraft mc) {
		super(mc);
	}
	
	private static final ResourceLocation experienceOrbTextures = new ResourceLocation("textures/entity/experience_orb.png");
	private static final ResourceLocation guiStatsBar = new ResourceLocation(HUDWidgets.modid, "textures/gui/" + config.expTextureStyle);
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderGui(RenderGameOverlayEvent event) {
		boolean enabled = true;
		if (!config.expEnabled) {
			enabled = false;
		}
		if (mc.thePlayer.capabilities.isCreativeMode && !config.renderExpCreative) {
			enabled = false;
		}
		
		if (enabled) {
			FontRenderer font = mc.fontRenderer;
	
			int experience = Math.round(mc.thePlayer.experience * 1000);
			int expLevel = mc.thePlayer.experienceLevel;
			int experienceAmount = Math.round(experience / 5);
			
			double widthMultiplier = getResIncreaseMultiplier("x");
			double heightMultiplier = getResIncreaseMultiplier("y");
			
			int sizeX = 204;
			int sizeY = 20;
			
			if (config.expAnchor == 0 || config.expAnchor > 8) {
				configX = (int) Math.round(config.expXPos * widthMultiplier);
				configY = (int) Math.round(config.expYPos * heightMultiplier);
			} else {
				configX = calculateAnchorPointX(config.expAnchor, sizeX);
				configY = calculateAnchorPointY(config.expAnchor, sizeY);
			}
			
			int xPos = configX + config.expXOffset;
			int yPos = configY + config.expYOffset;
			
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);;
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			mc.renderEngine.bindTexture(guiStatsBar);
	
			this.drawTexturedModalRect(xPos, yPos, 0, 148, sizeX, sizeY);
			this.drawTexturedModalRect(xPos + 2, yPos + 2, 0, 92, experienceAmount, 16);
			this.drawTexturedModalRect(xPos, yPos, 0, 40, sizeX, sizeY);
			
			this.mc.renderEngine.bindTexture(vanillaIcons);
			GL11.glScalef(2.0F, 2.0F, 2.0F);
			//this.drawTexturedModalRect(Math.round(xPos / 2) + 1, Math.round(yPos / 2), 53, 1, 7, 7);		
			
			
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			font.drawStringWithShadow("Experience: " + experience + "/" + "1000", xPos + 22, yPos + 6, config.expTextColour);
			font.drawStringWithShadow("Lvl: " + expLevel, xPos + 142, yPos + 6, config.expTextColour);
			GL11.glPopMatrix();
		}
	}
}

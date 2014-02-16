package co.uk.silvania.hudwidgets.client;

import org.lwjgl.opengl.GL11;

import co.uk.silvania.hudwidgets.HUDWidgets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class GuiWidgetName extends GuiWidgetBase {

	public GuiWidgetName(Minecraft mc) {
		super(mc);
	}
	
	private static final ResourceLocation vanillaIcons = new ResourceLocation("textures/gui/icons.png");
	private static final ResourceLocation guiStatsBar = new ResourceLocation(HUDWidgets.modid, "textures/gui/" + config.nameTextureStyle);
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderGui(RenderGameOverlayEvent.Pre event) {
		boolean enabled = true;		
		if (!config.nameEnabled) {
			enabled = false;
		}

		if (mc.thePlayer.capabilities.isCreativeMode && !config.renderNameCreative) {
			enabled = false;
		}
		
		if (enabled) {
			FontRenderer font = mc.fontRenderer;
			String name = mc.thePlayer.username;
			
			double widthMultiplier = getResIncreaseMultiplier("x");
			double heightMultiplier = getResIncreaseMultiplier("y");
			
			int sizeX = 79;
			int sizeY = 20;
			
			if (config.nameRelativeResize) {
				sizeX = 10 + (name.length() * 6);
			}
			
			if (config.nameAnchor == 0 || config.nameAnchor > 8) {
				configX = (int) Math.round(config.nameXPos * widthMultiplier);
				configY = (int) Math.round(config.nameYPos * heightMultiplier);
			} else {
				configX = calculateAnchorPointX(config.nameAnchor, sizeX);
				configY = calculateAnchorPointY(config.nameAnchor, sizeY);
			}
			
			int xPos = configX + config.nameXOffset;
			int yPos = configY + config.nameYOffset;
			int xTextPos = xPos;
			int yTextPos = yPos;
			
			if (config.nameTextAlignRight) {
				xPos = (79 - sizeX) + xPos - 2;
			}
			
			if (config.nameTextAlignRight) {
				if (!config.nameRelativeResize) {
					xTextPos = xPos + (name.length() * 6);
				}
			}
	
			GL11.glPushMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);;
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			mc.renderEngine.bindTexture(guiStatsBar);
			
			this.drawTexturedModalRect(xPos, yPos, 158, 148, sizeX, sizeY);
			if (config.nameRelativeResize) {
				this.drawTexturedModalRect(xPos + sizeX, yPos, 235, 148, 2, 20);
			}
			font.drawString(name, xTextPos + 6, yTextPos + 6, config.nameTextColour);
			GL11.glPopMatrix();
		}
	}
}

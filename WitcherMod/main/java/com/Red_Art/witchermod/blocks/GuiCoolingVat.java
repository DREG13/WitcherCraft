package com.Red_Art.witchermod.blocks;

import com.Red_Art.witchermod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiCoolingVat extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID+":textures/gui/cooling_vat.png");
	private final InventoryPlayer player;
	private final TileEntityCoolingVat tileentity;
	
	public GuiCoolingVat(InventoryPlayer player, TileEntityCoolingVat tileentity) {
		super(new ContainerCoolingVat(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize/2 - this.fontRenderer.getStringWidth(tileName)/2), 8, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedComponentText(),122, this.ySize-96+2, 4210752);
		
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if(TileEntityCoolingVat.isBurning(tileentity)) {
		
			
		int k = this.getBurnLeftScaled(13);
		this.drawTexturedModalRect(this.guiLeft + 56, this.guiTop + 38 - k, 176, 0-k , 14, k + 13);
			
		
		}
	}
	
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tileentity.getField(1);
		if(i == 0) i = 200;
		return this.tileentity.getField(0) * pixels / i;
	}
	
	
	
	

}

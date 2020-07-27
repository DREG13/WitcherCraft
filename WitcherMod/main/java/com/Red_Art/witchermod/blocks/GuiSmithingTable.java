package com.Red_Art.witchermod.blocks;

import com.Red_Art.witchermod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiSmithingTable extends GuiContainer
{
	
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID+":textures/gui/smithing_table.png");
	private final InventoryPlayer player;
	private final TileEntitySmithingTable tileentity;
	
	public GuiSmithingTable(InventoryPlayer player, TileEntitySmithingTable tileentity) {
		super(new ContainerSmithingtable(player, tileentity));
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
		
		if(TileEntitySmithingTable.InProgress(tileentity)) {
		
		
		int l = this.getCookProgressScaled(57);
		this.drawTexturedModalRect(this.guiLeft + 62, this.guiTop + 52, 176, 14, l + 1, 16);
		}
	}
	
	
	
	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileentity.getField(0);
		int j = this.tileentity.getField(1);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}

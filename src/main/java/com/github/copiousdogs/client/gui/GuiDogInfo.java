package com.github.copiousdogs.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.github.copiousdogs.entity.EntityDog;
import com.github.copiousdogs.lib.Reference;

public class GuiDogInfo extends GuiScreen {

	private EntityDog dog;
	private String startName = "";
	private GuiTextField nameField;
	
	public GuiDogInfo(EntityDog dog) {
		
		this.dog = dog;
		if (dog.hasCustomNameTag()) {
			
			startName = dog.getCustomNameTag();
		}
	}
	
	@Override
	public void initGui() {
		
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 + 55, this.height / 2 - 100, 50, 20, I18n.format("gui.copiousdogs.rename", new Object[0])));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 25, this.height / 2 + 50, 50, 20, I18n.format("gui.copiousdogs.done", new Object[0])));
		nameField = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 100, 150, 20);
		nameField.setFocused(true);
		nameField.setText(startName);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == 0) {
			
			String comp = nameField.getText().replaceAll("\\s", "");
			
			if (comp != "" && nameField.getText() != startName) {
				
				dog.setCustomNameTag(nameField.getText());
			}
		}
		if (button.id == 1) {
			
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	public void drawBackground(int p_146278_1_) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/dog_info.png"));
        int k = (this.width - 219) / 2;
        int l = (this.height - 197) / 2 - 20;
        this.drawTexturedModalRect(k, l, 0, 0, 219, 197);
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {

		this.drawBackground(0);
		
		this.fontRendererObj.drawString(String.format("%s: %.0f",
				I18n.format("gui.copiousdogs.health", new Object[0]), dog.getHealth()),
				this.width / 2 - 95, this.height / 2 - 70, 0xFFFFFF);
		
		this.fontRendererObj.drawString(String.format("%s: %s", 
				I18n.format("gui.copiousdogs.breed", new Object[0]), I18n.format("entity." + Reference.MOD_ID + "." + dog.getBreed() + ".name", new Object[0])),
				this.width / 2 - 95, this.height / 2 - 60, 0xFFFFFF);
		
		this.fontRendererObj.drawString(String.format("%s: %.2f", 
				I18n.format("gui.copiousdogs.energy", new Object[0]), dog.getEnergy()),
				this.width / 2 - 95, this.height / 2 - 50, 0xFFFFFF);
		
		this.fontRendererObj.drawString(String.format("%s: %.2f", 
				I18n.format("gui.copiousdogs.aggressiveness", new Object[0]), dog.getAggressiveness()),
				this.width / 2 - 95, this.height / 2 - 40, 0xFFFFFF);
		
		nameField.drawTextBox();
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		
		if (nameField.isFocused()) {
			
			nameField.textboxKeyTyped(c, i);
		}
		
		if (i == Keyboard.KEY_ESCAPE) {
			
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	public void onGuiClosed() {
		
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return false;
	}
}

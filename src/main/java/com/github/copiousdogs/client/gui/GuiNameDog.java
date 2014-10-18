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

public class GuiNameDog extends GuiScreen {

	private EntityDog dog;
	private GuiTextField nameField;
	
	public GuiNameDog(EntityDog dog) {
		
		this.dog = dog;
	}
	
	@Override
	public void initGui() {
		
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height / 2 + 15, 100, 20, I18n.format("gui.copiousdogs.done", new Object[0])));
		nameField = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, this.height / 2 - 10, 200, 20);
		nameField.setFocused(true);
		nameField.setText("");
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == 0) {
			
			String comp = nameField.getText().replaceAll("\\s", "");
			
			if (comp != "") {
				
				dog.setCustomNameTag(nameField.getText());
			}
			
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	public void drawBackground(int p_146278_1_) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/name_dog.png"));
        int k = (this.width - 232) / 2;
        int l = (this.height - 86) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, 232, 86);
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {

		this.drawBackground(0);
		
		String s = I18n.format("gui.copiousdogs.namedog", new Object[0]);
		this.fontRendererObj.drawString(s, (this.width - this.fontRendererObj.getStringWidth(s)) / 2, this.height / 2 - 30, 4210752);
		
		nameField.drawTextBox();
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		
		if (nameField.isFocused()) {
			
			nameField.textboxKeyTyped(c, i);
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		
		return true;
	}
	
	@Override
	public void onGuiClosed() {
		
		Keyboard.enableRepeatEvents(false);
	}
}

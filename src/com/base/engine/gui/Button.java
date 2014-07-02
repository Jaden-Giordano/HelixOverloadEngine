package com.base.engine.gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.Image;

import com.base.engine.Main;
import com.base.handlers.FileHandler;
import com.base.handlers.Input;

public class Button extends GuiElement {
	
	private Image img;
	private boolean clicked, recentClick, hover;

	/**
	 * Creates new button on gui layer with given x, y position
	 * @param x
	 * @param y
	 */
	public Button(float x, float y) {
		super(x, y);
		img = FileHandler.LoadImage("/assets/gui/button.png");
		clicked = false;
		recentClick = false;
		hover = false;
	}
	
	/**
	 * Checks if the user clicks the button or is hovering
	 */
	public void GetInput() {
		if (Input.MousePressed(org.newdawn.slick.Input.MOUSE_LEFT_BUTTON)) {
			if ((Mouse.getX() >= this.x && Mouse.getX() <= this.x+48) && ((Main.SCREEN_HEIGHT-Mouse.getY()) >= this.y && (Main.SCREEN_HEIGHT-Mouse.getY()) <= this.y+48)) {
				clicked = true;
			}
		}
		
		if ((Mouse.getX() >= this.x && Mouse.getX() <= this.x+48) && ((Main.SCREEN_HEIGHT-Mouse.getY()) >= this.y && (Main.SCREEN_HEIGHT-Mouse.getY()) <= this.y+48)) {
			hover = true;
		}
		else {
			hover = false;
		}
	}
	
	/**
	 * Performs function if button was clicked
	 */
	public void Update() {
		if (this.clicked) {
			recentClick = true;
			clicked = false;
		}
	}
	
	/**
	 * Draws the button on the gui layer
	 */
	public void Draw() {
		if (hover) {
			GL14.glBlendColor(100, 100, 100, 200);
			GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_CONSTANT_COLOR);
			img.draw(this.x, this.y);
			GL14.glBlendColor(255, 255, 255, 255);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		else {
			img.draw(this.x, this.y);
		}
	}
	
	public boolean HasButtonBeenClicked() {
		if (recentClick) {
			recentClick = false;
			return true;
		}
		return false;
	}

}

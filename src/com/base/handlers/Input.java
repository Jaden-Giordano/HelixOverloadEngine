package com.base.handlers;

import org.lwjgl.input.Keyboard;

public class Input {

	/**
	 * Returns direction for input.
	 * @param axis
	 * 	Horizontal -> left = -1, right = 1
	 * 	Vertical -> down = -1, up = 1
	 * @return
	 */
	public static float GetAxis(String axis) {
		if (axis.equalsIgnoreCase("Horizontal")) {
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				return 1;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				return -1;
			}
			return 0;
		}
		else if (axis.equalsIgnoreCase("Vertical")) {
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				return 1;
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				return -1;
			}
			return 0;
		}
		else {
			GuiHandler.AddMessage("Invalid axis: "+axis);
			return 0;
		}
	}
	
	public static boolean KeyPressed(int key) {
		if (Keyboard.getEventKey() == key && Keyboard.getEventKeyState()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean KeyReleased(int key) {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == key) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
}

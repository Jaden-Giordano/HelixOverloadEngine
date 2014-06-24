package com.base.handlers;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

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
	
	/**
	 * returns if the given key is pressed
	 * @param key
	 * @return
	 */
	public static boolean KeyPressed(int key) {
		if (Keyboard.getEventKey() == key && Keyboard.getEventKeyState()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * returns if the given key is released
	 * @param key
	 * @return
	 */
	public static boolean KeyReleased(int key) {
		if (Keyboard.getEventKey() == key && Keyboard.getEventKeyState()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * returns if the given mouse button is pressed
	 * @param button
	 * @return
	 */
	public static boolean MousePressed(int button) {
		if (Mouse.getEventButton() == button && Mouse.getEventButtonState()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * returns if the given mouse button is released
	 * @param button
	 * @return
	 */
	public static boolean MouseReleased(int button) {
		if (Mouse.getEventButton() == button && !Mouse.getEventButtonState()) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

package com.base.engine;

public class Camera {
	
	private static float x = 0;
	private static float y = 0;
	
	public static void GetInput() {
	}
	
	public static void Update() {
	}
	
	/**
	 * Centers the object to be focused on the screen
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public static void FocusOn(float x, float y, float w, float h) {
		SetX(x-(Main.SCREEN_WIDTH/2)+w/2);
		SetY(y-(Main.SCREEN_HEIGHT/2)+h/2);
	}
	
	/**
	 * Sets the x position of the camera to the given x coordinate
	 * @param f
	 */
	public static void SetX(float f) {
		x = f;
	}
	
	/**
	 * Sets the y positon of the camera to the given y coordinate
	 * @param g
	 */
	public static void SetY(float g) {
		y = g;
	}
	
	
	/**
	 * returns the X position of the camera
	 * @return
	 */
	public static float GetX() {
		return x;
	}
	
	/**
	 * returns the Y position of the camera
	 * @return
	 */
	public static float GetY() {
		return y;
	}
	
}

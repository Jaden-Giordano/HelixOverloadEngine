package com.base.engine;

public class Camera {
	
	private static float x = 0;
	private static float y = 0;
	
	public static void GetInput() {
	}
	
	public static void Update() {
	}
	
	public static void FocusOn(float x, float y, float w, float h) {
		SetX(x-(Main.SCREEN_WIDTH/2)+w/2);
		SetY(y-(Main.SCREEN_HEIGHT/2)+h/2);
	}
	
	public static void SetX(float f) {
		x = f;
	}
	
	public static void SetY(float g) {
		y = g;
	}
	
	public static float GetX() {
		return x;
	}
	
	public static float GetY() {
		return y;
	}
	
}

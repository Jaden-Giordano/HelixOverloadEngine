package com.base.engine;

import com.base.handlers.FileHandler;

public class SpriteSheet {
	
	private static org.newdawn.slick.SpriteSheet worldSpr;
	
	public static void InitSpriteSheet() {
		worldSpr = new org.newdawn.slick.SpriteSheet(FileHandler.LoadImage("/assets/objects/blocks/blocks.png"), 32, 32);
	}
	
	public static org.newdawn.slick.SpriteSheet GetSpriteSheet() {
		return worldSpr;
	}

}

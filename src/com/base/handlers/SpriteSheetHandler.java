package com.base.handlers;


public class SpriteSheetHandler {
	
	private static org.newdawn.slick.SpriteSheet worldSpr;
	
	/**
	 * Loads the base layer sprite sheet from file
	 */
	public static void InitSpriteSheet() {
		worldSpr = new org.newdawn.slick.SpriteSheet(FileHandler.LoadImage("/assets/objects/blocks/blocks.png"), 32, 32);
	}
	
	/**
	 * returns the base layer spritesheet
	 * @return
	 */
	public static org.newdawn.slick.SpriteSheet GetSpriteSheet() {
		return worldSpr;
	}
	
	public static org.newdawn.slick.SpriteSheet LoadSpriteSheet(String path) {
		return new org.newdawn.slick.SpriteSheet(FileHandler.LoadImage(path), 32, 32);
	}

}

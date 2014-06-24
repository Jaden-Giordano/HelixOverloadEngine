package com.base.engine;

import org.newdawn.slick.SpriteSheet;

public class Animation {
	
	private SpriteSheet animset;
	private Timer timer;

	/**
	 * Creates a new animation with a given spritesheet
	 * @param spriteSheet
	 */
	public Animation(SpriteSheet spriteSheet) {
		this.animset = spriteSheet;
		timer = new Timer();
		
		timer.Start();
	}
	
	/**
	 * Determines if the animation update should update to the next frame
	 */
	int currentFrame = 0;
	public void Update() {
		if (timer.GetElapsedTime() > 100) {
			currentFrame++;
			currentFrame %= animset.getHorizontalCount();
			timer.Start();
		}
	}
	
	/**
	 * Draws the current frame for the give layer
	 * @param set
	 */
	public void Draw(int layer) {
		animset.getSprite(currentFrame, layer).draw();
	}

}

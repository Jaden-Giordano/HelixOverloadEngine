package com.base.engine;

import org.newdawn.slick.SpriteSheet;

public class Animation {
	
	private SpriteSheet animset;
	private Timer timer;

	public Animation(SpriteSheet set) {
		this.animset = set;
		timer = new Timer();
		
		timer.Start();
	}
	
	int currentFrame = 0;
	public void Update() {
		if (timer.GetElapsedTime() > 100) {
			currentFrame++;
			currentFrame %= animset.getHorizontalCount();
			timer.Start();
		}
	}
	
	public void Draw(int set) {
		animset.getSprite(currentFrame, set).draw();
	}

}

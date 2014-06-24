package com.base.engine;

import static org.lwjgl.opengl.GL11.*;

public class Transition {
	
	private int duration;
	private Timer timer;
	private float alphaConst;
	
	private boolean isComplete;

	public Transition(int dur) {
		this.duration = dur;
		this.timer = new Timer();
		
		this.isComplete = false;
		
		this.alphaConst = ((float) duration)/255f;
		
		timer.Start();
	}
	
	public void Update() {
		if (timer.GetElapsedTime() >= duration) {
			isComplete = true;
		}
	}
	
	public void Draw() {
		float alpha = (((float) timer.GetElapsedTime())/alphaConst)/255f;
		
		glDisable(GL_TEXTURE_2D);
		
		glColor4f(0, 0, 0, alpha);
		glBegin(GL_QUADS);
		{
			glVertex2i(0, 0);
			glVertex2i(Main.SCREEN_WIDTH, 0);
			glVertex2i(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
			glVertex2i(0, Main.SCREEN_HEIGHT);
		}
		glEnd();
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public boolean IsComplete() {
		return this.isComplete;
	}

}

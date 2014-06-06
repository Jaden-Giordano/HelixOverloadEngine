package com.base.engine.gameobject;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;

import com.base.engine.Animation;
import com.base.engine.Camera;

public class RenderableObject extends GameObject {
	
	private Image tex = null;
	
	private Animation anim = null;
	private int animLayer = 0;

	public RenderableObject(float x, float y, Image tex) {
		super(x, y);
		this.tex = tex;
	}
	
	public RenderableObject(float x, float y, Animation anim) {
		super(x, y);
		this.anim = anim;
	}
	
	public void UpdateAnimation() {
		if (anim != null) {
			anim.Update();
		}
	}
	
	public void Draw() {
		glPushMatrix();
		{
			glTranslatef(this.x-Camera.GetX(), this.y-Camera.GetY(), 0);
			if (tex != null) {
				tex.draw();
			}
			else if (anim != null) {
				anim.Draw(animLayer);
			}
			else {
				GL11.glColor4f(1, 1, 1, 1);
				glBegin(GL_QUADS);
				{
					glVertex2f(0, 0);
					glVertex2f(32, 0);
					glVertex2f(32, 32);
					glVertex2f(0, 32);
				}
				glEnd();
			}
		}
		glPopMatrix();
	}
	
	protected void SetAnimLayer(int layer) {
		this.animLayer = layer;
	}

}

package com.base.engine.gameobject;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;

import com.base.engine.Animation;
import com.base.engine.Camera;

public class RenderableObject extends GameObject {
	
	private Image tex = null;
	
	private Animation anim = null;
	private int animLayer = 0;
	
	/**
	 * Creates new renderable object with starting x, y coordinate and white box
	 * @param x
	 * @param y
	 */
	public RenderableObject(float x, float y) {
		super(x, y);
	}

	/**
	 * Creates new renderable object with starting x, y coordinate and image
	 * @param x
	 * @param y
	 * @param tex
	 */
	public RenderableObject(float x, float y, Image tex) {
		super(x, y);
		this.tex = tex;
	}
	
	/**
	 * Creates new renderable object with starting x, y coordinate and animation
	 * @param x
	 * @param y
	 * @param anim
	 */
	public RenderableObject(float x, float y, Animation anim) {
		super(x, y);
		this.anim = anim;
	}
	
	/**
	 * Updates the animation
	 * 	must be called from derived class
	 */
	public void UpdateAnimation() {
		if (anim != null) {
			anim.Update();
		}
	}
	
	/**
	 * Draws the object based on attributes
	 */
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
				glDisable(GL_TEXTURE_2D);
				GL11.glColor4f(255, 255, 255, 255);
				glBegin(GL_QUADS);
				{
					glVertex2f(0, 0);
					glVertex2f(32, 0);
					glVertex2f(32, 32);
					glVertex2f(0, 32);
				}
				glEnd();
				glEnable(GL_TEXTURE_2D);
			}
		}
		glPopMatrix();
	}
	
	/**
	 * Sets the current animation layer the the given layer
	 * @param layer
	 */
	protected void SetAnimLayer(int layer) {
		this.animLayer = layer;
	}

}

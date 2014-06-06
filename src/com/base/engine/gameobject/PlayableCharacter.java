package com.base.engine.gameobject;

import org.newdawn.slick.Image;

import com.base.engine.Animation;
import com.base.engine.Camera;
import com.base.handlers.Input;

public class PlayableCharacter extends RenderableObject {
	
	private String move = "idle";

	public PlayableCharacter(float x, float y) {
		super(x, y, (Image) null);
	}
	
	public PlayableCharacter(float x, float y, Image img) {
		super(x, y, img);
	}
	
	public PlayableCharacter(float x, float y, Animation anim) {
		super(x, y, anim);
	}
	
	public void GetInput() {
		if (Input.GetAxis("Vertical") == -1) {
			move = "up";
		}
		if (Input.GetAxis("Vertical") == 1) {
			move = "down";
		}
		if (Input.GetAxis("Horizontal") == -1) {
			move = "left";
		}
		if (Input.GetAxis("Horizontal") == 1) {
			move = "right";
		}
		if (Input.GetAxis("Horizontal") == 0 && Input.GetAxis("Vertical") == 0) {
			move = "idle";
		}
	}
	
	public void Update() {
		if (move.equalsIgnoreCase("up")) {
			this.SetAnimLayer(1);
		}
		else if (move.equalsIgnoreCase("down")) {
			this.SetAnimLayer(2);
		}
		else if (move.equalsIgnoreCase("left")) {
			this.SetAnimLayer(3);
		}
		else if (move.equalsIgnoreCase("right")) {
			this.SetAnimLayer(4);
		}
		else {
			this.SetAnimLayer(0);
		}
		Move(Input.GetAxis("Horizontal")*4, -Input.GetAxis("Vertical")*4);
		Camera.FocusOn(this.x, this.y, 32, 32);
		this.UpdateAnimation();
	}
	
	public boolean isPlayableCharacter() {
		return true;
	}
	
}

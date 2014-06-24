package com.base.engine.gameobject;

import org.newdawn.slick.Image;

import com.base.engine.Animation;
import com.base.engine.Camera;
import com.base.engine.Stats;
import com.base.game.Game;
import com.base.handlers.Input;

public class PlayableCharacter extends RenderableObject {
	
	private String move = "idle";
	private Stats stats;

	/**
	 * Creates new playable character with given starting x, y position
	 * @param x
	 * @param y
	 */
	public PlayableCharacter(float x, float y) {
		super(x, y, (Image) null);
		this.stats = new Stats(1, "bob");
	}
	
	/**
	 * Creates new playable character with given starting x, y position and texture
	 * @param x
	 * @param y
	 * @param img
	 */
	public PlayableCharacter(float x, float y, Image img) {
		super(x, y, img);
		this.stats = new Stats(1, "bob");
	}
	
	/**
	 * Creates new playable character with given starting x, y position and animation
	 * @param x
	 * @param y
	 * @param anim
	 */
	public PlayableCharacter(float x, float y, Animation anim) {
		super(x, y, anim);
		this.stats = new Stats(1, "bob");
	}
	
	/**
	 * Gets keyboard input and sets move flags
	 */
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
	
	/**
	 * Updates player position based on input, camera position, animation
	 */
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
	
	/**
	 * Teleports character to given x, y coordinate
	 * @param destX
	 * @param destY
	 */
	public void Teleport(float destX, float destY) {
		this.x = destX;
		this.y = destY;
	}
	
	public void Teleport(int destW) {
		Game.SetCurrentWorld(destW);
	}
	
	/**
	 * returns if object is a playable character ( true b/c it is one )
	 * @return
	 */
	public boolean isPlayableCharacter() {
		return true;
	}
	
	/**
	 * returns characters stats
	 * @return
	 */
	public Stats GetStats() {
		return this.stats;
	}
	
	/**
	 * returns the characters id
	 * @return
	 */
	public int GetPlayerID() {
		return 0;
	}
	
}

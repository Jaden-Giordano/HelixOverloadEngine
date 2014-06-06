package com.base.engine.gameobject;

public abstract class GameObject {

	protected float x, y;
	
	/**
	 * Initializes Normal GameObjects, Not Blocks
	 * @param x
	 * @param y
	 */
	public GameObject(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void GetInput() { }
	
	public void Update() { }
	
	/**
	 * All Game Objects affected by camera.
	 */
	public void Draw() { }
	
	public void Move(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public boolean isPlayableCharacter() {
		return false;
	}

}

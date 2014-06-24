package com.base.engine.gameobject;

public abstract class GameObject {

	protected float x, y;
	
	/**
	 * Initializes Normal GameObjects with given x, y position
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
	 * Draws objects
	 */
	public void Draw() { }
	
	/**
	 * moves object with given x, y vector
	 * @param x
	 * @param y
	 */
	public void Move(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * returns the x position of the game object
	 * @return
	 */
	public float GetX() {
		return this.x;
	}
	
	/**
	 * returns the y position of the game object
	 * @return
	 */
	public float GetY() {
		return this.y;
	}
	
	public boolean isPlayableCharacter() {
		return false;
	}

}

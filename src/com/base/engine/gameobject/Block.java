package com.base.engine.gameobject;

import com.base.engine.SpriteSheet;

public class Block extends RenderableObject {
	
	protected int id;
	
	/**
	 * Creates new Tile with given x, y, and id
	 * @param x
	 * @param y
	 * @param id
	 */
	public Block(float x, float y, int id) {
		super(x, y, SpriteSheet.GetSpriteSheet().getSprite(id, 0));
		this.id = id;
	}
	
	/**
	 * returns the id of the block
	 * @return
	 */
	public int GetID() {
		return this.id;
	}

}

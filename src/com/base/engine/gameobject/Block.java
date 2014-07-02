package com.base.engine.gameobject;

import com.base.handlers.SpriteSheetHandler;

public class Block extends RenderableObject {
	
	protected int id;
	
	/**
	 * Creates new Tile with given x, y, and id
	 * @param x
	 * @param y
	 * @param id
	 */
	public Block(float x, float y, int id) {
		super(x, y, SpriteSheetHandler.GetSpriteSheet().getSprite(id%SpriteSheetHandler.GetSpriteSheet().getHorizontalCount(), id/SpriteSheetHandler.GetSpriteSheet().getHorizontalCount()));
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

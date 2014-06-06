package com.base.game.blocks;

import com.base.engine.SpriteSheet;
import com.base.engine.gameobject.Block;

public class StoneBlock extends Block {

	/**
	 * Creates new Stone Block
	 */
	public StoneBlock(float x, float y) {
		super(x, y, SpriteSheet.GetSpriteSheet().getSprite(2, 0));
		this.SetID(2).SetName("Stone Block");
	}

}

package com.base.game.blocks;

import com.base.engine.SpriteSheet;
import com.base.engine.gameobject.Block;

public class DirtBlock extends Block {

	/**
	 * Creates New Dirt Block
	 */
	public DirtBlock(float x, float y) {
		super(x, y, SpriteSheet.GetSpriteSheet().getSprite(1, 0));
	}

}

package com.base.game.blocks;

import com.base.engine.SpriteSheet;
import com.base.engine.gameobject.Block;

public class DefaultBlock extends Block {

	/**
	 * New Block Default block
	 */
	public DefaultBlock(float x, float y) {
		super(x, y, SpriteSheet.GetSpriteSheet().getSprite(0, 0));
	}

}

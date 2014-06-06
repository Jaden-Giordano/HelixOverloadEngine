package com.base.engine.gameobject;

import org.newdawn.slick.Image;

public class Block extends RenderableObject {
	
	public static final int BLOCK_DEFAULT= 0;
	public static final int BLOCK_DIRT = 1;
	public static final int BLOCK_STONE = 2;
	
	protected int id;
	protected String name;
	
	public Block(float x, float y, Image tex) {
		super(x, y, tex);
	}
	
	public Block SetID(int id) {
		this.id = id;
		return this;
	}
	
	public int GetID() {
		return this.id;
	}
	
	public Block SetName(String name) {
		this.name = name;
		return this;
	}
	
	public String GetName() {
		return this.name;
	}

}

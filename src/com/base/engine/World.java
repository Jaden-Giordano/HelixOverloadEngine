package com.base.engine;

import java.util.ArrayList;

import com.base.engine.gameobject.GameObject;
import com.base.engine.gameobject.PlayableCharacter;
import com.base.game.blocks.DefaultBlock;
import com.base.game.blocks.DirtBlock;
import com.base.game.blocks.StoneBlock;

public class World {
	
	ArrayList<GameObject> objs;
	int[][] data;

	/**
	 * Initializes world with world data
	 * @param worldData
	 */
	public World(int[][] worldData) {
		objs = new ArrayList<GameObject>();
		this.data = worldData;
		
		LoadWorld();
	}
	
	/**
	 * Converts world data to Game Objects/Blocks
	 */
	private void LoadWorld() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				switch (data[i][j]) {
				case 0:
					objs.add(new DefaultBlock(i*32-data.length*32/2, j*32-data.length*32/2));
					break;
				case 1:
					objs.add(new DirtBlock(i*32-data.length*32/2, j*32-data.length*32/2));
					break;
				case 2:
					objs.add(new StoneBlock(i*32-data.length*32/2, j*32-data.length*32/2));
					break;
				default:
					break;
				}
			}
		}
	}
	
	public void GetInput() {
		for (GameObject i : objs) {
			if (i.isPlayableCharacter()) {
				i.GetInput();
			}
		}
	}
	
	public void Update() {
		for (GameObject i : objs) {
			i.Update();
		}
	}
	
	public void Draw() {
		for (GameObject i : objs) {
			i.Draw();
		}
	}
	
	public void AddPlayableCharacter(PlayableCharacter p) {
		objs.add(p);
	}

}

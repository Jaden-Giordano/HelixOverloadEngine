package com.base.engine;

import java.util.ArrayList;

import com.base.engine.gameobject.Block;
import com.base.engine.gameobject.GameObject;
import com.base.engine.gameobject.PlayableCharacter;
import com.base.engine.gameobject.Warp;

public class World {
	
	private ArrayList<GameObject> objs;
	int[][] data;
	
	/**
	 * Initializes world with world data
	 * @param worldData
	 */
	public World(int[][] worldData) {
		objs = new ArrayList<GameObject>();
		this.data = worldData;
		
		LoadWorld(null);
	}
	
	/**
	 * Initializes world with world data, and obj data
	 * @param worldData
	 */
	public World(int[][] worldData, int[][] objData) {
		objs = new ArrayList<GameObject>();
		this.data = worldData;
		
		LoadWorld(objData);
	}
	
	/**
	 * Converts world data to Game Objects/Blocks
	 */
	private void LoadWorld(int[][] objData) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				objs.add(new Block(i*32-data.length*32/2, j*32-data.length*32/2, data[i][j]));
			}
		}
		if (objData != null) {
			for (int i = 0; i < objData.length; i++) {
				for (int j = 0; j < objData[i].length; j++) {
					if (objData[i][j] == 1) {
						objs.add(new PlayableCharacter(i*32-data.length*32/2, j*32-data.length*32/2, new Animation(SpriteSheet.LoadSpriteSheet("/assets/animsets/player.png"))));
					}
				}
			}
		}
		objs.add(new Warp(128, 0, 1));
	}
	
	/**
	 * Gets input for objects that have input function
	 */
	public void GetInput() {
		for (GameObject i : objs) {
			if (i.isPlayableCharacter()) {
				i.GetInput();
			}
		}
	}
	
	/**
	 * Updates all game objects
	 */
	public void Update() {
		for (GameObject i : objs) {
			i.Update();
		}
	}
	
	/**
	 * draws all renderable objects
	 */
	public void Draw() {
		for (GameObject i : objs) {
			i.Draw();
		}
	}
	
	/**
	 * Adds a playable character to the world
	 * @param p
	 */
	public void AddPlayableCharacter(PlayableCharacter p) {
		objs.add(p);
	}
	
	/**
	 * returns the current playable character
	 * @return
	 */
	public PlayableCharacter GetCurrentPlayer() {
		for (GameObject i : objs) {
			if (i.isPlayableCharacter() && ((PlayableCharacter) i).GetPlayerID() == 0) {
				return (((PlayableCharacter) i));
			}
		}
		return null;
	}
	
	/**
	 * returns the stats of the playable character with the given id
	 * @param id
	 * @return
	 */
	public Stats GetPlayerStats(int id) {
		for (GameObject i: objs) {
			if (i.isPlayableCharacter() && ((PlayableCharacter) i).GetPlayerID() == id) {
				return ((PlayableCharacter) i).GetStats();
			}
		}
		return null;
	}

}

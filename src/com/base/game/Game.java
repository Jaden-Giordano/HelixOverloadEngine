package com.base.game;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.base.engine.World;
import com.base.handlers.FileHandler;
import com.base.handlers.GuiHandler;
import com.base.handlers.Input;

public class Game {
	
	private static ArrayList<World> worlds;
	static int currentWorld;
	
	private static int debugState;
	
	/**
	 * Creates new game
	 * @throws IOException
	 */
	public Game() throws IOException {
		worlds = new ArrayList<World>();
		debugState = 0;
		
		LoadWorlds();
	}
	
	/**
	 * Loads World Data and adds them to the world.
	 * @throws IOException
	 */
	private void LoadWorlds() throws IOException {
		World test2 = new World(FileHandler.LoadTileData("/assets/worlds/test/largetest"), FileHandler.LoadTileData("/assets/worlds/test/objtest"));
		World hi = new World(FileHandler.LoadTileData("/assets/worlds/Hi/hi"), FileHandler.LoadTileData("/assets/worlds/test/objtest"));
		
		worlds.add(test2);
		worlds.add(hi);
		
		currentWorld = 0;
	}

	/**
	 * Gets input
	 */
	public void GetInput() {
		while(Keyboard.next() || Mouse.next()) {
			worlds.get(currentWorld).GetInput();
			GuiHandler.GetInput();
			if (Input.KeyPressed(Keyboard.KEY_2) && debugState != 0) {
				SetDebugState(1);
			}
			if (Input.KeyPressed(Keyboard.KEY_1) && debugState != 1) {
				SetDebugState(0);
			}
			if (Input.KeyPressed(Keyboard.KEY_3)) {
				currentWorld = 0;
			}
			if (Input.KeyPressed(Keyboard.KEY_4)) {
				currentWorld = 1;
			}
		}
	}
	/**
	 * Updates
	 */
	public void Update() {
		worlds.get(currentWorld).Update();
	}
	
	/**
	 * Renders
	 */
	public void Draw() {
		worlds.get(currentWorld).Draw();
	}
	
	/**
	 * returns the current debug mode
	 * @return
	 */
	public static int IsInIDMode() {
		return debugState;
	}
	
	/**
	 * Sets the debug state to the given state
	 * @param s
	 */
	public static void SetDebugState(int state) {
		debugState = state;
		GuiHandler.AddMessage("Debug State set to: "+String.valueOf(state));
	}
	
	/**
	 * returns the current loaded and open world
	 * @return
	 */
	public static World GetCurrentWorld() {
		return worlds.get(currentWorld);
	}
	
	public static void SetCurrentWorld(int destWorldId) {
		currentWorld = destWorldId;
	}
	
}

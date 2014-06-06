package com.base.game;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.SpriteSheet;

import com.base.engine.Animation;
import com.base.engine.Camera;
import com.base.engine.World;
import com.base.engine.gameobject.PlayableCharacter;
import com.base.handlers.FileHandler;
import com.base.handlers.GuiHandler;
import com.base.handlers.Input;

public class Game {
	
	ArrayList<World> worlds;
	int currentWorld;
	
	Camera cam;
	
	private static boolean[] debugState;
	
	public Game() throws IOException {
		worlds = new ArrayList<World>();
		debugState = new boolean[2];
		for (int i = 0; i < debugState.length; i++) {
			debugState[i] = false;
		}
		debugState[0] = true;
		
		LoadWorlds();
	}
	
	/**
	 * Loads World Data and adds them to the world.
	 * @throws IOException
	 */
	private void LoadWorlds() throws IOException {
		World test = new World(FileHandler.LoadTileData("/assets/worlds/test", 4));
		World test2 = new World(FileHandler.LoadTileData("/assets/worlds/largetest", 26));
		
		worlds.add(test);
		worlds.add(test2);
		
		currentWorld = 1;
		
		worlds.get(currentWorld).AddPlayableCharacter(new PlayableCharacter(0, 0, new Animation(new SpriteSheet(FileHandler.LoadImage("/assets/animsets/player.png"), 32, 32))));
		
		GuiHandler.AddMessage("To Aidan", "Aidan why you gotta be so busy all the time??? Like come on..........");
	}

	public void GetInput() {
		while(Keyboard.next()) {
			worlds.get(currentWorld).GetInput();
			GuiHandler.GetInput();
			if (Input.KeyPressed(Keyboard.KEY_2) && !debugState[1]) {
				SetDebugState(1);
			}
			if (Input.KeyPressed(Keyboard.KEY_1) && !debugState[0]) {
				SetDebugState(0);
			}
		}
	}
	
	int index = 0;
	public void Update() {
		index++;
		worlds.get(currentWorld).Update();
		//worlds.get(currentWorld).AddPlayableCharacter(new PlayableCharacter(index, index%100, new Animation(new SpriteSheet(FileHandler.LoadImage("/assets/animsets/player.png"), 32, 32))));
	}
	
	public void Draw() {
		worlds.get(currentWorld).Draw();
	}
	
	public static boolean IsInIDMode() {
		return debugState[1];
	}
	
	public static void SetDebugState(int s) {
		for (int i = 0; i < debugState.length; i++) {
			debugState[i] = false;
		}
		debugState[s] = true;
		GuiHandler.AddMessage("Debug State set to: "+String.valueOf(s));
	}
	
}

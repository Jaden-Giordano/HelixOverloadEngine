package com.base.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glViewport;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;

import com.base.game.Game;
import com.base.handlers.FileHandler;
import com.base.handlers.GuiHandler;

public class Main {
	
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static final float ASPECT = SCREEN_WIDTH/SCREEN_HEIGHT;
	
	private static Game game;
	
	public static void main(String[] args) throws SlickException, LWJGLException, IOException {
		InitDisplay();
		InitGL();
		InitGame();
		
		GameLoop();
		
		CleanUp();
	}

	/**
	 * Initializes Display and Keyboard
	 * @throws LWJGLException
	 */
	private static void InitDisplay() throws LWJGLException {
		Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
		Display.create();
		Display.setVSyncEnabled(true);
		Display.setTitle("BTK");
		Keyboard.create();
	}

	/**
	 * Initializes OpenGL
	 * @throws LWJGLException
	 */
	private static void InitGL() throws LWJGLException {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_DEPTH_TEST);
		
		glClearColor(0, 0, 0, 0);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	/**
	 * Initializes Game Variables/Objects
	 * @throws SlickException
	 * @throws IOException
	 */
	private static void InitGame() throws SlickException, IOException {
		SpriteSheet.InitSpriteSheet();
		Registry.Add(FileHandler.GetFont("/assets/fonts/Ubuntu-R.ttf", 18f));
		Registry.Add(FileHandler.GetFont("/assets/fonts/Ubuntu-B.ttf", 19f));
		GuiHandler.Init();
		game = new Game();
		GuiHandler.AddMessage("Game Log", "Loading Complete");
	}

	private static void GameLoop() throws SlickException {
		while (!Display.isCloseRequested()) {
			GetInput();
			Update();
			Draw();
		}
	}

	private static void GetInput() {
		game.GetInput();
		Camera.GetInput();
	}

	private static void Update() {
		game.Update();
		Camera.Update();
		GuiHandler.Update();
	}

	private static void Draw() throws SlickException {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		game.Draw();
		GuiHandler.Draw();
		
		Display.update();
		Display.sync(60);
	}

	private static void CleanUp() {
		Display.destroy();
		Keyboard.destroy();
	}

}

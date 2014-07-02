package com.base.handlers;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.base.engine.Stats;
import com.base.engine.gui.Button;
import com.base.engine.gui.GuiElement;
import com.base.engine.gui.MessageBox;
import com.base.engine.gui.StatsBox;
import com.base.game.Game;

public class GuiHandler {
	
	private static GuiElement[] elements;
	
	private static ArrayList<MessageBox> msgQueue;
	
	/**
	 * Initializes the Gui Handler
	 */
	public static void Init() {
		elements = new GuiElement[3];
		msgQueue = new ArrayList<MessageBox>();
		
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
	}
	
	/**
	 * Adds a message box to the gui with given message
	 * @param msg
	 */
	public static void AddMessage(String msg) {
		if (elements[1] == null && msgQueue.size() != 0) {
			elements[1] = new MessageBox(msg);
		}
		else {
			msgQueue.add(new MessageBox(msg));
		}
	}
	
	/**
	 * Adds message box to the gui with given message and title
	 * @param title
	 * @param msg
	 */
	public static void AddMessage(String msg, String title) {
		if (elements[1] == null && msgQueue.size() == 0) {
			elements[1] = new MessageBox(msg, title);
		}
		else {
			msgQueue.add(new MessageBox(msg, title));
		}
	}
	
	/**
	 * Displays a stats box on the gui with the given stats
	 * @param stats
	 */
	public static void PopStatsBox(Stats stats) {
		if (elements[0] == null) {
			elements[0] = new StatsBox(stats);
		}
	}
	
	/**
	 * Adds a button to the gui with the given button object
	 * @param b
	 */
	public static void AddButton(Button b) {
		elements[2] = b;
	}
	
	/**
	 * Gets to input on the gui layer
	 */
	public static void GetInput() {
		if (elements[1] != null) {
			if (Input.KeyPressed(Keyboard.KEY_RETURN) && ((MessageBox) elements[1]).IsMessageComplete()) {
				elements[1] = null;
			}
			else if (Input.KeyPressed(Keyboard.KEY_RETURN) && !((MessageBox) elements[1]).IsMessageComplete()) {
				((MessageBox) elements[1]).DisplayEntireMessage();
			}
		}
		
		if (elements[0] != null) {
			if (Input.KeyPressed(Keyboard.KEY_F)) {
				elements[0] = null;
			}
		}
		else if (elements[0] == null) {
			if (Input.KeyPressed(Keyboard.KEY_F)) {
				PopStatsBox(Game.GetCurrentWorld().GetPlayerStats(0));
			}
		}
		
		if (elements[2] != null) {
			elements[2].GetInput();
		}
	}
	
	/**
	 * Updates the gui layer
	 */
	public static void Update() {
		if (elements[1] != null) {
			elements[1].Update();
		}
		else if (elements[1] == null && msgQueue.size() != 0) {
			elements[1] = msgQueue.get(0);
			msgQueue.remove(0);
		}
		
		if (elements[2] != null) {
			elements[2].Update();
		}
	}
	
	/**
	 * draws the gui layer
	 */
	public static void Draw() {
		if (elements[1] != null) {
			elements[1].Draw();
		}
		
		if (elements[0] != null) {
			elements[0].Draw();
		}
		
		if (elements[2] != null) {
			elements[2].Draw();
		}
	}
	
	public static Button GetButton() {
		return (Button) elements[2];
	}

}

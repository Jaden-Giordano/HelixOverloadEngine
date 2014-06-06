package com.base.handlers;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.base.engine.gui.GuiElement;
import com.base.engine.gui.MessageBox;

public class GuiHandler {
	
	private static GuiElement[] elements;
	
	private static ArrayList<MessageBox> msgQueue;
	
	public static void Init() {
		elements = new GuiElement[2];
		msgQueue = new ArrayList<MessageBox>();
		
		for (int i = 0; i < elements.length; i++) {
			elements[i] = null;
		}
	}
	
	public static void AddMessage(String msg) {
		if (elements[1] == null && msgQueue.size() != 0) {
			elements[1] = new MessageBox(msg);
		}
		else {
			msgQueue.add(new MessageBox(msg));
		}
	}
	
	public static void AddMessage(String title, String msg) {
		if (elements[1] == null && msgQueue.size() == 0) {
			elements[1] = new MessageBox(title ,msg);
		}
		else {
			msgQueue.add(new MessageBox(title, msg));
		}
	}
	
	public static void GetInput() {
		if (elements[1] != null) {
			if (Input.KeyPressed(Keyboard.KEY_RETURN) && elements[1].IsMessageComplete()) {
				elements[1] = null;
			}
			else if (Input.KeyPressed(Keyboard.KEY_RETURN) && !elements[1].IsMessageComplete()) {
				elements[1].DisplayEntireMessage();
			}
		}
	}
	
	public static void Update() {
		if (elements[1] != null) {
			elements[1].Update();
		}
		else if (elements[1] == null && msgQueue.size() != 0) {
			elements[1] = msgQueue.get(0);
			msgQueue.remove(0);
		}
	}
	
	public static void Draw() {
		if (elements[1] != null) {
			elements[1].Draw();
		}
	}

}

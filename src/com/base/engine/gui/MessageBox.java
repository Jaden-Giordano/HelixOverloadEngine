package com.base.engine.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import com.base.engine.Registry;
import com.base.engine.Timer;
import com.base.handlers.FileHandler;

public class MessageBox extends GuiElement {
	
	private static final int CHARS_PER_LINE = 100;
	private static final int LINES = 3;
	private static final int UPDATE_TIME = 80;
	
	private Timer timer = new Timer();
	
	private TrueTypeFont font, titleFont;
	
	private String message, title;
	
	private char[][] splitMessage;
	String[] finalMessage = new String[LINES];
	
	private Image box = FileHandler.LoadImage("/assets/gui/msgbox.png");
	
	private boolean messageComplete = false;

	/**
	 * Creates new message box
	 * @param displayTime in milliseconds
	 * @param message
	 */
	public MessageBox(String message) {
		super(0, 0);
		this.message = message;
		this.font = Registry.GetFont(0);
		this.titleFont = Registry.GetFont(1);
		
		SplitMessage();
		
		timer.Start();
	}
	
	/**
	 * Creates new message box with custom title
	 * @param title
	 * @param message
	 */
	public MessageBox(String title, String message) {
		super(0, 0);
		this.message = message;
		this.title = title;
		this.font = Registry.GetFont(0);
		this.titleFont = Registry.GetFont(1);
		
		SplitMessage();
		
		timer.Start();
	}
	
	/**
	 * Splits message into lines
	 */
	private void SplitMessage() {
		char[] textsplitMessage = message.toCharArray();
		
		int jIndex = 0;
		splitMessage = new char[LINES][CHARS_PER_LINE];
		for (int i = 0; i < textsplitMessage.length; i++) {
			if (i >= CHARS_PER_LINE && i%CHARS_PER_LINE == 0) {
				jIndex++;
			}
			if (jIndex >= 3) {
				jIndex = 0;
				break;
			}
			splitMessage[jIndex][i%CHARS_PER_LINE] = textsplitMessage[i];
		}
		
		for (int j = 0; j < finalMessage.length; j++) {
			if (finalMessage[j] == null) {
				finalMessage[j] = "";
			}
		}
	}
	
	/**
	 * Does nothing, but would update the amount of text shown in the message.
	 * still in progress
	 */
	int amtOfTextShown = 0;
	boolean update = true;
	public void Update() {
		if (amtOfTextShown >= message.length()) {
			messageComplete = true;
		}
		if (!messageComplete) {
			if (update) {
				int linesShown = amtOfTextShown/CHARS_PER_LINE;
				for (int j = 0; j < linesShown+1; j++) {
					if (finalMessage[j] == null) {
						finalMessage[j] = "";
					}
					if ((amtOfTextShown >= CHARS_PER_LINE && j == 0)) {
						j++;
						if (amtOfTextShown >= CHARS_PER_LINE*2 && (j == 0 || j == 1)) {
							j = 2;
						}
					}
					finalMessage[j] += String.valueOf(splitMessage[j][amtOfTextShown%CHARS_PER_LINE]);
				}
				update = false;
			}
			
			double timeSinceLetterUpdate = timer.GetElapsedTime();
			if (timeSinceLetterUpdate > UPDATE_TIME) {
				amtOfTextShown++;
				timer.Start();
				update = true;
			}
		}
	}
	
	/**
	 * Draws the message and message box
	 */
	public void Draw() {
		if (box != null) {
			box.draw();
		}
		if (title != null) {
			titleFont.drawString(0, 0, title);
		}
		else {
			titleFont.drawString(0, 0, "MessageBox");
		}
		for (int j = 0; j < finalMessage.length; j++) {
			font.drawString(0, j*18+20, finalMessage[j]);
		}
	}
	
	/**
	 * Resets the timer back to 0
	 */
	public void ResetTimer() {
		timer.Start();
	}
	
	public String GetText() {
		return this.message;
	}
	
	public boolean IsMessageComplete() {
		return this.messageComplete;
	}
	
	public void DisplayEntireMessage() {
		for (int j = 0; j < finalMessage.length; j++) {
			finalMessage[j] = String.valueOf(splitMessage[j]);
		}
		amtOfTextShown = message.length();
	}

}

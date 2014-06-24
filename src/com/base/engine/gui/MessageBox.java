package com.base.engine.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import com.base.engine.Registry;
import com.base.engine.Timer;
import com.base.handlers.FileHandler;

public class MessageBox extends GuiElement {
	
	private static final int WORDS_PER_LINE = 14;
	private static final int LINES = 3;
	private static final int UPDATE_TIME = 40;
	
	private Timer timer = new Timer();
	
	private TrueTypeFont font, titleFont;
	
	private String message, title;
	
	private char[][] splitMessage;
	String[] finalMessage = new String[LINES];
	
	private Image box = FileHandler.LoadImage("/assets/gui/msgbox.png");
	
	private boolean messageComplete = false;

	/**
	 * Creates new message box with given message
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
	 * Creates new message box with custom title and given message
	 * @param title
	 * @param message
	 */
	public MessageBox(String message, String title) {
		super(0, 0);
		this.message = message;
		this.title = title;
		this.font = Registry.GetFont(0);
		this.titleFont = Registry.GetFont(1);
		
		SplitMessage();
		
		timer.Start();
	}
	
	/**
	 * Splits the message into lines then characters
	 */
	private void SplitMessage() {
		String[] words = message.split("\\s");
		
		int jIndex = 0;
		String[] lines = new String[LINES];
		for (int j = 0; j < lines.length; j++) {
			lines[j] = "";
		}
		for (int i = 0; i < words.length; i++) {
			if (i != 0 && i%WORDS_PER_LINE == 0) {
				jIndex++;
			}
			if (words[i] == null) {
				words[i] = "";
			}
			lines[jIndex] += words[i]+" ";
		}
		
		for (int j = 0; j < lines.length; j++) {
			if (lines[j] == null) {
				lines[j] = "";
			}
		}
		splitMessage = new char[LINES][];
		for (int j = 0; j < splitMessage.length; j++) {
			splitMessage[j] = lines[j].toCharArray();
			if (finalMessage[j] == null) {
				finalMessage[j] = "";
			}
		}
	}
	
	/**
	 * Updates the amount of text shown, and if a letter update
	 */
	int amtOfTextShown = 0;
	boolean update = true;
	public void Update() {
		if (amtOfTextShown >= message.length()) {
			messageComplete = true;
		}
		if (!messageComplete) {
			if (update) {
				int oneshown = splitMessage[0].length;
				int twoshown = splitMessage[0].length + splitMessage[1].length;
				int threeshown = splitMessage[0].length + splitMessage[1].length+splitMessage[2].length;
				
				int linesShown = 1;
				if (amtOfTextShown > oneshown && amtOfTextShown <= twoshown) {
					linesShown = 2;
				}
				if (amtOfTextShown > twoshown && amtOfTextShown <= threeshown) {
					linesShown = 3;
				}
				
				for (int j = 0; j < linesShown; j++) {
					if ((amtOfTextShown >= oneshown && j == 0) || (amtOfTextShown >= twoshown && j == 1)) {
						continue;
					}
					
					if ((amtOfTextShown >= threeshown && j == 2)) {
						DisplayEntireMessage();
						break;
					}
					
					if (j == 1) {
						finalMessage[j] += String.valueOf(splitMessage[j][amtOfTextShown-oneshown-1%(splitMessage[j-1].length+1)]);
					}
					else if (j == 2) {
						finalMessage[j] += String.valueOf(splitMessage[j][amtOfTextShown-twoshown-1%(splitMessage[j-1].length+1)]);
					}
					else {
						finalMessage[j] += String.valueOf(splitMessage[j][amtOfTextShown%(splitMessage[j].length+1)]);
					}
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
		if (messageComplete) {
			DisplayEntireMessage();
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
	
	/**
	 * returns the message
	 * @return
	 */
	public String GetText() {
		return this.message;
	}
	
	/**
	 * returns if the message is complete
	 * @return
	 */
	public boolean IsMessageComplete() {
		return this.messageComplete;
	}
	
	/**
	 * Displays the entire message
	 */
	public void DisplayEntireMessage() {
		for (int j = 0; j < finalMessage.length; j++) {
			finalMessage[j] = String.valueOf(splitMessage[j]);
		}
		amtOfTextShown = message.length();
	}

}

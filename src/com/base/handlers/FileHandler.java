package com.base.handlers;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FileHandler {
	
	/**
	 * Loads Font from file and converts it to TrueTypeFont for render use
	 * @param path
	 * @param size
	 * @return
	 */
	public static TrueTypeFont GetFont(String path, float size) {
		try {
			InputStream input = ResourceLoader.getResourceAsStream(path);
			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, input);
			awtFont = awtFont.deriveFont(size);
			TrueTypeFont f =  new TrueTypeFont(awtFont, false);
			return f;
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * loads and returns image from file
	 * @param path
	 * @return
	 */
	public static Image LoadImage(String path) {
		try {
			Image tmp = new Image(path);
			return tmp;
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Loads and converts world data for use in worlds
	 * @param path
	 * @param width "amount of horizontal tiles"
	 * @return
	 * @throws IOException
	 */
	public static int[][] LoadTileData(String path) throws IOException {
		
		String[] lines = OpenFile(System.getProperty("user.dir")+path+".MAP");
		
		String[] WH = lines[0].split(",");
		int iWidth = Integer.parseInt(WH[0]);
		int iHeight = Integer.parseInt(WH[1]);
		
		String[][] parts = new String[iHeight][iWidth];
		for (int j = 1; j < parts.length+1; j++) {
			parts[j-1] = lines[j].split("\\s");
		}
			
		int[][] worldData = new int[iWidth][iHeight];
		for (int j = 0; j < parts.length; j++) {
			for (int i = 0; i < parts[j].length; i++) {
				worldData[i][j] = Integer.parseInt(parts[j][i]);
			}
		}
		
		return worldData;
	}
	
	/**
	 * Breaks up world data into lines
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static String[] OpenFile(String path) throws IOException {
		BufferedReader textReader = new BufferedReader(new FileReader(path));
		
		String[] textData = new String[GetLines(path)];
		for (int i = 0; i < textData.length; i++) {
			textData[i] = textReader.readLine();
		}
		
		textReader.close();
		return textData;
	}
	
	/**
	 * Get the amount of lines in the world File
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private static int GetLines(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			i++;
		}
		
		br.close();
		return i;
	}
	
}
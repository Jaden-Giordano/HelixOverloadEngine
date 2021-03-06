package com.base.engine;

import java.util.ArrayList;

import org.newdawn.slick.TrueTypeFont;

public class Registry {
	
	private static ArrayList<TrueTypeFont> fonts = new ArrayList<TrueTypeFont>();
	
	/**
	 * Adds Font to the Font TrueTypeFont Registry
	 * @param f
	 */
	public static void Add(TrueTypeFont f) {
		fonts.add(f);
	}
	
	/**
	 * returns a font from the registry with the given id of the font
	 * @param id
	 * @return
	 */
	public static TrueTypeFont GetFont(int id) {
		return fonts.get(id);
	}

}

package com.base.engine;

public class Stats {
	
	private final int LEVEL_CONSTANT = 1;
	
	private int xp;
	private String name;

	/**
	 * Creates stats with given starting xp
	 * @param xp
	 */
	public Stats(int xp) {
		this.xp = xp;
	}
	
	/**
	 * Creates stats with given starting xp and name
	 * @param xp
	 * @param name
	 */
	public Stats(int xp, String name) {
		this.xp = xp;
		this.name = name;
	}
	
	/**
	 * returns the current stats level based on xp
	 * in progress
	 */
	public int GetLevel() {
		return xp*LEVEL_CONSTANT;
	}
	
	/**
	 * Adds given xp to the stats xp
	 * @param xp
	 */
	public void AddXp(int xp) {
		this.xp += xp;
	}
	
	/**
	 * returns the name
	 * if has name -> return name
	 * if has no name -> return "default"
	 * @return
	 */
	public String GetName() {
		if (name != null) {
			return this.name;
		}
		else {
			return "default";
		}
	}

}

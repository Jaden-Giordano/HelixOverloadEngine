package com.base.engine;

import static org.lwjgl.Sys.*;

public class Timer {
	
	private double startTime;

	public Timer() {
	}
	
	/**
	 * Set Timer to current time
	 */
	public void Start() {
		startTime = getTime();
	}
	
	/**
	 * Return the amount of time passed since timer Start()
	 * @return
	 */
	public double GetElapsedTime() {
		return getTime() - startTime;
	}

}

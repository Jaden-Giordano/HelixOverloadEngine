package com.base.engine.gui;

import org.newdawn.slick.Image;

import com.base.engine.Main;
import com.base.engine.Registry;
import com.base.engine.Stats;
import com.base.handlers.FileHandler;

public class StatsBox extends GuiElement {
	
	private Stats stats;
	private Image box;

	/**
	 * Creates a new stats box with the given stats
	 * @param stats
	 */
	public StatsBox(Stats stats) {
		super(Main.SCREEN_WIDTH-100, Main.SCREEN_HEIGHT-75);
		this.stats = stats;
		this.box = FileHandler.LoadImage("/assets/gui/statsbox.png");
	}
	
	/**
	 * Draws the stats on the gui layer
	 */
	public void Draw() {
		if (stats != null) {
				box.draw(this.x, this.y);
				Registry.GetFont(0).drawString(this.x+0, this.y+0, stats.GetName());
				Registry.GetFont(0).drawString(this.x+0, this.y+25, "Level: "+stats.GetLevel());
		}
		else {
			System.out.println("Null stats");
		}
	}

}

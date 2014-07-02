package com.base.engine.gameobject;

import java.awt.Rectangle;

import com.base.game.Game;
import com.base.handlers.FileHandler;
import com.base.handlers.TransitionHandler;

public class Warp extends RenderableObject {
	
	float destX, destY;
	private int destWorldId;
	private boolean worldWarp;
	
	private boolean enterWarp;
	
	private int transID = -1;

	/**
	 * Creates a new warp with starting x, y coordinate and destination coordinate
	 * @param x
	 * @param y
	 * @param destX
	 * @param destY
	 */
	public Warp(float x, float y, float destX, float destY) {
		super(x, y, FileHandler.LoadImage("/assets/objects/portal.png"));
		this.destX = destX;
		this.destY = destY;
		worldWarp = false;
		enterWarp = false;
	}
	
	public Warp(float x, float y, int destW) {
		super(x, y, FileHandler.LoadImage("/assets/objects/portal.png"));
		this.destWorldId = destW;
		worldWarp = true;
		enterWarp = false;
	}
	
	/**
	 * Checks if player collides and teleports to destination coordinate
	 */
	public void Update() {
		if (PlayerCollision(Game.GetCurrentWorld().GetCurrentPlayer()) && !enterWarp) {
			enterWarp = true;
			transID = TransitionHandler.AddTransition(0, 2000);
		}

		if (transID != -1) {
			if (TransitionHandler.GetTransition(transID).IsComplete() && enterWarp) {
				if (!worldWarp) {
					if (PlayerCollision(Game.GetCurrentWorld().GetCurrentPlayer())) {
						Game.GetCurrentWorld().GetCurrentPlayer().Teleport(this.destX, this.destY);
					}
				}
				else {
					if (PlayerCollision(Game.GetCurrentWorld().GetCurrentPlayer())) {
						Game.GetCurrentWorld().GetCurrentPlayer().Teleport(destWorldId);
					}
				}
				TransitionHandler.RemoveTransition(transID);
				transID = -1;
				enterWarp = false;
			}
		}
	}
	
	/**
	 * Temporary..... Checks if player collision is there
	 * @param p
	 * @return
	 */
	private boolean PlayerCollision(PlayableCharacter p) {
		Rectangle wRect = new Rectangle((int) this.x, (int) this.y, 32, 32);
		Rectangle pRect = new Rectangle((int) p.GetX(), (int) p.GetY(), 32, 32);
		
		return pRect.intersects(wRect);
	}

}

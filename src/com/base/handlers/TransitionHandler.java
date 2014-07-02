package com.base.handlers;

import java.util.ArrayList;

import com.base.engine.Transition;

public class TransitionHandler {

	private static ArrayList<Transition> trans = new ArrayList<Transition>();
	
	public static void Update() {
		for (Transition i : trans) {
			i.Update();
		}
	}
	
	public static void Draw() {
		for (Transition i : trans) {
			i.Draw();
		}
	}
	
	public static int AddTransition(int type, int len) {
		if (type == 0) {
			trans.add(new Transition(len));
			return trans.size()-1;
		}
		else {
			System.out.println("Quit fuckin shit up...");
			return -1;
		}
	}
	
	public static void RemoveTransition(int id) {
		trans.remove(id);
	}
	
	public static Transition GetTransition(int id) {
		return trans.get(id);
	}
	
}

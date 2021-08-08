package com.mygdx.game.characters;

import com.mygdx.game.characters.attacks.Steinschleuder;

public class Kind {
	private String name;
	private int maxHP;
	private int currentHP;
	private Steinschleuder steinschleuder;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public int getCurrentHP() {
		return currentHP;
	}
	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	public Steinschleuder getSteinschleuder() {
		return steinschleuder;
	}
	public void setSteinschleuder(Steinschleuder steinschleuder) {
		this.steinschleuder = steinschleuder;
	}
	
	
}
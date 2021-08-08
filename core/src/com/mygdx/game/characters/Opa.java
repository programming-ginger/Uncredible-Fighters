package com.mygdx.game.characters;

import com.mygdx.game.characters.attacks.Rollator;

public class Opa {
	private String name;
	private int maxHP;
	private int currentHP;
	private Rollator rollator;
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
	public Rollator getRollator() {
		return rollator;
	}
	public void setRollator(Rollator rollator) {
		this.rollator = rollator;
	}
}
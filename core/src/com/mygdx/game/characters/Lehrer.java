package com.mygdx.game.characters;

import com.mygdx.game.characters.attacks.Lineal;

public class Lehrer {
	private String name;
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
	public Lineal getLineal() {
		return lineal;
	}
	public void setLineal(Lineal lineal) {
		this.lineal = lineal;
	}
	private int maxHP;
	private int currentHP;
	private Lineal lineal;
}
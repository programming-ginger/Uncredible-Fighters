package com.mygdx.game.characters;

import com.mygdx.game.characters.attacks.Kugelschreiber;

public class Politiker {
	private String name;
	private int maxHP;
	private int currentHP;
	private Kugelschreiber kugelschreiber;
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
	public Kugelschreiber getKugelschreiber() {
		return kugelschreiber;
	}
	public void setKugelschreiber(Kugelschreiber kugelschreiber) {
		this.kugelschreiber = kugelschreiber;
	}
	
	
}

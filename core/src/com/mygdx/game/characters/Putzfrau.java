package com.mygdx.game.characters;

import com.mygdx.game.characters.attacks.Pfuetze;

public class Putzfrau {
	private String name;
	private int maxHP;
	private int currentHP;
	private Pfuetze pfuetze;
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
	public Pfuetze getPfuetze() {
		return pfuetze;
	}
	public void setPfuetze(Pfuetze pfuetze) {
		if(pfuetze != null) {
		this.pfuetze = pfuetze;
		}
	}
}
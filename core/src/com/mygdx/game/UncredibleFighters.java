package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class UncredibleFighters extends Game {
	
	private static UncredibleFighters instance;
	
	public UncredibleFighters () {
		if (instance == null) {
			instance = this;
		}
		else throw new IllegalArgumentException("Game Objekt wird mehrfach erstellt");
	}
	
	public static UncredibleFighters getInstance() {
		return instance;
	}
	
	@Override
    public void create() {
		showMenuScreen();
    }

    @Override
    public void dispose() {
    	this.screen.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
    	this.screen.resize(width, height);
    }
    
    public void showMenuScreen() {
    	Screen menu = new MenuScreen();
        setScreen(menu);
    }
    
    public void showFightingScreen() {
    	this.screen.dispose();
    	setScreen(new FightingScreen());
    }
}

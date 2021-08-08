package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.prototype.PrototypeCharMove;

public class FightingScreen implements Screen {

	private Camera camera;
	private Viewport viewport;
	private SpriteBatch batch = new SpriteBatch();
	
	PrototypeCharMove fights;


	@Override
	public void show() {
	
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.draw(new Texture("badlogic.jpg"), 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	}

}

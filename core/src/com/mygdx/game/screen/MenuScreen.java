package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.UncredibleFighters;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.*;
import com.mygdx.game.sound.SoundPlayer;

public class MenuScreen implements Screen {

	private UncredibleFighters game;

	private OrthographicCamera camera;
	private Viewport viewport;
	protected SpriteBatch batch;

	protected Texture background;
	protected Texture bottomBar;

	protected Array<MenuItem> items;
	protected Array<PassiveTexture> passiveTextures;

	protected MenuItem currentSelection;

	public boolean isActive = true;
	private boolean clickFromScreenBefore = true;
	private boolean wasClickedOnLastFrame;

	public MenuScreen() {
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.getWindowWidth(), Options.getWindowHeight());
		this.viewport = new FitViewport(Options.getWindowWidth(), Options.getWindowHeight(), camera);
		this.batch = new SpriteBatch();

		items = new Array<>();
		passiveTextures = new Array<>();
	}

	public void setBackground(Texture background) {
		this.background = background;
	}

	public void addMenuItem(MenuItem item) {
		this.items.add(item);

		if (this.items.size == 1) {
			this.currentSelection = this.items.get(0);
		}
	}

	public void addPassiveTexture(PassiveTexture item) {
		this.passiveTextures.add(item);
	}

	public void clear() {
		items.clear();
		passiveTextures.clear();
		clickFromScreenBefore = true;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		updateSelection();

		batch.begin();

		if (background != null) {
			batch.draw(background, 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
		}

		for (MenuItem button : this.items) {
			button.draw(batch, button == currentSelection);
		}

		for (PassiveTexture textures : this.passiveTextures) {
			textures.draw(batch);
		}

		batch.end();
		wasClickedOnLastFrame = Gdx.input.isTouched();
	}

	protected Vector2 getMousePosition() {
		Vector3 touchPos3 = new Vector3();
		touchPos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos3);

		return new Vector2(touchPos3.x, touchPos3.y);
	}

	protected void updateSelection() {

		Vector2 touchPos = getMousePosition();

		for (MenuItem item : this.items) {
			if (item.contains(touchPos.x, touchPos.y)) {

				if (item != currentSelection) {
					SoundPlayer.playSelectionSound();
				}
				currentSelection = item;
				break;
			}
		}

		MenuItem newSelection = null;
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			newSelection = currentSelection.getItemBelow();
		}

		else if (Gdx.input.isKeyJustPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			newSelection = currentSelection.getItemAbove();
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			newSelection = currentSelection.getItemLeft();
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			newSelection = currentSelection.getItemRight();
		}

		if (newSelection != null && newSelection != currentSelection) {
			SoundPlayer.playSelectionSound();
			currentSelection = newSelection;
		}

		boolean clicked = Gdx.input.isTouched();
		currentSelection.update(batch, touchPos, clicked && !clickFromScreenBefore, clicked & !this.wasClickedOnLastFrame);
		
		if (!clicked) {
			clickFromScreenBefore = false;
		}
	}

	@Override
	public void resize(int width, int height) {
		Options.setWindowHeight(height);
		Options.setWindowWidth(width);
		//viewport.update(width, height);

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
		batch.dispose();

		if (background != null) {
			background.dispose();
		}

		for (MenuItem button : this.items) {
			button.dispose();
		}

		for (PassiveTexture texture : this.passiveTextures) {
			texture.dispose();
		}
	}

}

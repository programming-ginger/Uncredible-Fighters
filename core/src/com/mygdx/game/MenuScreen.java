package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.menu.*;

public class MenuScreen implements Screen {

	private UncredibleFighters game;

	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;

	private Texture background;
	private PassiveTexture logo;

	Array<MenuItem> items;

	private int currentSelection;

	private final static float LOGO_SIZE = 0.2f;
	private final static float LOGO_Y = 0.95f;

	public MenuScreen() {
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.getWindowWidth(), Options.getWindowHeight());
		this.viewport = new StretchViewport(Options.getWindowWidth(), Options.getWindowHeight(), camera);
		this.batch = new SpriteBatch();

		items = new Array<>();

		// Logo
		Texture texture = new Texture("Logo.PNG");
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		float boxHeight = Options.getWindowWidth() * LOGO_SIZE;
		float boxWidth = Options.getWindowWidth() * LOGO_SIZE * ratio;
		float x = Options.getWindowWidth() / 2f - boxWidth / 2f;
		float y = Options.getWindowHeight() * LOGO_Y - boxHeight;

		Rectangle rectangle = new Rectangle(x, y, boxWidth, boxHeight);
		PassiveTexture button = new PassiveTexture(texture, rectangle);
		this.logo = (button);

		this.currentSelection = 0;
	}

	public void setBackground(Texture background) {
		this.background = background;
	}

	public void addMenuItem(MenuItem item) {
		this.items.add(item);
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
			button.draw(batch);
		}
		logo.draw(batch);

		if (this.items.size > currentSelection) {
			this.items.get(currentSelection).select(batch);
		}

		batch.end();

	}

	private Vector2 getMousePosition() {
		Vector3 touchPos3 = new Vector3();
		touchPos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos3);

		return new Vector2(touchPos3.x, touchPos3.y);
	}

	private void updateSelection() {

		Vector2 touchPos = getMousePosition();

		for (int i = 0; i < this.items.size; i++) {
			if (this.items.get(i).contains(touchPos.x, touchPos.y)) {
				currentSelection = i;
				i = this.items.size;
			}
		}

		if (this.items.size > currentSelection) {
			this.items.get(currentSelection).update(batch, touchPos);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			currentSelection++;
			currentSelection = currentSelection % this.items.size;
		}

		else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			currentSelection--;
			currentSelection = (currentSelection + this.items.size) % this.items.size;
		}
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
		batch.dispose();

		background.dispose();
		for (MenuItem button : this.items) {
			button.dispose();
		}
	}

}

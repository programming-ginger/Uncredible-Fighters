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
import com.mygdx.game.menu.ButtonAction;
import com.mygdx.game.menu.MenuButton;

public class MenuScreen implements Screen {
	
	private UncredibleFighters game;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	
	private Texture backround;
	private MenuButton logo;
	
	Array<MenuButton> buttons;
	
	private int currentSelection;

	private final static float LOGO_SIZE = 0.2f;
	private final static float LOGO_Y = 0.95f;
	
	private final static float BUTTON_SIZE = 0.07f;
	private final static float SPIELEN_Y = 0.55f;
	private final static float EINSTELLUNGEN_Y = 0.40f;
	private final static float BEENDEN_Y = 0.25f;

	private Texture selectionArrow;

	public MenuScreen() {
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.getWindowWidth(), Options.getWindowHeight());
		this.viewport = new StretchViewport(Options.getWindowWidth(), Options.getWindowHeight(), camera);
		this.batch = new SpriteBatch();
		
		buttons = new Array<>();
		
		this.backround = new Texture("Background Menu.png");
		makeMenuButtons(buttons);
		
		this.currentSelection = 0;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		updateSelection();
		
		batch.begin();

		batch.draw(backround, 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
		for (MenuButton button : this.buttons){
			button.draw(batch);
		}
		logo.draw(batch);
		this.buttons.get(currentSelection).select(batch);
		
		batch.end();
		
		
	}
	
	private void updateSelection() {
		Vector3 touchPos3 = new Vector3();
		touchPos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos3);
		
		Vector2 touchPos = new Vector2(touchPos3.x, touchPos3.y);

		for (int i=0; i< this.buttons.size; i++){
			if (this.buttons.get(i).contains(touchPos.x, touchPos.y)) {
				currentSelection = i;
				i = this.buttons.size;
			}
		}

		if((Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			this.buttons.get(currentSelection).performAction();
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			currentSelection++;
			currentSelection = currentSelection % this.buttons.size;
		}
		
		else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			currentSelection--;
			currentSelection = (currentSelection + this.buttons.size) % this.buttons.size;
		}
	}
	
	private void makeMenuButtons(Array<MenuButton> buttons) {

		Texture texture;
		Rectangle rectangle;
		ButtonAction action;
		this.selectionArrow = new  Texture("Menu Selection Arrow.png");

		MenuButton button;
		
		// Logo
		texture = new Texture("Logo.PNG");
		float ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		float boxHeight = Options.getWindowWidth() * LOGO_SIZE;
		float boxWidth = Options.getWindowWidth() * LOGO_SIZE * ratio;
		float x = Options.getWindowWidth()/2f - boxWidth/2f;
		float y = Options.getWindowHeight()*LOGO_Y - boxHeight;
		
		rectangle = new Rectangle(x, y, boxWidth, boxHeight);
		button = new MenuButton(texture, rectangle, selectionArrow);
		this.logo = (button);
		
		// "Spielen" Knopf
		texture = new Texture("Spielen-Button.PNG");
		ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*SPIELEN_Y - boxHeight;

		rectangle = new Rectangle(x, y, boxWidth, boxHeight);

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showCharacterChoice();
			}
		};
		button = new MenuButton(texture, rectangle, action, selectionArrow);
		this.buttons.add(button);

		// "Einstellungen" Knopf
		texture = new Texture("Einstellungen-Button.PNG");
		ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*EINSTELLUNGEN_Y - boxHeight;
		
		rectangle = new Rectangle(x, y, boxWidth, boxHeight);

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.showSettings();
			}
		};
		button = new MenuButton(texture, rectangle, action, selectionArrow);
		this.buttons.add(button);
		
		// "Beenden" Knopf
		texture = new Texture("Beenden-Button.PNG");

		ratio = (texture.getWidth() + 0.0f) / texture.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*BEENDEN_Y - boxHeight;
		
		rectangle = new Rectangle(x, y, boxWidth, boxHeight);

		action = new ButtonAction() {
			@Override
			public void action() {
				UncredibleFighters.closeGame();
			}
		};
		button = new MenuButton(texture, rectangle, action, selectionArrow);
		this.buttons.add(button);
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
		
		backround.dispose();
		selectionArrow.dispose();
		for (MenuButton button:this.buttons){
			button.dispose();
		}
	}

}

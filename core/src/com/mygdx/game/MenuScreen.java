package com.mygdx.game;

import java.util.function.Supplier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.menu.MenuButton;

public class MenuScreen implements Screen {
	
	private UncredibleFighters game;
	
	private OrthographicCamera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	
	private Texture backround;
	private Texture logo;
	private Texture spielen;
	private Texture einstellungen;
	private Texture beenden;
	private Texture selectionArrow;
	
	private Rectangle logoBox;
	private Rectangle spielenButton;
	private Rectangle einstellungenButton;
	private Rectangle beendenButton;
	
	Array<MenuButton> buttons;
	
	private Rectangle currentSelection;

	private final static float LOGO_SIZE = 0.2f;
	private final static float LOGO_Y = 0.95f;
	
	private final static float BUTTON_SIZE = 0.07f;
	private final static float SPIELEN_Y = 0.55f;
	private final static float EINSTELLUNGEN_Y = 0.40f;
	private final static float BEENDEN_Y = 0.25f;
	
	private final static float ARROW_X_OFFSET = 0.05f;
	
	public MenuScreen() {
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Options.getWindowWidth(), Options.getWindowHeight());
		this.viewport = new StretchViewport(Options.getWindowWidth(), Options.getWindowHeight(), camera);
		this.batch = new SpriteBatch();
		
		buttons = new Array<MenuButton>();
		
		this.backround = new Texture("Backround Menu.png");
		this.logo = new Texture("Logo.png");
		makeMenuButtons(buttons);
		
		this.currentSelection = spielenButton;
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
		batch.draw(logo, logoBox.x, logoBox.y, logoBox.width, logoBox.height);
		batch.draw(spielen, spielenButton.x, spielenButton.y, spielenButton.width, spielenButton.height);
		batch.draw(einstellungen, einstellungenButton.x, einstellungenButton.y, einstellungenButton.width, einstellungenButton.height);
		batch.draw(beenden, beendenButton.x, beendenButton.y, beendenButton.width, beendenButton.height);
		
		drawArrow();
		
		batch.end();
		
		
	}
	
	private void updateSelection() {
		Vector3 touchPos3 = new Vector3();
		touchPos3.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(touchPos3);
		
		Vector2 touchPos = new Vector2(touchPos3.x, touchPos3.y);
		
		if (spielenButton.contains(touchPos.x, touchPos.y)) {
			currentSelection = spielenButton;
		}
		else if (einstellungenButton.contains(touchPos.x, touchPos.y)) {
			currentSelection = einstellungenButton;
		}
		else if (beendenButton.contains(touchPos.x, touchPos.y)) {
			currentSelection = beendenButton;
		}
		
		if(currentSelection == spielenButton && (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			UncredibleFighters.getInstance().setScreen(new CharacterChoiceScreen());
			return;
		}
		else if(currentSelection == einstellungenButton && (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			UncredibleFighters.getInstance().setScreen(new CharacterChoiceScreen());
			return;
		}
		else if(currentSelection == beendenButton && (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER) || Gdx.input.isKeyJustPressed(Input.Keys.E))) {
			// Spiel beenden
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			currentSelection = nextButton(currentSelection);
		}
		
		else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			currentSelection = previousButton(currentSelection);
		}
	}
	
	private Rectangle nextButton(Rectangle currentSelection) {
		if (currentSelection == spielenButton) return einstellungenButton;
		else if (currentSelection == einstellungenButton) return beendenButton;
		else if (currentSelection == beendenButton) return spielenButton;
		return null;
	}
	
	private Rectangle previousButton(Rectangle currentSelection) {
		if (currentSelection == spielenButton) return beendenButton;
		else if (currentSelection == einstellungenButton) return spielenButton;
		else if (currentSelection == beendenButton) return einstellungenButton;
		return null;
	}

	private void drawArrow() {
		
		float arrowWidth = currentSelection.height * (selectionArrow.getWidth()/selectionArrow.getHeight());
		batch.draw(selectionArrow, currentSelection.x - arrowWidth - ARROW_X_OFFSET * Options.getWindowHeight(), currentSelection.y, 
				 arrowWidth, currentSelection.height);
	}
	
	private void makeMenuButtons(Array<MenuButton> buttons) {
		
		this.spielen = new Texture("Spielen-Button.png");
		this.einstellungen = new Texture("Einstellungen-Button.png");
		this.beenden = new Texture("Beenden-Button.png");
		this.selectionArrow = new Texture("Menu Selection Arrow.png");
		
		Rectangle rectangle;
		
		// Logo
		float ratio = (logo.getWidth() + 0.0f) / logo.getHeight();
		float boxHeight = Options.getWindowWidth() * LOGO_SIZE;
		float boxWidth = Options.getWindowWidth() * LOGO_SIZE * ratio;
		float x = Options.getWindowWidth()/2f - boxWidth/2f;
		float y = Options.getWindowHeight()*LOGO_Y - boxHeight;
		
		this.logoBox = new Rectangle(x, y, boxWidth, boxHeight);
		
		// "Spielen" Knopf
		ratio = (spielen.getWidth() + 0.0f) / spielen.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*SPIELEN_Y - boxHeight;
		
		rectangle = new Rectangle(x, y, boxWidth, boxHeight);
		this.spielenButton = rectangle;
		buttons.add(new MenuButton(new Texture("Spielen-Button.png"), rectangle, null));
		
		// "Einstellungen" Knopf
		ratio = (einstellungen.getWidth() + 0.0f) / einstellungen.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*EINSTELLUNGEN_Y - boxHeight;
		
		rectangle = new Rectangle(x, y, boxWidth, boxHeight);
		this.einstellungenButton = rectangle;
		
		// "Beenden" Knopf
		ratio = (beenden.getWidth() + 0.0f) / beenden.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*BEENDEN_Y - boxHeight;
		
		rectangle = new Rectangle(x, y, boxWidth, boxHeight);
		this.beendenButton = rectangle;
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
		logo.dispose();
		spielen.dispose();
		einstellungen.dispose();
		beenden.dispose();
	}

}

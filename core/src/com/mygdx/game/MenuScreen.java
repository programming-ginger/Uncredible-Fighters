package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {
	
	private UncredibleFighters game;
	
	private Camera camera;
	private Viewport viewport;
	private SpriteBatch batch;
	
	private Texture backround;
	private Texture logo;
	private Texture spielen;
	private Texture einstellungen;
	private Texture beenden;
	
	private Rectangle logoBox;
	private Rectangle spielenButton;
	private Rectangle einstellungenButton;
	private Rectangle beendenButton;

	private final static float LOGO_SIZE = 0.2f;
	private final static float LOGO_Y = 0.95f;
	
	private final static float BUTTON_SIZE = 0.07f;
	private final static float SPIELEN_Y = 0.55f;
	private final static float EINSTELLUNGEN_Y = 0.40f;
	private final static float BEENDEN_Y = 0.25f;
	
	public MenuScreen() {
		this.camera = new OrthographicCamera();
		this.viewport = new StretchViewport(Options.getWindowWidth(), Options.getWindowHeight(), camera);
		this.batch = new SpriteBatch();
		
		this.backround = new Texture("Backround Menu.png");
		this.logo = new Texture("Logo.png");
		this.spielen = new Texture("Spielen-Button.png");
		this.einstellungen = new Texture("Einstellungen-Button.png");
		this.beenden = new Texture("Beenden-Button.png");
		
		makeMenuBoxes();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		if(Gdx.input.isTouched()) {
		      Vector2 touchPos = new Vector2();
		      touchPos.set(Gdx.input.getX(), Gdx.input.getY());
		      
		      if (spielenButton.contains(touchPos)) {
		    	  batch.begin();
		    	  batch.draw(backround, 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
			  	  batch.end();
		    	  UncredibleFighters.getInstance().setScreen(new CharacterChoiceScreen());
		    	  return;
		      }
		}
		batch.begin();
		batch.draw(backround, 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
		batch.draw(logo, logoBox.x, logoBox.y, logoBox.width, logoBox.height);
		batch.draw(spielen, spielenButton.x, spielenButton.y, spielenButton.width, spielenButton.height);
		batch.draw(einstellungen, einstellungenButton.x, einstellungenButton.y, einstellungenButton.width, einstellungenButton.height);
		batch.draw(beenden, beendenButton.x, beendenButton.y, beendenButton.width, beendenButton.height);
		batch.end();
		
		
	}
	
	private void makeMenuBoxes() {
		
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
		
		this.spielenButton = new Rectangle(x, y, boxWidth, boxHeight);
		
		// "Einstellungen" Knopf
		ratio = (einstellungen.getWidth() + 0.0f) / einstellungen.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*EINSTELLUNGEN_Y - boxHeight;
		
		this.einstellungenButton = new Rectangle(x, y, boxWidth, boxHeight);
		
		// "Beenden" Knopf
		ratio = (beenden.getWidth() + 0.0f) / beenden.getHeight();
		boxHeight = Options.getWindowWidth() * BUTTON_SIZE;
		boxWidth = Options.getWindowWidth() * BUTTON_SIZE * ratio;
		x = Options.getWindowWidth()/2f - boxWidth/2f;
		y = Options.getWindowHeight()*BEENDEN_Y - boxHeight;
		
		this.beendenButton = new Rectangle(x, y, boxWidth, boxHeight);
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

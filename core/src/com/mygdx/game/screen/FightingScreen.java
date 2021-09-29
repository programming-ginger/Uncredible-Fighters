package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Constants;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.prototype.FightingGame;
import com.mygdx.game.prototype.PrototypeCharMove;
import com.mygdx.game.scene.Hud;

public class FightingScreen implements Screen {

	private FightingGame game;
	private Camera gameCam;
	private Viewport viewport;
	private Hud hud;

	public final static float paddingTop = 30;
	public final static float paddingBottom = 0.1f * Options.getWindowHeight();
	public final static float paddingLeft = 0.05f * Options.getWindowWidth();
	public final static float paddingRight = 0.05f * Options.getWindowWidth();

	private UncredibleFighter charA;
	private UncredibleFighter charB;

	private float timeSeconds = 0f;

	private boolean menuIsActive;
	private MenuScreen menuOverlay;
	
	private Texture background;

	float tmp;

	private final int MOVE1_BUTTON_PLAYER_A = Input.Keys.R;
	private final int MOVE2_BUTTON_PLAYER_A = Input.Keys.F;

	private final int MOVE1_BUTTON_PLAYER_B = Input.Keys.O;
	private final int MOVE2_BUTTON_PLAYER_B = Input.Keys.L;

	public FightingScreen(FightingGame game) {
		this.game = game;
		gameCam = new OrthographicCamera();
		viewport = new FitViewport(Options.getWindowWidth(), Options.getWindowHeight(), gameCam);
		hud = new Hud(game.batch);
		charA = game.getCharacterA();
		charA.setPosition(paddingLeft, paddingBottom);
		charB = game.getCharacterB();
		charB.setPosition(Options.getWindowWidth() - paddingRight, paddingBottom);
		hud.updateName(charA.getName(), charB.getName());
		
		this.menuOverlay = new MenuScreen();
		
		if (Math.random() < 0.5) {
			background = charA.getSpecificBackground();
		}
		else {
			background = charB.getSpecificBackground();
		}
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			menuIsActive = !menuIsActive;

			if (menuIsActive) {
				showMenu();
			} 
		}

		if (!menuIsActive) {
			resetMoves();
			listenUserAInput();
			listenUserBInput();
			charA.update(delta, charB);
			charB.update(delta, charA);
//			moveUserA(delta);
//			moveUserB(delta);
		}

		game.batch.begin();
		game.batch.draw(background, 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
		charA.draw(game.batch);
		charB.draw(game.batch);
		game.batch.end();
		
		updateHud();
		
		if (menuIsActive) {
			menuOverlay.render(delta);
		}

	}

	@Override
	public void resize(int width, int height) {
//		Options.setWindowWidth(width);
//		Options.setWindowHeight(height);
//		viewport.update(Options.getWindowWidth(), Options.getWindowHeight());
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

	private void updateHud() {
		if (!menuIsActive) {
			updateTimer();
		}
		hud.updateData(game.getFightTime(), charA.getCurrentHP(), charB.getCurrentHP());
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

	}

	private void updateTimer() {
		timeSeconds += Gdx.graphics.getDeltaTime();
		if (timeSeconds > 1f) {
			timeSeconds -= 1f;
			game.decFightTime();
		}
	}

	private void resetMoves() {
		charA.moveX = 0;
		// charA.moveY = 0;
		charB.moveX = 0;
		// charB.moveY = 0;
	}

	private void listenUserAInput() {
		
		if (!charA.canMove()) {
			return;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			charA.jump();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			charA.moveLeft();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			charA.moveDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			charA.moveRight();
		}

		if (Gdx.input.isKeyPressed(MOVE1_BUTTON_PLAYER_A)) {
			charA.useMove1();
		}
		if (Gdx.input.isKeyPressed(MOVE2_BUTTON_PLAYER_A)) {
			charA.useMove2();
		}
	}

	private void listenUserBInput() {
		
		if (!charB.canMove()) {
			return;
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			charB.jump();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			charB.moveLeft();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			charB.moveDown();
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			charB.moveRight();
		}

		if (Gdx.input.isKeyPressed(MOVE1_BUTTON_PLAYER_B)) {
			charB.useMove1();
		}
		if (Gdx.input.isKeyPressed(MOVE2_BUTTON_PLAYER_B)) {
			charB.useMove2();
		}
	}

	public void closeMenu() {
		menuIsActive = false;
	}

	public void showSettings() {
		MenuFactory.turnIntoOptionsMenu(menuOverlay);
	}

	public void showMenu() {
		MenuFactory.turnIntoFightingPauseMenu(menuOverlay);

	}

	public Texture getBackground() {
		return background;
	}

	public UncredibleFighter getPlayer1() {
		return this.charA;
	}
	
	public UncredibleFighter getPlayer2() {
		return this.charB;
	}
}

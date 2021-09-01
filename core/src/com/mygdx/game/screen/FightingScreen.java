package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.character.UncredibleFighter;
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
	private SpriteBatch batch;

	private final float paddingTop = 30;
	private final float paddingBottom = 0.1f * Options.getWindowHeight();
	private final float paddingLeft = 0.05f * Options.getWindowWidth();
	private final float paddingRight = 0.05f * Options.getWindowWidth();

	private UncredibleFighter charA;
	private UncredibleFighter charB;
	private Rectangle rectA;
	private Rectangle rectB;

	private float timeSeconds = 0f;

	private boolean menuIsActive;
	private MenuScreen menuOverlay;

	float tmp;

	PrototypeCharMove fights;

	private final static float SPEED_FACTOR = 0.05f;
	private final static int GRAVITY = 28;

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
		rectA = game.getCharacterA().getRectangle();
		rectB = game.getCharacterB().getRectangle();
		batch = new SpriteBatch();
		hud.updateName(charA.getName(), charB.getName());
		this.menuOverlay = new MenuScreen();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			menuIsActive = !menuIsActive;

			if (menuIsActive) {
				showMenu();
			} else {
				menuOverlay = null;
			}
		}

		updateHud();

		if (!menuIsActive) {
			resetMoves();
			listenUserAInput();
			listenUserBInput();
			moveUserA(delta);
			moveUserB(delta);
		}

		game.batch.begin();
		charA.draw(game.batch);
		charB.draw(game.batch);
		game.batch.end();

		if (menuIsActive) {
			menuOverlay.render(delta);
		}

	}

	@Override
	public void resize(int width, int height) {
		Options.setWindowWidth(width);
		Options.setWindowHeight(height);
		viewport.update(Options.getWindowWidth(), Options.getWindowHeight());
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
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (charA.moveY == 0 && !charA.jumping && !charA.falling) {
				charA.jumping = true;
				charA.moveY = charA.jumpSpeed;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			charA.lookLeft();
			if ((rectA.x - charA.moveX) >= paddingLeft) {
				charA.moveX = -1 * charA.getSpeed();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (charA.jumping) {
				charA.jumping = false;
				charA.falling = true;
			}
			if (charA.falling)
				charA.moveY -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			charA.lookRight();
			if ((rectA.x + charA.moveX) < viewport.getScreenWidth() - paddingRight * 2) {
				charA.moveX = 1 * charA.getSpeed();
			}
		}

		if (Gdx.input.isKeyPressed(MOVE1_BUTTON_PLAYER_A)) {

			charA.useMove1();
		}
	}

	private void listenUserBInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {
			if (charB.moveY == 0 && !charB.jumping && !charB.falling) {
				charB.jumping = true;
				charB.moveY = charB.jumpSpeed;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) {
			charB.lookLeft();
			if ((rectB.x - charB.moveX) >= paddingLeft) {
				charB.moveX = -1 * charB.getSpeed();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)) {
			if (charB.jumping) {
				charB.jumping = false;
				charB.falling = true;
			}
			if (charB.falling)
				charB.moveY -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) {
			charB.lookRight();
			if ((rectB.x + charB.moveX) < viewport.getScreenWidth() - paddingRight * 2) {
				charB.moveX = 1 * charB.getSpeed();
			}
		}

		if (Gdx.input.isKeyPressed(MOVE1_BUTTON_PLAYER_B)) {
			charB.useMove1();
		}
	}

	private void moveUserA(float delta) {
		if (charA.jumping) {
			charA.moveY -= GRAVITY * delta;
			if (charA.moveY <= 0) {
				charA.moveY = 0;
				charA.falling = true;
				charA.jumping = false;
			}
		} else if (charA.falling) {
			charA.moveY -= GRAVITY * delta;
			if (rectA.y + charA.moveY < paddingBottom + (rectA.height / 2)) {
				charA.falling = false;
			}
		} else {
			charA.moveY = 0;
		}

		float oldX = rectA.x;
		float oldY = rectA.y;

		tmp = rectA.x;
		rectA.x += charA.moveX * delta * Options.getWindowWidth() * SPEED_FACTOR;
		if (rectA.overlaps(rectB))
			rectA.x = tmp;

		tmp = rectA.y;
		rectA.y = Math.max(rectA.y + charA.moveY * delta * Options.getWindowHeight() * SPEED_FACTOR,
				paddingBottom + (rectA.height / 2));
		if (rectA.overlaps(rectB))
			rectA.y = tmp;

		charA.update(delta, charB);
	}

	private void moveUserB(float delta) {
		if (charB.jumping) {
			charB.moveY -= GRAVITY * delta;
			if (charB.moveY <= 0) {
				charB.moveY = 0;
				charB.falling = true;
				charB.jumping = false;
			}
		} else if (charB.falling) {
			charB.moveY -= GRAVITY * delta;
			if (rectB.y + charB.moveY < paddingBottom + (rectB.height / 2)) {
				charB.falling = false;
			}
		} else {
			charB.moveY = 0;
		}

		tmp = rectB.x;
		rectB.x += charB.moveX * delta * Options.getWindowWidth() * SPEED_FACTOR;
		if (rectB.overlaps(rectA))
			rectB.x = tmp;

		tmp = rectB.y;
		rectB.y = Math.max(rectB.y + charB.moveY * delta * Options.getWindowHeight() * SPEED_FACTOR,
				paddingBottom + (rectB.height / 2));
		if (rectB.overlaps(rectA))
			rectB.y = tmp;

		charB.update(delta, charA);
	}

	public void closeMenu() {
		menuOverlay = null;
		menuIsActive = false;
	}

	public void showSettings() {
		MenuFactory.turnIntoOptionsMenu(menuOverlay);
	}

	public void showMenu() {
		MenuFactory.turnIntoFightingPauseMenu(menuOverlay);

	}
}

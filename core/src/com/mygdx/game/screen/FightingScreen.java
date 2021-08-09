package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.character.Character;
import com.mygdx.game.data.Options;
import com.mygdx.game.prototype.FightingGame;
import com.mygdx.game.prototype.PrototypeCharMove;
import com.mygdx.game.scene.Hud;

public class FightingScreen implements Screen {

	private FightingGame game;
	private Camera gameCam;
	private Viewport viewport;
	private Hud hud;

	private final float paddingTop = 30;
	private final float paddingBottom = 30;
	private final float paddingLeft = 50;
	private final float paddingRight = 50;

	private Character charA;
	private Character charB;
	private ShapeRenderer srA;
	private ShapeRenderer srB;
	private Rectangle rectA;
	private Rectangle rectB;

	private float timeSeconds = 0f;
	
	PrototypeCharMove fights;

	public FightingScreen(FightingGame game)
	{
		this.game = game;
		gameCam = new OrthographicCamera();
		viewport = new FitViewport(Options.getWindowWidth(), Options.getWindowHeight(), gameCam);
		hud = new Hud(game.batch);
		charA = game.getCharacterA();
		charB = game.getCharacterB();
		rectA = game.getCharacterA().getRectangle();
		rectB = game.getCharacterB().getRectangle();
		srA = new ShapeRenderer();
		srB = new ShapeRenderer();
		hud.updateName(charA.getName(), charB.getName());
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateHud();
		resetMoves();
		listenUserAInput();
		listenUserBInput();
		moveUserA();
		moveUserB();
		renderUserA();
		renderUserB();

		game.batch.begin();
		game.batch.end();

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
	public void dispose()
	{

	}

	private void updateHud()
	{
		updateTimer();
		hud.updateData(game.getFightTime(), charA.getCurrentHP(), charB.getCurrentHP());
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

	}

	private void updateTimer()
	{
		timeSeconds += Gdx.graphics.getDeltaTime();
		if(timeSeconds > 1f){
			timeSeconds-=1f;
			game.decFightTime();
		}
	}

	private void resetMoves()
	{
		charA.moveX = 0;
		//charA.moveY = 0;
		charB.moveX = 0;
		//charB.moveY = 0;
	}

	private void listenUserAInput()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.W))
		{
			if (charA.moveY == 0 && !charA.jumping && !charA.falling)
			{
				charA.jumping = true;
				charA.moveY = charA.jumpSpeed;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A))
		{
			if ((rectA.x - charA.moveX) >= paddingLeft)
			{
				charA.moveX = -1 * charA.getSpeed();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S))
		{
			if (charA.jumping)
			{
				charA.jumping = false;
				charA.falling = true;
			}
			if (charA.falling)
				charA.moveY -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D))
		{
			if ((rectA.x + charA.moveX) < viewport.getScreenWidth() - paddingRight * 2)
			{
				charA.moveX = 1 * charA.getSpeed();
			}
		}
	}

	private void listenUserBInput()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
		{
			if (charB.moveY == 0 && !charB.jumping && !charB.falling)
			{
				charB.jumping = true;
				charB.moveY = charB.jumpSpeed;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
		{
			if ((rectB.x - charB.moveX) >= paddingLeft)
			{
				charB.moveX = -1 * charB.getSpeed();
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
		{
			if (charB.jumping)
			{
				charB.jumping = false;
				charB.falling = true;
			}
			if (charB.falling)
				charB.moveY -= 0.5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
		{
			if ((rectB.x + charB.moveX) < viewport.getScreenWidth() - paddingRight * 2)
			{
				charB.moveX = 1 * charB.getSpeed();
			}
		}
	}

	private void moveUserA()
	{
		if (charA.jumping)
		{
			charA.moveY -= 0.2;
			if (charA.moveY <= 0)
			{
				charA.moveY = 0;
				charA.falling = true;
				charA.jumping = false;
			}
		}
		else if (charA.falling)
		{
			charA.moveY -= 0.2;
			if (rectA.y + charA.moveY < paddingBottom + (rectA.height/2))
			{
				charA.falling = false;
			}
		}
		else
		{
			charA.moveY = 0;
		}

		rectA.x += charA.moveX;
		rectA.y = Math.max(rectA.y + charA.moveY, paddingBottom + (rectA.height/2));
	}

	private void moveUserB()
	{
		if (charB.jumping)
		{
			charB.moveY -= 0.2;
			if (charB.moveY <= 0)
			{
				charB.moveY = 0;
				charB.falling = true;
				charB.jumping = false;
			}
		}
		else if (charB.falling)
		{
			charB.moveY -= 0.2;
			if (rectB.y + charB.moveY < paddingBottom + (rectB.height/2))
			{
				charB.falling = false;
			}
		}
		else
		{
			charB.moveY = 0;
		}

		rectB.x += charB.moveX;
		rectB.y = Math.max(rectB.y + charB.moveY, paddingBottom + (rectB.height/2));
	}

	private void renderUserA()
	{
		srA.begin(ShapeRenderer.ShapeType.Filled);
		srA.setColor(1, 0, 0 , 1);
		srA.rect(rectA.x, rectA.y, rectA.width, rectA.height);
		srA.end();
	}

	private void renderUserB()
	{
		srB.begin(ShapeRenderer.ShapeType.Filled);
		srB.setColor(0, 1, 0 , 1);
		srB.rect(rectB.x, rectB.y, rectB.width, rectB.height);
		srB.end();
	}
}

package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.data.Options;
import com.mygdx.game.prototype.FightingGame;
import com.mygdx.game.prototype.PrototypeCharMove;
import com.mygdx.game.scene.Hud;

public class FightingScreen implements Screen {

	private FightingGame game;
	private Camera gameCam;
	private Viewport viewport;
	private Hud hud;

	private float timeSeconds = 0f;
	
	PrototypeCharMove fights;

	public FightingScreen(FightingGame game)
	{
		this.game = game;
		gameCam = new OrthographicCamera();
		viewport = new FitViewport(Options.getWindowWidth(), Options.getWindowHeight(), gameCam);
		hud = new Hud(game.batch);
		hud.updateName(game.getCharAName(), game.getCharBName());
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateTimer();
		hud.updateData(game.getFightTime(), game.getCharAHp(), game.getCharBHp());
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

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

	private void updateTimer()
	{
		timeSeconds += Gdx.graphics.getDeltaTime();
		if(timeSeconds > 1f){
			timeSeconds-=1f;
			game.decFightTime();
		}
	}
}

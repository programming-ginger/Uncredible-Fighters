package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.UncredibleFighters;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.MenuFactory;
import com.mygdx.game.menu.PassiveTexture;
import com.mygdx.game.sound.MusicPlayer;

public class WinnerScreen implements Screen{
	
	private Texture background;
	private PassiveTexture winnerPortrait;
	private PassiveTexture winnerLabel;
	
	private UncredibleFighter winner;
	private UncredibleFighter loser;
	private SpriteBatch batch;
	
	private Camera gameCam;
	private Viewport viewport;
	
	private boolean loserIsFalling = true;
	
	private static final float PORTRAIT_Y = 0.7f;
	private static final float PORTRAIT_SIZE = 0.4f;
	
	private static final float LABEL_Y = 0.4f;
	private static final float LABEL_SIZE = 0.2f;

	public WinnerScreen(Texture background, UncredibleFighter player1, UncredibleFighter player2) {
		
		gameCam = new OrthographicCamera();
		viewport = new FitViewport(Options.getWindowWidth(), Options.getWindowHeight(), gameCam);
		batch = new SpriteBatch();
		
		if (player1.getCurrentHP() > player2.getCurrentHP()) {
			this.winner = player1;
			this.loser = player2;
		}
		else {
			this.winner = player2;
			this.loser = player1;
		}
				
		this.background = background;
		
		winnerPortrait = MenuFactory.makePassiveTexture(winner.getPortrait(), Options.getWindowWidth()/2, PORTRAIT_Y * Options.getWindowHeight(), Options.getWindowHeight() * PORTRAIT_SIZE);
		winnerLabel = MenuFactory.makePassiveTexture(new Texture("WinnerLabel.png"), Options.getWindowWidth()/2, LABEL_Y * Options.getWindowHeight(), Options.getWindowHeight() * LABEL_SIZE);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
		if (loserIsFalling) {
			loserIsFalling = loser.fallToTheGround(delta);
		}
		
		batch.begin();
		batch.draw(background, 0, 0, Options.getWindowWidth(), Options.getWindowHeight());
		winner.draw(batch);
		loser.draw(batch);
		winnerPortrait.draw(batch);
		winnerLabel.draw(batch);
		batch.end();		
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
		// TODO Auto-generated method stub
		
	}
}

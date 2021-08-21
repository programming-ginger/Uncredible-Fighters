package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.UncredibleFighters;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.menu.CharacterPortrait;
import com.mygdx.game.menu.MenuItem;
import com.mygdx.game.menu.PassiveTexture;
import com.mygdx.game.prototype.FightingGame;
import com.mygdx.game.sound.SoundPlayer;

public class CharacterChoiceScreen extends MenuScreen {
	
	private MenuItem player2Selection;
	
	private boolean player1Confirmed;
	private boolean player2Confirmed;
	
	public CharacterChoiceScreen() {
		player1Confirmed = false;
		player2Confirmed = false;
	}
	
	@Override
	public void addMenuItem(MenuItem item) {
		this.items.add(item);
		
		if (this.items.size == 1) {
			this.currentSelection = this.items.get(0);
			this.player2Selection = this.items.get(0);
		}
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
			button.draw(batch, button == currentSelection, button == player2Selection);
		}

		for (PassiveTexture textures: this.passiveTextures) {
			textures.draw(batch);
		}

		batch.end();
	}
	
	@Override
	protected void updateSelection() {
		MenuItem newSelection = null;
		
		//Selection Player 1
		if (!player1Confirmed) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
				newSelection = currentSelection.getItemBelow();			
			}
	
			else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
				newSelection = currentSelection.getItemAbove();
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
				newSelection = currentSelection.getItemLeft();
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
				newSelection = currentSelection.getItemRight();
			}
			
			if (newSelection != null && newSelection != currentSelection) {
				SoundPlayer.playSelectionSound();
				currentSelection = newSelection;
			}
			
			newSelection = null;
		}
		
		//Selection Player 2
		if (!player2Confirmed) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
				newSelection = player2Selection.getItemBelow();			
			}
	
			else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				newSelection = player2Selection.getItemAbove();
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
				newSelection = player2Selection.getItemLeft();
			}
			else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
				newSelection = player2Selection.getItemRight();
			}
			
			if (newSelection != null && newSelection != player2Selection) {
				SoundPlayer.playSelectionSound();
				player2Selection = newSelection;
			}
		}
		
		Vector2 touchPos = getMousePosition();
		
		// Check if someone picked their character
		if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
			if (!(currentSelection instanceof CharacterPortrait)) {
				currentSelection.performAction();
			}
			else {
				if (! (currentSelection == player2Selection && player2Confirmed)) {
					SoundPlayer.playActionSound();
					player1Confirmed = !player1Confirmed;
				}
			}
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			if (!(player2Selection instanceof CharacterPortrait)) {
				player2Selection.performAction();
			}
			else {			
				if (! (currentSelection == player2Selection && player1Confirmed)) {
					SoundPlayer.playActionSound();
					player2Confirmed = !player2Confirmed;
				}
			}
		}
		
		if (player1Confirmed && player2Confirmed) {
			UncredibleFighter player1 = ((CharacterPortrait) currentSelection).getFighter();
			UncredibleFighter player2 = ((CharacterPortrait) player2Selection).getFighter();
			UncredibleFighters.startFight(player1, player2);
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
		// TODO Auto-generated method stub

	}

}

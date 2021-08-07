package com.mygdx.game.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MenuScreen;
import com.mygdx.game.Options;
import com.mygdx.game.UncredibleFighters;

public class MenuFactory {

    public static MenuScreen createMainMenu(){
    	
    	 final float BUTTON_SIZE = 0.07f;
    	 final float SPIELEN_Y = 0.55f;
    	 final float EINSTELLUNGEN_Y = 0.40f;
    	 final float BEENDEN_Y = 0.25f;
    	
        MenuScreen mainMenu = new MenuScreen();
        mainMenu.setBackground("Background Menu.png");
        
        
        Texture texture;
		Rectangle rectangle;
		ButtonAction action;
		Texture selectionArrow = new Texture("Menu Selection Arrow.png");
		
		float boxHeight;
		float boxWidth;
		float ratio;
		float x;
		float y;

		MenuButton button;
		
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
		mainMenu.addMenuItem(button);

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
		mainMenu.addMenuItem(button);
		
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
		mainMenu.addMenuItem(button);
        
        
        return mainMenu;
    }
}

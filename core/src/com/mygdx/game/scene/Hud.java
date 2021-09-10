package com.mygdx.game.scene;

import org.omg.CORBA.FREE_MEM;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.data.Options;

public class Hud
{
    public Stage stage;
    private Viewport viewport;

    Label countdownLabel;
    Label charNameALabel;
    Label charNameBLabel;
    Label timeLabel;
    Label hpALabel;
    Label hpBLabel;
    ProgressBar hpAProgressBar;
    ProgressBar hpBProgressbar;
    BitmapFont font;
    
    		
    		
    public Hud(SpriteBatch sb)
    {
        viewport = new FitViewport(Options.getWindowWidth(), Options.getWindowHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        font = new BitmapFont();
        font.getData().setScale(2.0f);
        
        countdownLabel = new Label(("ZEIT"), new Label.LabelStyle(font, Color.valueOf("FFE033")));
        charNameALabel = new Label(("Player 1"), new Label.LabelStyle(font, Color.valueOf("FFE033")));
        charNameBLabel = new Label(("Player 2"), new Label.LabelStyle(font, Color.valueOf("FFE033")));
        timeLabel = new Label(String.format("%03d", 300), new Label.LabelStyle(font, Color.valueOf("FFE033")));
        hpALabel = new Label(String.format("%03d", 100), new Label.LabelStyle(font, Color.valueOf("FFE033")));
        hpBLabel = new Label(String.format("%03d", 100), new Label.LabelStyle(font, Color.valueOf("FFE033")));

        table.add(charNameALabel).expandX().padTop(10).padLeft(10).align(Align.left);
        table.add(countdownLabel).expandX().padTop(10);
        table.add(charNameBLabel).expandX().padTop(10).padRight(10).align(Align.right);
        table.row();
        table.add(hpALabel).expandX().padLeft(10).align(Align.left);
        table.add(timeLabel).expandX();
        table.add(hpBLabel).expandX().padRight(10).align(Align.right);

        stage.addActor(table);
    }

    public void updateData(int fightTimer, int charAHp, int charBHp)
    {
        timeLabel.setText(String.format("%03d", fightTimer));
        hpALabel.setText(String.format("%03d", charAHp));
        hpBLabel.setText(String.format("%03d", charBHp));
    }

    public void updateName(String charAName, String charBName)
    {
        charNameALabel.setText(charAName);
        charNameBLabel.setText(charBName);
    }
}

package com.mygdx.game.prototype;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.character.Child;
import com.mygdx.game.character.Maid;
import com.mygdx.game.data.Options;
import com.mygdx.game.screen.FightingScreen;

public class FightingGame extends Game {

    ShapeRenderer shapeRendererRectA;
    ShapeRenderer shapeRendererRectB;

    public SpriteBatch batch = new SpriteBatch();
    private UncredibleFighter characterA;
    private UncredibleFighter characterB;

    private int fightTime;

    public FightingGame(UncredibleFighter characterA, UncredibleFighter characterB)
    {
        this.characterA = characterA;
        this.characterB = characterB;
        this.fightTime = Options.getFightTime();
    }

    @Override
    public void create ()
    {
        shapeRendererRectA = new ShapeRenderer();
        shapeRendererRectB = new ShapeRenderer();
    }

    @Override
    public void render ()
    {

    }

    @Override
    public void dispose ()
    {

    }

    public UncredibleFighter getCharacterA()
    {
        return characterA;
    }

    public UncredibleFighter getCharacterB()
    {
        return characterB;
    }

    public int getFightTime()
    {
        return fightTime;
    }

    public void decFightTime()
    {
        fightTime--;
    }

}

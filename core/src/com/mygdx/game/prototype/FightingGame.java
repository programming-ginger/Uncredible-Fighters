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

    private final float defSpeed = 2;
    private final float jumpStartSpeed = 8;

    private final float paddingTop = 10;
    private final float paddingBottom = 30;
    private final float paddingLeft = 10;
    private final float paddingRight = 10;

    private final float rectWidth = 50;
    private final float rectHeight = 50;

    float rectXA = (rectWidth/2) + paddingLeft;
    float rectYA = (rectHeight/2) + paddingBottom;
    float rectXB = (rectWidth/2) + paddingLeft;
    float rectYB = (rectHeight/2) + paddingBottom;

    float speedXA = 0;
    float speedYA = 0;
    float speedXB = 0;
    float speedYB = 0;

    boolean jumpingA = false;
    boolean fallingA = false;
    boolean jumpingB = false;
    boolean fallingB = false;

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

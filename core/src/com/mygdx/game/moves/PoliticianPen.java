package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.Politician;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.sound.SoundPlayer;

public class PoliticianPen extends Move{

    private final static int MOVE_DAMAGE = 5;
    private final static float ATTACK_RANGE = 0.5f;
    private final static float ATTACK_HEIGHT = 0.75f;

    public PoliticianPen(){
        Array<Texture> texturesBeforeEffect = new Array<>();
        texturesBeforeEffect.add(new Texture("Politician/PoliticianFightingSprite.png"));
        texturesBeforeEffect.add(new Texture("Politician/PoliticianShootingSprite1 - 1.png"));

        Array<Texture> texturesAfterEffect = new Array<>();
        texturesAfterEffect.add(new Texture("Politician/PoliticianShootingSprite2.png"));
        texturesAfterEffect.add(new Texture("Politician/PoliticianFightingSprite.png"));

        setTexturesBeforeEffect(texturesBeforeEffect);
        setTexturesAfterEffect(texturesAfterEffect);

        setTimeBeforeEffect(1.5f);
        setTimeAfterEffect(1);
    }

    @Override
    public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
        ((Politician) self).addPen();
    }

    @Override
    protected boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy) {
        return true;
    }
}

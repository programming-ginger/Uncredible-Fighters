package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.Child;
import com.mygdx.game.character.Grandpa;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.sound.SoundPlayer;

public class GrandpaPills extends Move{

    private final static int MOVE_DAMAGE = 5;
    private final static float ATTACK_RANGE = 0.5f;
    private final static float ATTACK_HEIGHT = 0.75f;

    public GrandpaPills(){
        Array<Texture> texturesBeforeEffect = new Array<>();

        texturesBeforeEffect.add(new Texture("Grandpa/GrandpaFightingSprite.png"));

        Array<Texture> texturesAfterEffect = new Array<>();
        texturesAfterEffect.add(new Texture("Grandpa/GrandpaBuffSprite.png"));


        setTexturesBeforeEffect(texturesBeforeEffect);
        setTexturesAfterEffect(texturesAfterEffect);

        setTimeBeforeEffect(1.5f);
        setTimeAfterEffect(1);
    }

    @Override
    public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
        self.changeSpeedTemporarily(1, 7);
    }

    @Override
    protected boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy) {
        return true;
    }


}
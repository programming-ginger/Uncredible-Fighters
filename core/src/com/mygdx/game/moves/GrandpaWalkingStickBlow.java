package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.UncredibleFighter;

public class GrandpaWalkingStickBlow extends Move{

    public GrandpaWalkingStickBlow(){
        Array<Texture> texturesBeforeEffect = new Array<>();
        texturesBeforeEffect.add(new Texture("Grandpa/GrandpaFightingSprite.png"));
        texturesBeforeEffect.add(new Texture("Grandpa/Grandpa-Move1 - 1.png"));

        Array<Texture> texturesAfterEffect = new Array<>();
        texturesAfterEffect.add(new Texture("Grandpa/Grandpa-Move1 - 2.png"));
        texturesAfterEffect.add(new Texture("Grandpa/Grandpa-Move1 - 3.png"));
        texturesAfterEffect.add(new Texture("Grandpa/GrandpaFightingSprite.png"));

        setTexturesBeforeEffect(texturesBeforeEffect);
        setTexturesAfterEffect(texturesAfterEffect);

        setTimeBeforeEffect(1.5f);
        setTimeAfterEffect(1);
    }

    @Override
    public void applyEffect(UncredibleFighter enemy) {

    }
}

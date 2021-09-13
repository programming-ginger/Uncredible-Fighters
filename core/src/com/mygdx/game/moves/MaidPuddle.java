package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.sound.SoundPlayer;

public class MaidPuddle extends Move{

    private final static int MOVE_DAMAGE = 5;
    private final static float ATTACK_RANGE = 0.5f;
    private final static float ATTACK_HEIGHT = 0.75f;

    public MaidPuddle(){
        Array<Texture> texturesBeforeEffect = new Array<>();
        texturesBeforeEffect.add(new Texture("Maid/MaidFightingSprite.png"));
        texturesBeforeEffect.add(new Texture("Maid/Maid-Move1 - 1.png"));

        Array<Texture> texturesAfterEffect = new Array<>();
        texturesAfterEffect.add(new Texture("Maid/Maid-Move1 - 2.png"));
        texturesAfterEffect.add(new Texture("Maid/Maid-Move1 - 3.png"));
        texturesAfterEffect.add(new Texture("Maid/MaidFightingSprite.png"));

        setTexturesBeforeEffect(texturesBeforeEffect);
        setTexturesAfterEffect(texturesAfterEffect);

        setTimeBeforeEffect(1.5f);
        setTimeAfterEffect(1);
    }

    @Override
    public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
        enemy.reduceHP(MOVE_DAMAGE);
        SoundPlayer.playHitSound();
    }

    @Override
    protected boolean moveHits(UncredibleFighter attacker, UncredibleFighter enemy) {
        float hitPointX;
        float hitPointY;

        Rectangle ownPosition = attacker.getRectangle();
        Rectangle enemyHitbox = enemy.getRectangle();

        if (attacker.looksLeft()) {
            hitPointX = ownPosition.getX() - ownPosition.getWidth() * ATTACK_RANGE;
        }
        else {
            hitPointX = ownPosition.getX() + ownPosition.getWidth() + ownPosition.getWidth() * ATTACK_RANGE;
        }

        hitPointY = ownPosition.getY() + ownPosition.getHeight() * ATTACK_HEIGHT;

        return enemyHitbox.contains(hitPointX, hitPointY);
    }


}

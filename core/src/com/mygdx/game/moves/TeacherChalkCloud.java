package com.mygdx.game.moves;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.Child;
import com.mygdx.game.character.UncredibleFighter;
import com.mygdx.game.data.Options;
import com.mygdx.game.sound.SoundPlayer;

public class TeacherChalkCloud extends Move{

    private final static float DURATION = 2f;
    private final static float ATTACK_RANGE = 1.5f;
    private final static float ATTACK_HEIGHT = 0.65f;

    public TeacherChalkCloud(){
        Array<Texture> texturesBeforeEffect = new Array<>();

        texturesBeforeEffect.add(new Texture("Teacher/TeacherCloudSprite1.png"));

        Array<Texture> texturesAfterEffect = new Array<>();
        texturesAfterEffect.add(new Texture("Teacher/TeacherCloudSprite2.png"));

        setTexturesBeforeEffect(texturesBeforeEffect);
        setTexturesAfterEffect(texturesAfterEffect);

        setTimeBeforeEffect(1.5f);
        setTimeAfterEffect(1);
    }

    @Override
    public void applyEffect(UncredibleFighter self, UncredibleFighter enemy) {
        enemy.stun(DURATION);
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
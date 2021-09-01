package com.mygdx.game.moves;

import com.badlogic.gdx.scenes.scene2d.Action;

public class CountDownAction extends Action {
    private int count;

    @Override
    public boolean act(float delta) {
        --count;
        return true;
    }

    public void setCount(int value) { count = value; }

    public boolean isComplete() { return count == 0; }
}

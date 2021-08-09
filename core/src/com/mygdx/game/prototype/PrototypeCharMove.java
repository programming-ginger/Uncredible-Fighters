package com.mygdx.game.prototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PrototypeCharMove implements Screen {
    ShapeRenderer rectA;
    ShapeRenderer rectB;

    private final float defSpeed = 2;
    private final float jumpStartSpeed = 8;

    private final float vpPaddingTop = 10;
    private final float vpPaddingBottom = 30;
    private final float vpPaddingLeft = 10;
    private final float vpPaddingRight = 10;

    private final float rectWidth = 50;
    private final float rectHeight = 50;

    float rectXA = (rectWidth/2) + vpPaddingLeft;
    float rectYA = (rectHeight/2) + vpPaddingBottom;
    float rectXB = (rectWidth/2) + vpPaddingLeft;
    float rectYB = (rectHeight/2) + vpPaddingBottom;

    float speedXA = 0;
    float speedYA = 0;
    float speedXB = 0;
    float speedYB = 0;

    boolean jumpingA = false;
    boolean fallingA = false;
    boolean jumpingB = false;
    boolean fallingB = false;

    public PrototypeCharMove ()
    {
        rectA = new ShapeRenderer();
        rectB = new ShapeRenderer();
    }

    @Override
    public void render (float delta) {
        speedXA = 0;
        //speedYA = 0;
        speedXB = 0;
        //speedYB = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            if (speedYA == 0 && !jumpingA && !fallingA)
            {
                jumpingA = true;
                speedYA = jumpStartSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if ((rectXA - speedXA) >= vpPaddingLeft + (rectWidth/2))
            {
                speedXA = -1 * defSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            if (jumpingA)
            {
                jumpingA = false;
                fallingA = true;
            }
            if (fallingA)
                speedYA -= 0.5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if ((rectXA + speedXA) < Gdx.graphics.getWidth() - vpPaddingRight - rectWidth*1.5)
            {
                speedXA = 1 * defSpeed;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_UP))
        {
            if (speedYB == 0 && !jumpingB && !fallingB)
            {
                jumpingB = true;
                speedYB = jumpStartSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
        {
            if ((rectXB - speedXB) >= vpPaddingLeft + (rectWidth/2))
            {
                speedXB = -1 * defSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN))
        {
            if (jumpingB)
            {
                jumpingB = false;
                fallingB = true;
            }
            if (fallingB)
                speedYB -= 0.5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
        {
            if ((rectXB + speedXB) < Gdx.graphics.getWidth() - vpPaddingRight - rectWidth*1.5)
            {
                speedXB = 1 * defSpeed;
            }
        }

        if (jumpingA)
        {
            speedYA -= 0.2;
            if (speedYA <= 0)
            {
                speedYA = 0;
                fallingA = true;
                jumpingA = false;
            }
        }
        else if (fallingA)
        {
            speedYA -= 0.2;
            if (rectYA + speedYA < vpPaddingBottom + (rectHeight/2))
            {
                fallingA = false;
            }
        }
        else
        {
            speedYA = 0;
        }

        if (jumpingB)
        {
            speedYB -= 0.2;
            if (speedYB <= 0)
            {
                speedYB = 0;
                fallingB = true;
                jumpingB = false;
            }
        }
        else if (fallingB)
        {
            speedYB -= 0.2;
            if (rectYB + speedYB < vpPaddingBottom + (rectHeight/2))
            {
                fallingB = false;
            }
        }
        else
        {
            speedYB = 0;
        }

        rectXA += speedXA;
        rectYA = Math.max(rectYA + speedYA, vpPaddingBottom + (rectHeight/2));
        rectXB += speedXB;
        rectYB = Math.max(rectYB + speedYB, vpPaddingBottom + (rectHeight/2));

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        rectA.begin(ShapeRenderer.ShapeType.Filled);
        rectA.setColor(1, 0, 0 , 1);
        rectA.rect(rectXA, rectYA, rectWidth, rectHeight);
        rectA.end();

        rectB.begin(ShapeRenderer.ShapeType.Filled);
        rectB.setColor(0, 1, 0 , 1);
        rectB.rect(rectXB, rectYB, rectWidth, rectHeight);
        rectB.end();
    }

    @Override
    public void dispose () {
        rectA.dispose();
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}

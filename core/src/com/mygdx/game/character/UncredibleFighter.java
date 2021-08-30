package com.mygdx.game.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class UncredibleFighter
{
    private String name;
    private int maxHP;
    private int currentHP;
    private float speed;
    protected Texture texture;
    protected Rectangle rectangle;
    protected boolean lookingLeft = false;
    public boolean jumping = false;
    public boolean falling = false;
    public float moveX = 0;
    public float moveY = 0;
    public final float jumpSpeed = 8;

    public void lookLeft(){
        lookingLeft = true;
    }

    public void lookRight(){
        lookingLeft = false;
    }

    public void draw (SpriteBatch batch){
        batch.draw(texture, rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), 0, 0, texture.getWidth(), texture.getHeight(), lookingLeft, false);
    }

    public void setPosition(float x, float y){
        rectangle.x = x - rectangle.getWidth()/2;
        rectangle.y = y - rectangle.getHeight()/2;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    public void setMaxHP(int maxHP)
    {
        this.maxHP = maxHP;
        this.setCurrentHP(maxHP);
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    public void setCurrentHP(int currentHP)
    {
        this.currentHP = currentHP;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}

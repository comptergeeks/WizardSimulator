package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Wizard {
    int x;
    int y;
    TextureRegion[] region;
    SpriteBatch batch;

    public Wizard(TextureRegion[] region, SpriteBatch batch) {
        this.region = region;
        this.batch = batch;
    }
    public void render() {
        update();
        batch.draw(region[3], x, y);
    }
    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 10;
        }
    }

}

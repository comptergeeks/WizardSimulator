package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Wizard {
    int x;
    int y;
    int speed = 5;
    int vertical = 0;
    int horizontal = 0;
    TextureRegion[] region;
    SpriteBatch batch;

    public Wizard(TextureRegion[] region, SpriteBatch batch) {
        this.region = region;
        this.batch = batch;
    }
    public void render() {
        update();

    }
    public void update() {

        Vector2 move = new Vector2(vertical, horizontal);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            move.y = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            move.y = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            move.x = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            move.x = -1;
        }
        move = normalize(move);
        batch.draw(region[3], x+= move.x * speed , y+= move.y * speed);
    }
    public Vector2 normalize(Vector2 move) {
        double mag = Math.sqrt(move.x*move.x + move.y*move.y);
        if(mag > 0) {
            move.x /= mag;
            move.y /= mag;
        }
        return move;
    }

}

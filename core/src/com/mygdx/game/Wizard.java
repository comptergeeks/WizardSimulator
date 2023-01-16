package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;


public class Wizard {
    private Animation<TextureRegion> walkDownAnim;
    private Animation<TextureRegion> walkLeftAnim;
    private Animation<TextureRegion> walkRightAnim;
    private Animation<TextureRegion> walkUpAnim;
    float stateTime;
    int x;
    int y;
    int speed = 2;
    int vertical = 0;
    int horizontal = 0;
    TextureRegion[] region;
    TextureRegion[] walkDownRegion;
    TextureRegion[] walkLeftRegion;
    TextureRegion[] walkRightRegion;
    TextureRegion[] walkUpRegion;
    TextureRegion currentFrame;
    SpriteBatch batch;
    Texture wizardSheet;
    GameScreen game;
    ArrayList<MapObject> list;

    public Wizard(TextureRegion[] region, SpriteBatch batch, Texture wizardSheet, GameScreen game) {
        this.region = region;
        this.batch = batch;
        this.wizardSheet = wizardSheet;
        this.game = game;
        list = new ArrayList<>();

        createSpriteSheet();
        animationController();


    }

    public void render() {
        update();
    }

    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = region[1];
        //maybe add a true false system to determine which key was last pressed and find orinetation
        //avoid out of bounds movement via && statment whithin the script once map is passed
        Vector2 move = new Vector2(vertical, horizontal);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            move.y = 1;
            currentFrame = walkUpAnim.getKeyFrame(stateTime, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            move.y = -1;
            currentFrame = walkDownAnim.getKeyFrame(stateTime, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            move.x = 1;
            currentFrame = walkRightAnim.getKeyFrame(stateTime, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            move.x = -1;
            currentFrame = walkLeftAnim.getKeyFrame(stateTime, true);
        }
        move = normalize(move);
        batch.draw(currentFrame, x += move.x * speed, y += move.y * speed, 17, 17);
    }

    public Vector2 normalize(Vector2 move) {
        double mag = Math.sqrt(move.x * move.x + move.y * move.y);
        if (mag > 0) {
            move.x /= mag;
            move.y /= mag;
        }
        return move;
    }

    public void animationController() {
        walkDownRegion = new TextureRegion[3];
        walkDownRegion[0] = region[0];
        walkDownRegion[1] = region[1];
        walkDownRegion[2] = region[2];
        walkDownAnim = new Animation<TextureRegion>(0.2f, walkDownRegion);

        walkLeftRegion = new TextureRegion[3];
        walkLeftRegion[0] = region[3];
        walkLeftRegion[1] = region[4];
        walkLeftRegion[2] = region[5];
        walkLeftAnim = new Animation<TextureRegion>(0.2f, walkLeftRegion);
        stateTime = 0f;

        walkRightRegion = new TextureRegion[3];
        walkRightRegion[0] = region[6];
        walkRightRegion[1] = region[7];
        walkRightRegion[2] = region[8];
        walkRightAnim = new Animation<TextureRegion>(0.2f, walkRightRegion);

        walkUpRegion = new TextureRegion[3];
        walkUpRegion[0] = region[9];
        walkUpRegion[1] = region[10];
        walkUpRegion[2] = region[11];
        walkUpAnim = new Animation<TextureRegion>(0.2f, walkUpRegion);
        stateTime = 0f;
    }

    public int sendX() {
        return x;
    }

    public int sendY() {
        return y;
    }

    //create sprite sheet that is accessed by player class -> maybe switch to recursion to meet requirements
    //set a number when character is picked mutiply that number by 16 and add 1 to it each time, this approach will work and properly set the wizard spirte, but does not account for y axis change
    public void createSpriteSheet() {
        int tempY = 0;
        int tempX = 0;
        for (int i = 0; i < 12; i++) {
            if (i % 3 == 0 && i != 0) {
                tempY += 16;
            }
            region[i] = new TextureRegion(wizardSheet, tempX, tempY, 16, 16);
            if (tempX != 32) {
                tempX += 16;
            } else {
                tempX = 0;
            }
        }
    }

    public void setUpCollision() {
        if (game.selectedMap.equals(String.valueOf(Level.INSIDE))) {
            for (MapObject objects: game.insideScene.getLayers().get("walls").getObjects()){
                list.add(objects);
            }
            for (MapObject objects: game.insideScene.getLayers().get("Furniture").getObjects()){
                list.add(objects);
            }
            System.out.println(Arrays.toString(list.toArray()));
    }
    }

    public boolean isColliding() {
        return false;
    }
}

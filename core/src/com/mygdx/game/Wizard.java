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
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Arrays;


public class Wizard {
    //all animations
    private Animation<TextureRegion> walkDownAnim;
    private Animation<TextureRegion> walkLeftAnim;
    private Animation<TextureRegion> walkRightAnim;
    private Animation<TextureRegion> walkUpAnim;
    //time handling for animation
    float stateTime;
    //position of wizard
    int x;
    int y;
    //speed and movement values
    int speed = 2;
    //regions with indivdual "animation" frames
    TextureRegion[] region;
    TextureRegion[] walkDownRegion;
    TextureRegion[] walkLeftRegion;
    TextureRegion[] walkRightRegion;
    TextureRegion[] walkUpRegion;
    TextureRegion currentFrame;
    //batch handles screen rendering
    SpriteBatch batch;
    //image of sprite sheet
    Texture wizardSheet;
    GameScreen game;
    Rectangle rect;
    //array lists for collisions
    ArrayList<MapObject> list;
    ArrayList<MapObject> dungeon;

    public Wizard(TextureRegion[] region, SpriteBatch batch, Texture wizardSheet, GameScreen game) {
        this.region = region;
        this.batch = batch;
        this.wizardSheet = wizardSheet;
        this.game = game;
        list = new ArrayList<>();
        dungeon = new ArrayList<>();

        createSpriteSheet(region, wizardSheet, 0, 0, 0);
       rect = new Rectangle(x, y, 14, 14);
        animationController();


    }

    public void render() {
        //update movement and animation
        update();
    }

    public void update() {
        rect.setPosition(x, y);
        //animation time
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = region[1];

        Vector2 move = new Vector2(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.W) ) {
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
        move.scl(speed);
        rect.x += move.x;
        rect.y += move.y;
        if(isColliding()){
            rect.x -= move.x;
            rect.y -= move.y;
        }
        //set x to collision boundary
        x = (int)rect.x;
        y = (int)rect.y;
        //draw the sprites new position
        batch.draw(currentFrame, x, y, 16, 16);
    }

    //physics equation to normalize the movement, so diagonal movement is not faster than up/down/left/right
    public Vector2 normalize(Vector2 move) {
        double mag = Math.sqrt(move.x * move.x + move.y * move.y);
        if (mag > 0) {
            move.x /= mag;
            move.y /= mag;
        }
        return move;
    }

    public void animationController() {
        //creates different "animations", based on the positon in the sprite sheet
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
    //sendX (getter) used for other methods to access the current position of the wizard
    public int sendX() {
        return x;
    }
    //sendY (getter), used for other methods to access the current position of the wizard
    public int sendY() {
        return y;
    }
    //recursive method to handle the animation of the character
    //for future expansion I plan to make this system modular, with i being multiplied my different values to allow for different characters to be chosen
    public void createSpriteSheet(TextureRegion[] region, Texture wizardSheet, int tempX, int tempY, int i) {
        if (i == 12) {
            return;
        }
        if (i % 3 == 0 && i != 0) {
            tempY += 16;
        }
        region[i] = new TextureRegion(wizardSheet, tempX, tempY, 16, 16);
        if (tempX != 32) {
            tempX += 16;
        } else {
            tempX = 0;
        }
        createSpriteSheet(region, wizardSheet, tempX, tempY, i + 1);
    }

    public void setUpCollision() {
        if (game.selectedMap.equals(String.valueOf(Level.INSIDE))) {
            for (MapObject objects : game.insideScene.getLayers().get("walls").getObjects()) {
                list.add(objects);
            }
            for (MapObject objects : game.insideScene.getLayers().get("Furniture").getObjects()) {
                list.add(objects);
            }
            for (MapObject objects : game.insideScene.getLayers().get("dungeon floor").getObjects()) {
                dungeon.add(objects);
            }
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    //boolean
    public boolean isColliding() {
        for (MapObject object : list) {
            Rectangle rect2 = ((RectangleMapObject) object).getRectangle();
            if (rect.overlaps(rect2)) {

                return true;
            }
        }
        return false;
    }
    public void updateWizardRect() {
        //wizardPos.get().x
    }

}

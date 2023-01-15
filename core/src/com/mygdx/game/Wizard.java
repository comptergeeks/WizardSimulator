package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Wizard {
    private Animation<TextureRegion> walkDownAnim;
    private Animation<TextureRegion> walkLeftAnim;
    private Animation<TextureRegion> walkRightAnim;
    private Animation<TextureRegion> walkUpAnim;
    float stateTime;
    int x;
    int y;
    int speed = 3;
    int vertical = 0;
    int horizontal = 0;
    TextureRegion[] region;
    TextureRegion[] walkDownRegion;
    TextureRegion[] walkLeftRegion;
    TextureRegion[] walkRightRegion;
    TextureRegion[] walkUpRegion;
    TextureRegion currentFrame;
    SpriteBatch batch;

    public Wizard(TextureRegion[] region, SpriteBatch batch) {
        this.region = region;
        this.batch = batch;
        animationController();


    }
    public void render() {
        update();

    }
    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = region[1];
        //maybe add a true false system to determine which key was last pressed and find orinetation
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
        batch.draw(currentFrame, x+= move.x * speed , y+= move.y * speed);
    }
    public Vector2 normalize(Vector2 move) {
        double mag = Math.sqrt(move.x*move.x + move.y*move.y);
        if(mag > 0) {
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

}

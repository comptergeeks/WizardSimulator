package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;

public class TitleScreen extends ScreenAdapter {
    GameScreen game;
    Skin skin;
    Texture background;
    Texture title;
    Texture pressEnter;
    Image start;
    Float fadeTime = 1f;

    public TitleScreen(GameScreen game) {
        this.game = game;
        background = new Texture("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/starting graphics/background.png");
        title = new Texture("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/starting graphics/wizardsimulator.png");
        pressEnter = new Texture("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/starting graphics/batsugunpressenter.png");
        start = new Image(pressEnter);
    }
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new OutsideLevel(game));
                }
                return true;
            }
        });
        this.dispose();
    }
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, 800, 800);
        game.batch.draw(title, 150, 700);
        game.batch.draw(pressEnter, 100, 100);
        //add blink effect for press start later
        game.batch.end();

    }
    public void hide() {
        background.dispose();
    }


}

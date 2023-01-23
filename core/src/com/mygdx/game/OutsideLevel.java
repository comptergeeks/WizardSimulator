package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class OutsideLevel extends ScreenAdapter {
    //game screen is where all the shared values are
    GameScreen game;

    public OutsideLevel(GameScreen game) {
        //starting position for wizard
        game.wizard.x = 40;
        this.game = game;
        //load correct map
        game.loadMap(game.outsideScene);
        //set selected map from enum for state of game, this is to set up collision
        game.selectedMap = String.valueOf(Level.OUTSIDE);
    }
    //render is how often the screen is updating
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.camera.update();
		game.batch.setProjectionMatrix(game.camera.combined);
        game.renderer.render();
		game.renderer.setView(game.camera);
		game.wizard.render();
        game.worldCam.updateCameraPos();
        //relative location of house
        if(game.wizard.sendX() > 428 && game.wizard.sendX() < 478 && game.wizard.sendY() > 316 && game.wizard.sendY() < 330) {
            game.setScreen(new Inside(game));
            //set wizard to new position after changing screens
            game.wizard.x = 32;
            game.wizard.y = 0;
            game.camera.update();
            game.worldCam.updateCameraPos();
            game.wizard.setUpCollision();
        }
        game.batch.end();
    }

    @Override
    public void hide() {
        game.outsideScene.dispose();
    }
}

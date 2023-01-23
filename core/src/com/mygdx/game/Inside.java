package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class Inside extends ScreenAdapter {
    GameScreen game;
    public Inside(GameScreen game) {
        //similar set up to outside level
        this.game = game;
        game.loadMap(game.insideScene);
        game.selectedMap = String.valueOf(Level.INSIDE);
    }
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.begin();
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.renderer.render();
        game.renderer.setView(game.camera);
        game.wizard.render();
        game.worldCam.updateCameraPos();
        game.batch.end();
    }

}

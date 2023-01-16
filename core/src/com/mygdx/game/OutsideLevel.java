package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class OutsideLevel extends ScreenAdapter {
    GameScreen game;

    public OutsideLevel(GameScreen game) {
        game.wizard.x = 40;
        this.game = game;
        game.loadMap(game.outsideScene);
        game.selectedMap = String.valueOf(Level.OUTSIDE);
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
        if(game.wizard.sendX() > 428 && game.wizard.sendX() < 478 && game.wizard.sendY() > 316 && game.wizard.sendY() < 330) {
            game.setScreen(new Inside(game));
            game.wizard.x = 16;
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

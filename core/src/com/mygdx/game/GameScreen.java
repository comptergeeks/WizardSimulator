package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import org.w3c.dom.Text;

public class GameScreen extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	Texture wizardSheet;
	Sprite wizard;
	TextureRegion region;

	
	@Override
	public void create () {
		//create player here, outsource render to new class
		wizardSheet = new Texture(Gdx.files.internal("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/06-conjurer.png"));
		region = new TextureRegion(wizardSheet, 0, 0, 16, 16);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(region, 0, 0);
		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}

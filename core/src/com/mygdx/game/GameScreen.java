package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	Texture wizardSheet;
	//create region array, this will handle rotation and each indivdual sqaure will have its own region
	TextureRegion[] region;
	Wizard wizard;



	@Override
	public void create () {
		//create player here, outsource render to new class
		batch = new SpriteBatch();
		wizardSheet = new Texture(Gdx.files.internal("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/06-conjurer.png"));
		region = new TextureRegion[12];
		createSpriteSheet();
		wizard = new Wizard(region, batch);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		//update player pos
		batch.begin();
		wizard.render();
		batch.end();

	}
	
	@Override
	public void dispose () {

	}
	//create sprite sheet that is accessed by player class -> maybe switch to recursion to meet requirements
	public void createSpriteSheet() {
		int tempY = 0;
		int tempX = 0;
		for (int i = 0; i < 12; i++) {
			if (i%3 == 0 && i != 0) {
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
}

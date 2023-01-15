package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ApplicationAdapter {
	private OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	private SpriteBatch batch;
	TiledMap map;

	Texture wizardSheet;
	//region array = spritesheet
	TextureRegion[] region;
	Wizard wizard;
	Camera worldCam;



	@Override
	public void create () {
		//create player here, outsource render to new class
		wizardSheet = new Texture(Gdx.files.internal("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/06-conjurer.png"));

		map = new TmxMapLoader().load("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/outsidetiledmap/outsideHouse.tmx");
		loadMap();

		region = new TextureRegion[12];
		createSpriteSheet();

		batch = new SpriteBatch();

		wizard = new Wizard(region, batch);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 200, 200);
		//map.getHeight, once outsourced into new class
		worldCam = new Camera(camera, 480, 480, wizard);
		//outsource into setup Camera method once complete

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		//update player pos
		batch.begin();
		renderer.render();
		renderer.setView(camera);
		wizard.render();
		worldCam.updateCameraPos();
		//code is referenced in
		//
		batch.end();
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		wizardSheet.dispose();
		map.dispose();
	}
	//create sprite sheet that is accessed by player class -> maybe switch to recursion to meet requirements
	//move to wizard class later
	//set a number when character is picked mutiply that number by 16 and add 1 to it each time, this approach will work and properly set the wizard spirte, but does not account for y axis change
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
	public void loadMap() {
		float unitScale = 1f;
		renderer = new OrthogonalTiledMapRenderer(map, unitScale);
	}
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen extends Game {
	public OrthographicCamera camera;
	OrthogonalTiledMapRenderer renderer;
	public SpriteBatch batch;
	TiledMap outsideScene;
	TiledMap insideScene;

	Texture wizardSheet;
	//region array = spritesheet
	TextureRegion[] region;
	Wizard wizard;
	Camera worldCam;



	@Override
	public void create () {
		//create player here, outsource render to new class
		wizardSheet = new Texture(Gdx.files.internal("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/06-conjurer.png"));

		outsideScene = new TmxMapLoader().load("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/outsidetiledmap/outsideHouse.tmx");
		loadMap();

		region = new TextureRegion[12];
		//createSpriteSheet();

		batch = new SpriteBatch();
		setScreen(new TitleScreen(this));

		wizard = new Wizard(region, batch, wizardSheet);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 200, 200);
		worldCam = new Camera(camera, 480, 480, wizard);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void loadMap() {
		float unitScale = 1f;
		//render based on chosen scene (enum perhaps)
		renderer = new OrthogonalTiledMapRenderer(outsideScene, unitScale);
	}
}

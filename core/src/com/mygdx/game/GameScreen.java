package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen extends Game {
	//camera handles rendering distance and viewing
	public OrthographicCamera camera;
	//renders map
	OrthogonalTiledMapRenderer renderer;
	//handles rendering to screen
	public SpriteBatch batch;
	//maps that I have created
	TiledMap outsideScene;
	TiledMap insideScene;

	Texture wizardSheet;
	//region array = spritesheet
	TextureRegion[] region;
	//Wizard character
	Wizard wizard;
	//worldCam is the camera that handles the limited view distance
	Camera worldCam;
	//gets the string of the selected map to detect objects for collision
	String selectedMap;



	@Override
	public void create () {
		//since the class structure is using encapsulation shared assets are created here
		//create player here, outsource render to new class
		wizardSheet = new Texture(Gdx.files.internal("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/06-conjurer.png"));
		//creaing maps on startup
		outsideScene = new TmxMapLoader().load("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/outsidetiledmap/outsideHouse.tmx");
		insideScene = new TmxMapLoader().load("/Users/farhankhan/Documents/school/12 grade/RPGProject/assets/insidetiledmap/insidehouse.tmx");
		//region for sprite sheet
		region = new TextureRegion[12];
		//batch once again handles rendering to screen
		batch = new SpriteBatch();
		//encapsulation is being used here.
		setScreen(new TitleScreen(this));
		//wizard character created
		wizard = new Wizard(region, batch, wizardSheet, this);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 200, 200);
		//setting view distance
		worldCam = new Camera(camera, 480, 480, wizard, this);
		selectedMap = String.valueOf(Level.MENU);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}

	//loap map in proper scale
	public void loadMap(TiledMap mapToLoad) {
		float unitScale = 1f;
		//render based on chosen scene (enum perhaps)
		renderer = new OrthogonalTiledMapRenderer(mapToLoad, unitScale);
	}
}

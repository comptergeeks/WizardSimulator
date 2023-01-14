package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	Texture mainCharacter;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 400);
		mainCharacter = new Texture(Gdx.files.internal("![](../../../../../assets/knight.png)"));
		batch = new SpriteBatch(); 

	}

	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {

	}
}

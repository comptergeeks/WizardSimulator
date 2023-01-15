package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;


public class Camera {
    int mapLeft= 0;
    // The right boundary of the map (x + width)
    int mapRight;
    // The bottom boundary of the map (y)
    int mapBottom = 0;
    // The top boundary of the map (y + height)
    int mapTop;
    // The camera dimensions, halved
    float cameraHalfWidth;
    float cameraHalfHeight;
    float cameraLeft;
    float cameraRight;
    float cameraBottom;
    float cameraTop;
    Wizard wizard;
    OrthographicCamera camera;

    public Camera(OrthographicCamera camera, int mapTop, int mapRight, Wizard wizard) {
        this.camera = camera;
        this.mapTop = mapTop;
        this.mapRight = mapRight;
        this.wizard = wizard;

    }

    //referenced in documenation, stack exchange to move camera (might update layer)
    //shorter method might be to clamp camera position, create an alternative method and test tehre.
    public void updateCameraPos() {
        Vector2 camerPos = new Vector2(wizard.sendX(), wizard.sendY());
        cameraHalfWidth = camera.viewportWidth * .5f;
        cameraHalfHeight = camera.viewportHeight * .5f;
        camera.position.set(camerPos, 0);
        cameraLeft = camera.position.x - cameraHalfWidth;
        cameraRight= camera.position.x + cameraHalfWidth;
        cameraBottom = camera.position.y - cameraHalfHeight;
        cameraTop = camera.position.y + cameraHalfHeight;

        if(480 < camera.viewportWidth)
        {
            camera.position.x = mapRight / 2;
        }
        else if(cameraLeft <= mapLeft)
        {
            camera.position.x = mapLeft + cameraHalfWidth;
        }
        else if(cameraRight >= mapRight)
        {
            camera.position.x = mapRight - cameraHalfWidth;
        }
        // Vertical axis
        if(480 < camera.viewportHeight)
        {
            camera.position.y = mapTop / 2;
        }
        else if(cameraBottom <= mapBottom)
        {
            camera.position.y = mapBottom + cameraHalfHeight;
        }
        else if(cameraTop >= mapTop)
        {
            camera.position.y = mapTop - cameraHalfHeight;
        }
    }
}

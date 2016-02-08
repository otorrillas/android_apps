package ntnu.otorrillas.introexercise.task3;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import ntnu.otorrillas.introexercise.Helicopter;
import ntnu.otorrillas.introexercise.R;
import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

/**
 * Created by otorr on 08/02/2016.
 */
public class Task3Layer extends Layer {


    private ArrayList<Helicopter> helicopters;
    private float[] screenDimensions = new float[2];
    private float[] imageDimensions = new float[2];


    public Task3Layer(int height, int width) {

        /* Initialization values */
        Image img = new Image(R.drawable.helicopter1);
        screenDimensions[1] = (float) height;
        screenDimensions[0] = (float) width;
        imageDimensions[0] = img.getWidth();
        imageDimensions[1] = img.getHeight();
        float[] helicopters_posX = {500, 500, 500};
        float[] helicopters_posY = {100, 400, 800};
        float[] helicopters_speedX = {-400, -100, -800};
        float[] helicopters_speedY = {-600, 400, -100};

        /* Initialization parameters */
        helicopters = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            Helicopter helicopter = new Helicopter(img, helicopters_posX[i], helicopters_posY[i]);
            helicopter.setSpeed(helicopters_speedX[i], helicopters_speedY[i]);
            helicopters.add(helicopter);
        }
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        for(Helicopter helicopter : helicopters)
            helicopter.draw(canvas);
    }

    @Override
    public void update(float dt) {
        for(Helicopter helicopter : helicopters) {
            bounceOnMargins(helicopter);
            bounceOnCollisions(helicopter);
            helicopter.update(dt);
        }
    }

    private void bounceOnCollisions(Helicopter helicopter) {
        for(Helicopter heli2 : helicopters) {
            if(heli2 != helicopter && helicopter.collides(heli2)) {
                helicopter.changeDirX();
                helicopter.changeDirY();
            }
        }
    }


    private void bounceOnMargins(Helicopter helicopter) {
        Vector2 currPos = helicopter.getPosition();
        Vector2 currSpeed = helicopter.getSpeed();

        float imgFactorX = imageDimensions[0] / 2.f;
        float imgFactorY = imageDimensions[1] / 2.f;

        /* X axis */
        if (currSpeed.getX() > 0 && (currPos.getX() + imgFactorX) > screenDimensions[0]) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirX();
        }
        if (currSpeed.getX() < 0 && (currPos.getX() - imgFactorX) < 0) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirX();
        }

        /* Y axis */
        if (currSpeed.getY() > 0 && (currPos.getY() + imgFactorY) > screenDimensions[1]) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirY();
        }
        if (currSpeed.getY() < 0 && (currPos.getY() - imgFactorY) < 0) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirY();
        }


    }

}

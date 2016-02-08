package ntnu.otorrillas.introexercise.task3;

import android.graphics.Canvas;
import android.util.Log;

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

    private Helicopter helicopter;
    private float[] screenDimensions = new float[2];
    private float[] imageDimensions = new float[2];

    public Task3Layer(int height, int width) {
        Image img = new Image(R.drawable.helicopter1);
        screenDimensions[1] = (float) height;
        screenDimensions[0] = (float) width;
        imageDimensions[0] = img.getWidth();
        imageDimensions[1] = img.getHeight();
        Log.v("ImgWidth", Float.toString(imageDimensions[0]));
        Log.v("ImgHeight", Float.toString(imageDimensions[1]));

        helicopter = new Helicopter(img, width / 2, height / 2);
        helicopter.setSpeed(-400, 400);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helicopter.draw(canvas);
    }

    @Override
    public void update(float dt) {
        bounceOnMargins();
        helicopter.update(dt);
    }


    private void bounceOnMargins() {
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

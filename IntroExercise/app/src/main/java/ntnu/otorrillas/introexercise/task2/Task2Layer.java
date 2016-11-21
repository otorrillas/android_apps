package ntnu.otorrillas.introexercise.task2;

import android.graphics.Canvas;
import android.util.Log;

import ntnu.otorrillas.introexercise.Helicopter;
import ntnu.otorrillas.introexercise.R;
import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

/**
 * Created by otorr on 08/02/2016.
 */
public class Task2Layer extends Layer {

    private Helicopter helicopter;
    private float[] screenscreenDimensions = new float[2];
    private float[] imagescreenDimensions = new float[2];

    private TextButton txtBut;

    public Task2Layer(int height, int width) {
        Image img = new Image(R.drawable.helicopter1);
        screenscreenDimensions[1] = (float) height;
        screenscreenDimensions[0] = (float) width;
        imagescreenDimensions[0] = img.getWidth();
        imagescreenDimensions[1] = img.getHeight();
        Log.v("ImgWidth", Float.toString(imagescreenDimensions[0]));
        Log.v("ImgHeight", Float.toString(imagescreenDimensions[1]));

        helicopter = new Helicopter(img, width / 2, height / 2);
        String label = "X: " + Float.toString(helicopter.getX())
                + "\n Y: " + Float.toString(helicopter.getY());
        txtBut = new TextButton(50, 50, label);
    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        helicopter.draw(canvas);
        txtBut.draw(canvas);
    }

    @Override
    public void update(float dt) {
        helicopter.update(dt);
        String label = "X: " + Float.toString(helicopter.getX())
                + "\n Y: " + Float.toString(helicopter.getY());
        txtBut.setLabel(label);
    }


    private void bounceOnMargins() {
        Vector2 currPos = helicopter.getPosition();
        Vector2 currSpeed = helicopter.getSpeed();

        float imgFactorX = imagescreenDimensions[0] / 2.f;
        float imgFactorY = imagescreenDimensions[1] / 2.f;

        /* X axis */
        if (currSpeed.getX() > 0 && (currPos.getX() + imgFactorX) > screenscreenDimensions[0]) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirX();
        }
        if (currSpeed.getX() < 0 && (currPos.getX() - imgFactorX) < 0) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirX();
        }

        /* Y axis */
        if (currSpeed.getY() > 0 && (currPos.getY() + imgFactorY) > screenscreenDimensions[1]) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirY();
        }
        if (currSpeed.getY() < 0 && (currPos.getY() - imgFactorY) < 0) {
            Log.v("CurrPos", currPos.toString());
            helicopter.changeDirY();
        }

    }

    public void userPressed(float x, float y) {
        float imgFactorX = imagescreenDimensions[0] / 2.f;
        float imgFactorY = imagescreenDimensions[1] / 2.f;
        x = Math.max(0, x - imgFactorX);
        x = Math.min(x + imgFactorX, screenscreenDimensions[0]);

        y = Math.max(0, y - imgFactorY);
        y = Math.min(y + imgFactorY, screenscreenDimensions[1]);

        helicopter.setPosition(x, y);
    }

}

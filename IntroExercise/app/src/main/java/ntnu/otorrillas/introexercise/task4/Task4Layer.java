package ntnu.otorrillas.introexercise.task4;

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
 * Created by otorr on 09/02/2016.
 */
public class Task4Layer extends Layer {
    private Ball pokeball;
    private float[] screenscreenDimensions = new float[2];
    private float[] imagescreenDimensions = new float[2];

    private TextButton txtBut;

    public Task4Layer(int height, int width) {
        Image img = new Image(R.drawable.pokeball);
        screenscreenDimensions[1] = (float) height;
        screenscreenDimensions[0] = (float) width;
        imagescreenDimensions[0] = img.getWidth();
        imagescreenDimensions[1] = img.getHeight();
        Log.v("ImgWidth", Float.toString(imagescreenDimensions[0]));
        Log.v("ImgHeight", Float.toString(imagescreenDimensions[1]));

        pokeball = new Ball(img, width / 2, height / 2);

    }

    @Override
    public void draw(Canvas canvas, BoundingBox boundingBox) {
        pokeball.draw(canvas);
    }

    @Override
    public void update(float dt) {
        bounceOnMargins();
        pokeball.update(dt);
    }


    private void bounceOnMargins() {
        Vector2 currPos = pokeball.getPosition();
        Vector2 currSpeed = pokeball.getSpeed();

        float imgFactorX = imagescreenDimensions[0] / 2.f;
        float imgFactorY = imagescreenDimensions[1] / 2.f;

        /* X axis */
        if (currSpeed.getX() > 0 && (currPos.getX() + imgFactorX) > screenscreenDimensions[0]) {
            Log.v("CurrPos", currPos.toString());

        }
        if (currSpeed.getX() < 0 && (currPos.getX() - imgFactorX) < 0) {
            Log.v("CurrPos", currPos.toString());

        }

        /* Y axis */
        if (currSpeed.getY() > 0 && (currPos.getY() + imgFactorY) > screenscreenDimensions[1]) {
            Log.v("CurrPos", currPos.toString());

        }
        if (currSpeed.getY() < 0 && (currPos.getY() - imgFactorY) < 0) {
            Log.v("CurrPos", currPos.toString());

        }

    }


}

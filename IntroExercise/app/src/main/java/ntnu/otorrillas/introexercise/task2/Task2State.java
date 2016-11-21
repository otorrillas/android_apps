package ntnu.otorrillas.introexercise.task2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import sheep.game.State;
import sheep.game.World;
import sheep.input.Touch;
import sheep.input.TouchListener;

/**
 * Created by otorr on 01/02/2016.
 */
public class Task2State extends State {

    private World world;
    private Task2Layer task2Layer;

    public Task2State(int height, int width) {
        world = new World();
        task2Layer = new Task2Layer(height, width);
        world.addLayer(task2Layer);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        world.draw(canvas);
    }

    public void update(float dt) {
        world.update(dt);
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        Log.v("X pressed: ", Float.toString(x));
        Log.v("Y pressed: ", Float.toString(y));

        task2Layer.userPressed(x, y);
        return super.onTouchUp(event);
    }
}

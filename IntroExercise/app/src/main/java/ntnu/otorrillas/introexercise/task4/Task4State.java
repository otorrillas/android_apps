package ntnu.otorrillas.introexercise.task4;

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
public class Task4State extends State {

    private World world;
    private Task4Layer task4Layer;

    public Task4State(int height, int width) {
        world = new World();
        task4Layer = new Task4Layer(height, width);
        world.addLayer(task4Layer);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(0, 0, 0));
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

        return super.onTouchUp(event);
    }
}

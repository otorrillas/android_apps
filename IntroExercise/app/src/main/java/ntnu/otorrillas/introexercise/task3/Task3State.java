package ntnu.otorrillas.introexercise.task3;

import android.graphics.Canvas;
import android.graphics.Color;

import ntnu.otorrillas.introexercise.R;
import sheep.audio.Sound;
import sheep.game.State;
import sheep.game.World;

/**
 * Created by otorr on 01/02/2016.
 */
public class Task3State extends State {

    private World world;
    private Task3Layer task3Layer;


    public Task3State(int height, int width) {
        world = new World();

        task3Layer = new Task3Layer(height, width);
        world.addLayer(task3Layer);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(254, 0, 254));
        world.draw(canvas);
    }

    public void update(float dt) {
        world.update(dt);
    }


}

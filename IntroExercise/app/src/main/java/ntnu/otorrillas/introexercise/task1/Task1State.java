package ntnu.otorrillas.introexercise.task1;

import android.graphics.Canvas;
import android.graphics.Color;

import sheep.game.State;
import sheep.game.World;

/**
 * Created by otorr on 01/02/2016.
 */
public class Task1State extends State {

    private World world;
    private Task1Layer task1Layer;

    public Task1State(int height, int width) {
        world = new World();
        task1Layer = new Task1Layer(height, width);
        world.addLayer(task1Layer);
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

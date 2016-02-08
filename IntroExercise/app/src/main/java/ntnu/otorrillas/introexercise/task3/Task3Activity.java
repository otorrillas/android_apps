package ntnu.otorrillas.introexercise.task3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import sheep.game.Game;

public class Task3Activity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the game.
        game = new Game(this, null);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int maxHeight = metrics.heightPixels - 220;
        int maxWidth = metrics.widthPixels;

        // Create Task2State
        Task3State task3State = new Task3State(maxHeight, maxWidth);
        Log.v("MaxHeight", Integer.toString(maxHeight));
        Log.v("MaxWidth", Float.toString(maxWidth));

        // Push the main state.
        game.pushState(task3State);

        // View the game.
        setContentView(game);
    }

    @Override
    public void onBackPressed() {
        game.popState();
        super.onBackPressed();
    }
}

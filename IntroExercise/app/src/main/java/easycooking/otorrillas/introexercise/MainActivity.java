package easycooking.otorrillas.introexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import sheep.game.Game;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the game.
        Game game = new Game(this, null);

        // Push the main state.
        game.pushState(new TitleScreen());

        // View the game.
        setContentView(game);
    }
}

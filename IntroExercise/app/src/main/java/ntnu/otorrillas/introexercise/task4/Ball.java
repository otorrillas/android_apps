package ntnu.otorrillas.introexercise.task4;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by otorr on 09/02/2016.
 */
public class Ball extends Sprite {

    Ball (Image image, float posx, float posy) {
        super(image);
        setPosition(posx, posy);
    }
}

package ntnu.otorrillas.introexercise;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by otorr on 06/02/2016.
 */
public class Helicopter extends Sprite {

    public Helicopter(Image image, float posx, float posy) {
        super(image);
        setPosition(posx, posy);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }

    public void changeDirX() {
        setXSpeed(-getSpeed().getX());
        invertHoritzontal();
        setOrientation(180 - getOrientation());
    }


    public void changeDirY() {
        setYSpeed(-getSpeed().getY());
    }

    private void invertHoritzontal() {
        if (getSpeed().getX() > 0)
            setScale(1, -1);
        else
            setScale(1, 1);
    }

    private void invertVertically() {
        if (getSpeed().getY() > 0)
            setScale(1, 1);
        else
            setScale(-1, 1);
    }
}

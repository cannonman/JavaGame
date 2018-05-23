import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

public class Bow {
    float bowAngle;
    Sprite bSprite;
    Texture bTexture;
    int angle = 0;

    public Bow(int i, int i1) {

    }

    Sprite getSprite()
    {
        return bSprite;
    }

    void changeAngleUp()
    {
        bSprite.rotate((float) -2.5);
        angle-=2.5;
    }

    void changeAngleDown()
    {
        bSprite.rotate((float) 2.5);
        angle+=2.5;
    }

    void setAngle(float angle)
    {
        bSprite.setRotation(angle);
    }

    void reset()
    {
        bSprite.setRotation(0);
    }

    float getAngle()
    {
        return angle;
    }
}

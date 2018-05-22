import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

public class Arrow {

    static final int BEGIN_X = 84;
    static final int BEGIN_Y = 340;

    RectangleShape arrow;
    boolean released;
    Sprite aSprite;
    Texture aTexture;
    float beginX, beginY;

    Vector2f currVelo;
    float maxSpeed;

    Arrow(float x, float y) : Arrow(0.f,0.f); maxSpeed(10.f) throws IOException {

        aTexture.loadFromFile(Paths.get("arrowDef.png"));
        aSprite.setPosition(getBeginX(), getBeginY());
        aSprite.setTexture(aTexture);
        released=false;

    }


    void release()
    {
        released = true;
    }

    Sprite getSprite()
    {
        return aSprite;
    }

    void changeAngleUp()
    {
        aSprite.rotate((float) -2.5);
    }

    void changeAngleDown()
    {
        aSprite.rotate((float) 2.5);
    }

    void setAngle(float angle)
    {
        aSprite.setRotation(angle);
    }
    void resetPosition()
    {
        aSprite.setPosition(getBeginX(), getBeginY());
        released=false;
    }

    boolean ifReleased()
    {
        return released;
    }

    int getBeginX()
    {
        return BEGIN_X;
    }

    int getBeginY()
    {
        return BEGIN_Y;
    }
}

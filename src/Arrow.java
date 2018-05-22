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

    Arrow(float x, float y) : currVelo(0.f,0.f), maxSpeed(10.f)
    {

        aTexture.loadFromFile("arrowDef.png");
        aSprite.setPosition(getBeginX(), getBeginY());
        aSprite.setTexture(aTexture);
        Arrow::released=false;

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
        aSprite.rotate(-2.5);
    }

    void changeAngleDown()
    {
        aSprite.rotate(2.5);
    }

    void setAngle(float angle)
    {
        aSprite.setRotation(angle);
    }
    void resetPosition()
    {
        aSprite.setPosition(getBeginX(), getBeginY());
        Arrow::released=false;
    }

    boolean ifReleased()
    {
        return Arrow::released;
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

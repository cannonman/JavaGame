public class Bow {
    float bowAngle;
    Sprite bSprite;
    Texture bTexture;

    Sprite getSprite()
    {
        return bSprite;
    }

    void changeAngleUp()
    {
        bSprite.rotate(-2.5);
        angle-=2.5;
    }

    void changeAngleDown()
    {
        bSprite.rotate(2.5);
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

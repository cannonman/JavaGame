public class Target {
    float targetSpeed;
    static final int BEGIN_X = 550;
    static final int BEGIN_Y = 5;


    Target (float x, float y) {
        aTexture.loadFromFile("arrowDef.png");
        aSprite.setTexture(aTexture);
        // aSprite.setScale(Vector2f(0.5, 0.5));
        aSprite.setPosition(x, y);
    }

    void resetPosition() {
        aSprite.setPosition((random() % 350) + 200, getY());
    }

    int getX()
    {
        return BEGIN_X;
    }

    int getY()
    {
        return BEGIN_Y;
    }

    void setSpeed(float speed) {
        targetSpeed = speed;
    }

    Sprite getSprite(){
        return aSprite;
    }

    Texture getTexture(){
        return aTexture;
    }

    void objMove(int move)
    {

        aSprite.move(0,move);

    }

    Vector2f objPos()
    {

        return Vector2f(aSprite.getPosition().x,aSprite.getPosition().y);

    }
}

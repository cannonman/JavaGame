import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;

import static com.sun.tools.jdeprscan.DeprDB.loadFromFile;
import static java.lang.Math.random;

public class Target {
    float targetSpeed;
    static final int BEGIN_X = 550;
    static final int BEGIN_Y = 5;

    Sprite aSprite;
    Texture aTexture;
    Target (float x, float y) throws IOException {
        Texture aTexture.loadFromFile("arrowDef.png");
        aSprite.setTexture(aTexture);
        // aSprite.setScale(Vector2f(0.5, 0.5));
        aSprite.setPosition(x, y);
    }

    void resetPosition() {
        aSprite.setPosition((float) ((random() % 350) + 200), getY());
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

        return new Vector2f(aSprite.getPosition().x,aSprite.getPosition().y);

    }
}

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

public class Player {
    Vector2f pozycja;
    RectangleShape ksztalt;
    Sprite pSprite;
    Texture pTexture;
    Text tekst;

    Player(float startX, float startY) throws IOException {
        pTexture.loadFromFile(Paths.get("pirate.png"));
        pSprite.setTexture(pTexture);

        pSprite.setPosition(startX,startY);

        pozycja = new Vector2f(pSprite.getPosition().x, pSprite.getPosition().y);
    }

    Sprite getSpirte()
    {
        return pSprite;
    }
}

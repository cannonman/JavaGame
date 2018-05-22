public class Player {
    Vector2f pozycja;
    RectangleShape ksztalt;
    Sprite pSprite;
    Texture pTexture;
    Text tekst;

    Player(float startX, float startY)
    {
        pTexture.loadFromFile("pirate.png");
        pSprite.setTexture(pTexture);

        pSprite.setPosition(startX,startY);

        pozycja = Vector2f (pSprite.getPosition().x, pSprite.getPosition().y);
    }

    Sprite getSpirte()
    {
        return pSprite;
    }
}

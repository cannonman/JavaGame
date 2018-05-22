import javax.swing.JFrame;
import java.awt.*;

public class Game extends JFrame{
    enum GameState{MENU,OPTIONS,GAME,GAME_OVER,END,TARGETHIT};
    GameState state;

    Font font;

    Sprite backgroundSprite;
    Texture backgroundTexture;
    double x;
    boolean kolizja;

    Clock mainClock;
    Time elapsed;

    Vector2f mousePos;
    Vector2f playerPos;
    Vector2f aimDir;
    Vector2f aimDirNorm;
    Vector2f vector;

    Player gracz;
    Bow luk;
    Arrow strzala;
    Target obiekt;

    int a=0;
    int score;
    float angle;
    int time;
    int diff;
    int lives;


    RenderWindow window(VideoMode(800,600),"Archer the Game");


    Game::Game()
    {
        state= END;

        gracz = new Player (-50,200);

        luk = new Bow (84,340);

        luk->reset();

        strzala = new Arrow(84, 340);
        strzala->resetPosition();

        obiekt = new Target (550,10);

        diff=0;
        time=5;

        angle=0;

        if(!font.loadFromFile("arial.ttf"))
        {

            //error

        }

        state = MENU;
    }

    Game::~Game()
    {
        //dtor
    }

    void Game::runGame()
    {

        while (state != END)
        {
            switch (state)
            {
                case GameState::MENU:
                    menu();
                    break;
                case GameState::GAME:
                    gameStart();
                    break;
                case GameState::OPTIONS:
                    options();
                    break;
                case GameState::GAME_OVER:
                    gameOver();
                    break;

            }
        }

    }

    void Game::gameStart()
    {

        backgroundTexture.loadFromFile("jungle.png");
        backgroundSprite.setTexture(backgroundTexture); //load texture
        score=0;
        switch (diff)
        {
            case 0: time = 6;
                break;
            case 1: time = 5;
                break;
            case 2: time = 4;
                break;
            //default: time=5;
        }

        lives = 5;
        while (state == GAME)
        {


            mainClock.restart(); //start time measure
            Text title ("Archer the Game",font,30);
            string points;
            points = "Punkty: ";
            stringstream ss, sa;
            sa << lives;
            ss<< score;
            points+=ss.str();
            points += " Zycia: ";
            points += sa.str();
            Text punkty(points ,font,15);
            title.setStyle(Text::Bold);
            title.setPosition(800/2-title.getGlobalBounds().width/2,20); //setting window options
            punkty.setPosition(10,10);


            Event event;


            while (window.pollEvent(event)) //wait for event
            {

                vector.y = -ARROW_SPEED*(-sin((float)M_PI*(angle/180)));
                vector.x =ARROW_SPEED*cos ((float)M_PI*abs(angle/180));



                if (event.type==Event::Closed || Event::KeyPressed && event.key.code == Keyboard::Escape)
                    state = END;
                //game escape

                if (Event::KeyPressed && event.key.code == Keyboard::Up)
                {
                    if (angle-1.5>=MAX_ANGLE) {
                        angle -=1.5;
                        luk->setAngle(angle);
                        if (!strzala->ifReleased()){

                            strzala->setAngle(angle); //lift bow  and arrow up
                        }

                    }
                }

                if (Event::KeyPressed && event.key.code == Keyboard::Down)
                {
                    if (angle+1.5<=MIN_ANGLE) {
                        angle +=1.5;
                        luk->setAngle(angle);

                        if (!strzala->ifReleased()){
                            strzala->setAngle(angle); //lift bow and arrow down

                        }
                    }
                }

                if (Event::KeyPressed && event.key.code == Keyboard::Space)
                {

                    strzala->release();



                    if (strzala->aSprite.getPosition().x < 0 || strzala->aSprite.getPosition().x > window.getSize().x) {
                        delete strzala;
                        strzala = new Arrow(84, 340);
                        strzala->resetPosition();
                        strzala->setAngle(angle);
                        window.draw(strzala->getSprite());
                    }
                    if (strzala->aSprite.getPosition().y < 0 || strzala->aSprite.getPosition().y > window.getSize().y) {
                        delete strzala;
                        strzala = new Arrow(84, 340);
                        strzala->resetPosition();
                        strzala->setAngle(angle);
                        window.draw(strzala->getSprite());
                    }

                }

            }

            if (Collision::PixelPerfectTest(strzala->getSprite(),obiekt->getSprite()))
            {
                score++;

                strzala->resetPosition();
                obiekt->resetPosition();

                if (score%3==0&&time>1)
                    time--;


            }


            window.clear();
            window.draw(backgroundSprite);
            window.draw(gracz->getSpirte());
            window.draw(luk->getSprite());
            window.draw(strzala->getSprite());

            obiekt->objMove(3);
            //      if (a==0)
            //    {
            if (obiekt->aSprite.getPosition().y<600)
            {

                obiekt->objMove(5-time);

                a++;

            }
            if (obiekt->aSprite.getPosition().y>=600)
            {
                cout << "przed" << endl;
                obiekt->resetPosition();
                cout << "po" << endl;
                lives--;
                if (lives==0)
                    state = GAME_OVER;
            }

            //  }
            else if(czas(clock())%time!=0) {a=0;}

            if (strzala->ifReleased()){

                strzala->aSprite.move(vector);
            }


            window.draw(strzala->getSprite());
            window.draw(obiekt->getSprite());
            window.draw(title);
            window.draw(punkty);
            window.display();

            elapsed = mainClock.getElapsedTime(); //get time measured
            sleep((sf::milliseconds(1000/FRAMERATE) - mainClock.getElapsedTime()));

            if (lives==0)
                state=GAME_OVER;

        }

    }

    void Game::gameOver(){
        backgroundTexture.loadFromFile("jungle.png");
        backgroundSprite.setTexture(backgroundTexture); //load texture
        Text title;
        Text title2;
        Text powrot;
        title.setStyle(Text::Bold);
        title.setPosition(300, 50);
        title.setString("Koniec Gry");
        title.setFont(font);
        title.setCharacterSize(40);

        string points;
        stringstream ss;
        ss<< score;
        points+=ss.str();
        points+=" punktow";

        title2.setStyle(Text::Bold);
        title2.setPosition(300, 190);
        title2.setString(points);
        title2.setFont(font);
        title2.setCharacterSize(40);

        powrot.setStyle(Text::Bold);
        powrot.setPosition(325, 400);
        powrot.setString("Powrot");
        powrot.setFont(font);
        powrot.setCharacterSize(40);

        while (state==GAME_OVER){

            Vector2f mouse(Mouse::getPosition(window));

            Event event;

            while (window.pollEvent(event))
            {
                if (event.type==Event::Closed || Event::KeyPressed && event.key.code == Keyboard::Escape)
                    state = END;

                else if (powrot.getGlobalBounds().contains(mouse) && event.type == Event::MouseButtonReleased
                        && event.key.code== Mouse::Left)
                    state = MENU;


            }


            if(powrot.getGlobalBounds().contains(mouse))
                powrot.setColor(Color::Red);
            else powrot.setColor(Color::White);

            window.clear();
            window.draw(title);
            window.draw(title2);
            window.draw(powrot);
            window.setVisible(true);

            window.display();
        }
    }

    void Game::options()
    {

        Text title1 ("Archer The Game",font,80);
        title1.setStyle(Text::Bold);
        Text title2 ("Options",font,60);
        title1.setPosition(800/2-title1.getGlobalBounds().width/2,20);
        title2.setPosition(800/2-title2.getGlobalBounds().width/2,120);


        Text poziom;
        Text powrot;

        string str1[] = {"Easy","Normal","Hard"};

        poziom.setFont(font);
        poziom.setCharacterSize(65);

        poziom.setString(str1[0]);
        poziom.setPosition(800/2-poziom.getGlobalBounds().width/2,250);

        powrot.setFont(font);
        powrot.setCharacterSize(65);

        powrot.setString("Back");
        powrot.setPosition(1280/2-poziom.getGlobalBounds().width/2,250+2*120);

        while (state == OPTIONS)
        {
            Vector2f mouse(Mouse::getPosition(window));

            Event event;

            while (window.pollEvent(event))
            {
                if (event.type==Event::Closed || Event::KeyPressed && event.key.code == Keyboard::Escape)
                    state = END;

                else if (powrot.getGlobalBounds().contains(mouse) && event.type == Event::MouseButtonReleased
                        && event.key.code== Mouse::Left)
                    state = MENU;

            }

            if(powrot.getGlobalBounds().contains(mouse))
                powrot.setColor(Color::Red);
            else powrot.setColor(Color::White);

            poziom.setString(str1[diff]);

            if(poziom.getGlobalBounds().contains(mouse))
                poziom.setColor(Color::Red);
            else poziom.setColor(Color::White);

            if(poziom.getGlobalBounds().contains(mouse) && event.type == Event::MouseButtonReleased && event.key.code == Mouse::Button::Left)
            {
                if(diff==2) diff=0;
                else diff++;
                poziom.setString(str1[diff]);
                poziom.setPosition(800/2-poziom.getGlobalBounds().width/2,250);
            }

            window.clear();

            window.draw(title1);
            window.draw(title2);
            window.draw(poziom);
            window.draw(powrot);

            window.display();


        }



    }

    void Game::menu()
    {



        Text title ("Archer The Game",font,80);
        title.setStyle(Text::Bold);

        // cout<<diff<<endl;

        title.setPosition(800/2-title.getGlobalBounds().width/2,20);

    const int ile = 3;

        Text tekst[ile];

        string str[] = {"Play","Options","Exit"};

        for (int i=0;i<ile;i++)
        {


            tekst[i].setFont(font);
            tekst[i].setCharacterSize(65);

            tekst[i].setString(str[i]);
            tekst[i].setPosition(1280/2-tekst[i].getGlobalBounds().width/2,250+i*120);

        }

        while(state == MENU)
        {

            Event event;

            Vector2f mouse (Mouse::getPosition(window));

            while (window.pollEvent(event))
            {
                if (event.type==Event::Closed || Event::KeyPressed && event.key.code == Keyboard::Escape)
                    state = END;

                else if (tekst[2].getGlobalBounds().contains(mouse) && event.type == Event::MouseButtonReleased
                        && event.key.code== Mouse::Left)
                    state = END;
                if (tekst[0].getGlobalBounds().contains(mouse) && event.type == Event::MouseButtonReleased && event.key.code == Mouse::Left)
                    state = GAME;
                if (tekst[1].getGlobalBounds().contains(mouse) && event.type == Event::MouseButtonReleased && event.key.code == Mouse::Left)
                    state = OPTIONS;

            }
            for(int i=0;i<ile;i++)
                if(tekst[i].getGlobalBounds().contains(mouse))
                    tekst[i].setColor(Color::Red);
                else tekst[i].setColor(Color::White);

            window.clear();

            window.draw(title);
            for (int i =0; i<ile; i++)
                window.draw(tekst[i]);
            window.display();
        }

    }

    int Game::czas (clock_t t)
    {
        return static_cast <int> (t) / CLOCKS_PER_SEC;
    }

}

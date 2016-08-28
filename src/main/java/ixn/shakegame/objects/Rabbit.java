package ixn.shakegame.objects;

import ixn.shakegame.SnakeGame;

/**
 * Created by Антон on 20.08.2016.
 */
public class Rabbit {

    SnakeGame main;
    Snake s;

    public int posX, posY;

    public Rabbit(int startX, int startY) {
        posX = startX;
        posY = startY;
    }

    public void setRandomPosition() {
        posX = (int) (Math.random()*main.WIDTH);
        posY = (int) (Math.random()*main.HEIGHT);
    }
}

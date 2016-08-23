package ixn.shakegame.objects;

import ixn.shakegame.SnakeGame;

import static ixn.shakegame.SnakeGame.SCALE;

/**
 * Created by Антон on 20.08.2016.
 */
public class Snake {

    SnakeGame main;

    public int direction = 0;
    public int length = 2;

    public int snakeX[] = new int[main.WIDTH* SCALE];
    public int snakeY[] = new int[main.HEIGHT* SCALE];

    public Snake(int x0, int y0, int x1, int y1) {
        snakeX[0] = x0;
        snakeY[0] = y0;
        snakeX[1] = x1;
        snakeY[1] = y1;
    }

    public void move() {

        if ( snakeX[0] > main.WIDTH ) snakeX[0] = 0;
        if ( snakeX[0] < 0 ) snakeX[0] = main.WIDTH;
        if ( snakeY[0] > main.HEIGHT ) snakeY[0] = 0;
        if ( snakeY[0] < 0 ) snakeY[0] = main.HEIGHT;

        for (int d = length; d > 0; d--) {
            snakeX[d] = snakeX[d-1];
            snakeY[d] = snakeY[d-1];
        }

        if(direction == 0) snakeX[0]++;
        if(direction == 1) snakeY[0]++;
        if(direction == 2) snakeX[0]--;
        if(direction == 3) snakeY[0]--;
    }
}

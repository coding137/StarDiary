package com.hello.myapp.snakeapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class World {// 가로 새로의 길이가 바뀌고 landscape로 변경할 예정 게임 방식은 런닝 방식으로
    static final int WORLD_WIDTH = 15;
    static final int WORLD_HEIGHT = 8;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;
    static final int OBSTACLE_CYCLE = 5;

    public int cycle = 0;
    public Snake snake;
    public ArrayList<Stain> stain;
    public ArrayList<Ground> grounds;

    //    public Stain[] stain;
    public boolean gameOver = false;
    public int score = 0;
    public int max_stain = WORLD_WIDTH * WORLD_HEIGHT;
    public int current_stain = 0;
    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    public World() {
        snake = new Snake();

        stain = new ArrayList<Stain>();

        place_ground();
        init_obstacle();
    }

    private void place_ground() {

        for (int i = 0; i < WORLD_WIDTH; i++) {
            for (int j = 0; j < WORLD_HEIGHT; j++) {
                fields[i][j] = false;
            }
        }


        for (int i = 0; i < WORLD_WIDTH; i++) {
            for (int j = 6; j <= 7; j++) {
                if (i != 6) {
                    fields[i][j] = true;
                    stain.add(new Stain(i, j, Stain.types.Ground));

                }
            }
        }
    }


    private void placeStain() {// 추후에 place를 이용해서 공략
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                fields[x][y] = false;
            }
        }

        int len = snake.parts.size();
        for (int i = 0; i < len; i++) {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        int stainX = random.nextInt(WORLD_WIDTH);
        int stainY = random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (fields[stainX][stainY] == false)
                break;
            stainX += 1;
            if (stainX >= WORLD_WIDTH) {
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT) {
                    stainY = 0;
                }
            }
        }
        stain.add(new Stain(stainX, stainY, Stain.types.Obstacle));
    }

    public void update(float deltaTime) {
        if (gameOver)
            return;

        tickTime += deltaTime;
        Log.d("snake y position  : ", "" + snake.parts.get(0).y);
        while (tickTime > tick) {

            cycle++;
            tickTime -= tick;
            snake.running();
//            snake.advance();// 대신에 스테인 advance 코드
            advance_obstacle();
            // 요부분은 유지
            //check_drop();
//check_ground();

            if (snake.state == Snake.status.jump) {
                snake.jump();
            }
//            else if (snake.state == Snake.status.drop) {
//
//            //    snake.dropdown();
//            }

            if (cycle >= 3) {
                //      placeStain();// 여기다가 장애물 설치 하는 코드
                cycle = 0;
            }
            if (snake.checkBitten()) {
                gameOver = true;
                return;
            }

            check_stain();

        }
    }

//    public void check_drop() {
//
//        if (snake.parts.get(0).y > 4) {
//            gameOver = true;
//            return;
//        }
//    }

    public void check_drop() {


        if(snake.state== Snake.status.jump)
            return;
        int posX = snake.parts.get(0).x;
        int posY = snake.parts.get(0).y;
        float groundposY = 11;


        for (int i = 0; i < stain.size(); i++) {

            if (posX == stain.get(i).x && groundposY > stain.get(i).y) {

                groundposY = stain.get(i).y;


            }
        }

        if (groundposY - posY > 1) {

            snake.state = Snake.status.drop;
            //dropdown();
            snake.dropdown();
            //주인공 상태는 dropdown이고 , y++로바뀜 y 1증가
        } else if (groundposY - posY <= 1 && snake.state == Snake.status.drop) {
            snake.state = Snake.status.running;
        }
    }


    public void check_stain() {
        SnakePart head = snake.parts.get(0);
        Log.d("check stain start ", "head x ,y :" + snake.parts.get(0).x + ", " + snake.parts.get(0).y);
        Log.d("stain size is : ", " " + stain.size());
        int len = stain.size();
        for (int i = 0; i < len; i++) {
            Log.d("Stain check : ", " stain number is " + i);
            if (head.x == stain.get(i).x && head.y == stain.get(i).y && stain.get(i).type == Stain.types.Obstacle) {
                gameOver = true;
                return;
            }
        }
    }

    public void check_ground() {


        SnakePart head = snake.parts.get(0);

        Log.d("check stain start ", "head x ,y :" + snake.parts.get(0).x + ", " + snake.parts.get(0).y);
        int len = stain.size();
        float pos_x = snake.parts.get(0).x;
        float pos_y = 10;


        if (snake.state == Snake.status.jump) {


            for (int i = 0; i < stain.size(); i++) {
                if ((stain.get(i).x == head.x)) {
                    if (stain.get(i).y < pos_y)
                        pos_y = stain.get(i).y;
                }
            }
            snake.drop_time = pos_y - head.y;
            snake.state = Snake.status.drop;
        }
    }

    public void advance_obstacle() {
        int len = stain.size();

        for (int i = 0; i < len; i++) {

            stain.get(i).x-=0.1;
//            float x = stain.get(i).x-0.1;
            float y = stain.get(i).y;

            if (stain.get(i).x < 0) {
                stain.get(i).x = WORLD_WIDTH - 1;


            }


        }

    }

    public void init_obstacle() {
        for (int j = 4; j <= 5; j++) {
            fields[WORLD_WIDTH - 1][j] = true;
            stain.add(new Stain(WORLD_WIDTH - 1, j, Stain.types.Obstacle));
        }
    }


}

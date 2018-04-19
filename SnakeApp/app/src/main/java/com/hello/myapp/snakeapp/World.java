package com.hello.myapp.snakeapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class World {// 가로 새로의 길이가 바뀌고 landscape로 변경할 예정 게임 방식은 런닝 방식으로
    static final int WORLD_WIDTH = 15;
    static final int WORLD_HEIGHT = 8;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.1f;
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


        for (int i = 0; i <= WORLD_WIDTH+1; i++) {
            for (int j = 6; j <= 7; j++) {
                if (i != 6 && i!=7 && i!=8) {

                    stain.add(new Stain(i, j, Stain.types.Ground));

                }
            }
        }
    }

    public void update(float deltaTime) {
        if (snake.state== Snake.status.die) {
         gameOver=true;
            return;
        }
        tickTime += deltaTime;
      //  Log.d("snake y position  : ", "" + snake.parts.get(0).y);
        while (tickTime > tick) {

            cycle++;
            tickTime -= tick;
            //snake.running();

        //    crush_check();

            if (snake.state == Snake.status.jump) {
               // Log.d("jump","jump");
                snake.jump();
            }else if (snake.state== Snake.status.drop){
             //   Log.d("drop","drop");

                snake.dropdown();
            }
            advance_obstacle();
            if (cycle >= 2) {
                if(snake.state== Snake.status.running){
                    // Log.d("running","running");

                    snake.running();
                }

                cycle = 0;
            }
            crush_check();

        }
    }


    public  void crush_check(){
        SnakePart head = snake.parts.get(0);

        int len =stain.size();

        for (int i = 0 ; i <len; i++){

            float x1,x2,x3,x4,y1,y2,y3,y4;

            float x =stain.get(i).x;
            float y = stain.get(i).y;
            x1 = (float) (head.x-0.4);
            x2 = (float) (head.x+0.4);

            x3 = (float) (x-0.5);
            x4 = (float) (x+0.5);

            y1 = (float) (head.y-0.5);
            y2 = (float) (head.y+0.5);

            y3 = (float) (y-0.5);
            y4 = (float) (y+0.5);

//            Log.d("x,y values : ",x+", "+y);
                if((((x2<= x4)&&(x3<=x2))&&((y3<=y2)&&(y1<=y3))) ||
                        (((x1<= x4)&&(x1>=x3))&&((y1<=y4)&&(y1>=y3))) ||
                        (((x3<= x2)&&(x2<=x4))&&((y1<=y4)&&(y1>=y3))) ||
                        (((x3<= x1)&&(x1<=x4))&&((y3<=y2)&&(y2<=y4)))){

                if(stain.get(i).type == Stain.types.Obstacle){
                    snake.state= Snake.status.die;
//                    Log.d("Crush Event","Now crush Obstacle no."+i);
                }else if (stain.get(i).type== Stain.types.Ground){
                    Log.d("Crush Event","Now crush ground no. : "+i);

                }
            }

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

            if (stain.get(i).x <=-1) {
                stain.get(i).x = WORLD_WIDTH;


            }


        }

    }

    public void init_obstacle() {
        for (int j = 4; j <= 5; j++) {
            stain.add(new Stain(WORLD_WIDTH - 1, j, Stain.types.Obstacle));
        }
    }
}

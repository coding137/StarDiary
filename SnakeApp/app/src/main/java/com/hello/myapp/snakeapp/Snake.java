package com.hello.myapp.snakeapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
* snake 추후에 업데이트 사항
*
* update 관련 함수를 작성후 FSM을 따로 개별적으로 만들어서 행동을 finite한다.
*
* */

public class Snake {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public float drop_time=0;

   public enum  status {idle, running, jump, die, drop};
    public  status state;
    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;


    public Snake() {
        state = status.drop;
         direction = RIGHT;
         parts.add(new SnakePart(5, 4));
//        parts.add(new SnakePart(5, 7));
//        parts.add(new SnakePart(5, 8));
    }
    public void fsm(){

    }

    public void running(){


        direction -=1;
        if(direction <UP)
            direction=RIGHT;

    }
    
    public void turnLeft() {
        direction += 1;
        if(direction > RIGHT)
            direction = UP;
    }
    
    public void turnRight() {
        direction -= 1;
        if(direction < UP)
            direction = RIGHT;
    }
    
    public void eat() {
        SnakePart end = parts.get(parts.size()-1); 
        parts.add(new SnakePart(end.x, end.y));
    }
    public void jump(){// state를 jump로 변경후 2번 y 값 감소 후에, drop으로 변경


        SnakePart head = parts.get(0);
        if(state!=status.jump)
            return;
        if(state==status.jump) {

            head.y -=1;
            drop_time+=1;

            if(drop_time>=2){
                state=status.drop;
                drop_time=0;

            }

        }
    }
    
    public void advance() {
        SnakePart head = parts.get(0);               
        
        int len = parts.size() - 1;
        for(int i = len; i > 0; i--) {
            SnakePart before = parts.get(i-1);
            SnakePart part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }
        
        if(direction == UP)
            head.y -= 1;
        if(direction == LEFT)
            head.x -= 1;
        if(direction == DOWN)
            head.y += 1;
        if(direction == RIGHT)
            head.x += 1;
        
        if(head.x < 0)
            head.x = 9;
        if(head.x > 9)
            head.x = 0;
        if(head.y < 0)
            head.y = 12;
        if(head.y > 12)
            head.y = 0;
    }

//    public void dropdown(){
//        SnakePart head =parts.get(0);
//
//        if (drop_time>=0 && state==status.drop) {
//            head.y += 1;
//            drop_time-=1;
//        }else {
//            state= status.running;
//        }
//
//
//    }


    public void dropdown(){
        SnakePart head =parts.get(0);
     if(state==status.drop)
         head.y++;
    }
    
    public boolean checkBitten() {// 맞는거 체크로
        int len = parts.size();
        SnakePart head = parts.get(0);
        for(int i = 1; i < len; i++) {
            SnakePart part = parts.get(i);
            if(part.x == head.x && part.y == head.y)
                return true;
        }        
        return false;
    }

}

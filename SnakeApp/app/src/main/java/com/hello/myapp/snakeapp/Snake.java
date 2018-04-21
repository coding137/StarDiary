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
    public float max_height=3;
    public float jump_power= (float) 0.5;
    public static final float Initial_jump_power=0.5f;
    public static final float Initial_drop_power=0.04f;

    public float drop_power=(float) 0.04;
   public enum  status {idle, running, jump, die, drop};
    public  status state;
    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;


    public Snake() {
        state = status.running;
         direction = RIGHT;
         parts.add(new SnakePart(5, 7));
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
    

    

    public void jump(){// state를 jump로 변경후 2번 y 값 감소 후에, drop으로 변경
        SnakePart head = parts.get(0);
        if(state==status.jump){
            if(jump_power<=0){
                state=status.drop;
                jump_power= Initial_jump_power;
               // Log.d("jump Function","now changed status");
            }else{
                head.y -= 1*jump_power;
                jump_power-=0.03;


            }

        }
    }
    public void dropdown(){
        SnakePart head = parts.get(0);
        if(state==status.drop){
           // Log.d("Drop down function","Drops");

            head.y+=1*drop_power;
            drop_power+=0.1;

        //    Log.d("drop function","y axis : "+ head.y);
          //  Log.d("drop function","drop power: "+ drop_power);

            if(head.y>=7){
                head.y=7;
                state=status.running;
                drop_power= Initial_drop_power;
            }
            if(head.y>=9){
                state=status.die;
            }
        }

    }



}

package com.example.seansantarsiero.game_tutorial;

import android.graphics.Point;

public class Velocity {

    private int run;
    private static int direction;


    public Velocity(int run){

        this.run = run;
        this.direction = 0;

    }
    public void setRun(int PositionX,int flag){

        //stop scroll
        if(flag == Constants.STOP){
            run = 0;
        }
        //jump right
        else if(flag == Constants.JUMPRIGHT){
            run = 20;
        }
        //jump left
        else if(flag == Constants.JUMPLEFT){
            run = -20;
        }
        //move right
        else if (PositionX > 900) {
            run = 10;
            direction = Constants.MOVERIGHT;

        }
        //move left
        else if (PositionX < 284){
            run = -10;
            direction = Constants.MOVELEFT;
        }

    }

    public int getRun(){return run;}
    public int getDirection(){return direction;}

    public void setRun(int stall){run = stall;}

}

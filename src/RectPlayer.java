package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.WindowManager;

import java.util.ArrayList;

import static com.example.seansantarsiero.game_tutorial.Constants.GROUND_LEVEL;

public class RectPlayer implements GameObject {

    private Rect rectangle;

    private Bitmap Mario;
    private Bitmap FireMario;
    private Bitmap SuperMario;

    private static int Lives;
    private static int Delay;

    private int PlayerCenter;

    public static int LEVELONE_GROUND = 655;
    public static int Jump = 10;
    public static boolean JLock =false;
    public static boolean GravLock =false;
    public boolean GravLeft;
    public boolean GravRight;

    public Rect getRectangle;

    public boolean Fire;
    public boolean Super;
    private static boolean onPipe;


    public RectPlayer (Rect r, Bitmap mario, Bitmap fireMario, Bitmap fireball, Bitmap superMario){
        this.rectangle = r;
        this.Mario = mario;
        this.FireMario = fireMario;
        this.SuperMario = superMario;
        this.PlayerCenter = rectangle.left + 30;
        this.Fire = false;
        this.Super = false;
        this.Lives = 3;
        this.Delay = 0;
        onPipe = false;
        this.GravRight = false;
        this.GravLeft = false;
    }

    public int getPlayerCenter(){return PlayerCenter;}
    public Rect getRectangle() {
        return rectangle;
    }
    public boolean getJLock(){return JLock;}
    public void setFireMario(boolean fireMario){this.Fire = fireMario;}
    public void setSuperMario(boolean superMario){this.Super = superMario;}
    public boolean getSuper(){return Super;}
    public int getLives(){return Lives;}
    public void resetLives(){ this.Lives = 3;}
    public boolean getFire(){return Fire;}
    public void DecrementLives(){
        Lives--;
        if(Fire){
            Fire = false;
        }
    }

    public void SuperMode(){

        if (Delay == 200){
            Delay = 0;
            Super = false;
        }
        else {
            Delay++;
        }
    }



    public void draw(Canvas canvas, StructHelper helper) {
        Paint paint = new Paint();
        Jump(helper);
        Gravity();


        if(Super){
            canvas.drawBitmap(SuperMario,null,rectangle,null);
            SuperMode();
        }
        else if(Fire){
            canvas.drawBitmap(FireMario,null,rectangle,null);

        }
        else {
            canvas.drawBitmap(Mario, null, rectangle, null);
        }
        //System.out.println("Rect: " + getRectangle.left);
    }


    public void force(int velocity) {

        System.out.println("                                               Velo: " + velocity + "GravRight: " + GravRight);

        if ((!GravRight) && (!GravLeft)) {
            if ((velocity == Constants.MOVERIGHT) && (rectangle.bottom < Constants.GROUND_LEVEL) && (!GravLock) && (!JLock)) {
                GravRight = true;
            }


            else if ((velocity == Constants.MOVELEFT) && (rectangle.bottom < Constants.GROUND_LEVEL) && (!GravLock) && (!JLock)) {
                GravLeft = true;
            }
        }

    }

    void Gravity(){
        if((rectangle.bottom > Constants.GROUND_LEVEL) && ((GravRight) || (GravLeft))){
            GravRight = false;
            GravLeft = false;
            rectangle.top = Constants.GROUND_LEVEL - 60;
            rectangle.bottom = Constants.GROUND_LEVEL;
        }
        else if((GravRight) || (GravLeft) &&(!JLock)){
            rectangle.top += 20;
            rectangle.bottom += 20;
        }
    }


    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    public void update(Point point){

        if (point.y == Constants.JUMP) {
                JLock = true;
                }

        //Gravity();


    }

    public void Jump(StructHelper helper){

        //System.out.println("JLock: " + JLock + ", GravLock: " + GravLock);
        int res = 0;
        if(JLock){

            if (rectangle.top < 400){
                GravLock =true;
            }

            if(!GravLock) {
                rectangle.top -= 15;
                rectangle.bottom -= 15;

            }
            else if(GravLock) {
                res = helper.isOver(this);
                if(rectangle.bottom < res) {
                    rectangle.top += 15;
                    rectangle.bottom += 15;
                }
                else {
                    System.out.println("ON PIPE!!!!!!!");
                    onPipe = true;
                    rectangle.top = res - 60;
                    rectangle.bottom = res;
                    GravLock = false;
                    JLock = false;
                }
            }

           /* if(GravLock)
            {
                res = helper.isOver(this);
                System.out.println("res = " + res);
                if(res == GROUND_LEVEL) {
                    onPipe = false;
                }
                rectangle.bottom = res;
                rectangle.top = rectangle.bottom - 60;
                GravLock = res != rectangle.bottom;
            }*/

        }
    }
}

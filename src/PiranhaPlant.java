package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class PiranhaPlant implements GameObject {

    private Rect rectangle;
    private boolean draw;
    private boolean creep;
    private boolean hit;
    private Bitmap Piranha;
    private static int patrolPath;
    private static int direction;
    private int delay;


    public PiranhaPlant(Rect rectangle,Bitmap piranha){
        this.rectangle = rectangle;
        this.Piranha = piranha;
        this.draw = true;
        this.patrolPath = 0;
        this.hit = true;
        this.delay = 0;
    }

    void PeekAboo(RectPlayer Player){
        int left = rectangle.left - 150;
        int right = rectangle.right + 150;

        if((Player.getRectangle().right > left) ||
        (Player.getRectangle().left > right)){
            System.out.println("Creep");
            creep = true;
        }

    }

    public void delay(){

        if (delay == 50){
            delay = 0;
            hit = true;
        }
        else {
            delay++;
        }
    }

    public void Collision(RectPlayer Player){

        if ((Player.getRectangle().right > rectangle.left) && (Player.getRectangle().left < rectangle.right) && (Player.getRectangle().bottom < 150)) {
            if(Player.getLives() != 0) {
                Player.DecrementLives();
            }

            hit = false;
        }
    }

    void Path(){

        System.out.println("LOOK! " + patrolPath + "  " + creep + "   " + (3*direction));
            if(patrolPath == 0) {
                direction = -1;
            }
            else if (patrolPath == 45) {
                direction = 1;
            }
            else if(patrolPath == 90) {
                direction = -1;
                patrolPath = 0;
                creep = false;
            }


            rectangle.top += (direction);
            rectangle.bottom += (direction);

            patrolPath++;

        }





    @Override
    public void draw(Canvas canvas) {
        if(draw) {
            canvas.drawBitmap(Piranha, null, rectangle, null);
        }
    }

    @Override
    public void update() {

    }


    public void update(RectPlayer player,int velocity) {

        rectangle.right -= 1.5*(velocity);
        rectangle.left -= 1.5*(velocity);

        PeekAboo(player);
        if(creep){
            Path();
        }

        if(hit){
            Collision(player);
        }
        else if (!hit){
            delay();
        }


    }
}

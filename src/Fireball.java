package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Fireball implements GameObject  {

    private Rect Rectangle;
    private Bitmap Fireball;
    private int pathX;
    private int pathY;
    private boolean draw;
    private boolean ready;
    private boolean transit;
    private boolean hit;


    public Fireball(Bitmap fireball){
        this.Fireball = fireball;
        this.pathX = 0;
        this.pathY = 0;
        this.draw =false;
        this.ready = true;

    }

    public Rect getRectangle(){return Rectangle;}
    public Boolean getTransit() {return transit;}
    public int getPathX() { return pathX; }
    public void setHit(boolean hit){this.hit = hit;}


    public void setPathX(int direction) {

            if (direction == Constants.MOVELEFT) {
                pathX = (-25);
            } else if (direction == Constants.MOVERIGHT) {
                pathX = (25);
            }


    }

    public void path(){

        Rectangle.left += pathX;
        Rectangle.right += pathX;

    }


    public void setlock(RectPlayer Player){



        if ((Rectangle.left < Player.getPlayerCenter() - 600)
                || (Rectangle.right > Player.getPlayerCenter() + 600)
                    || (hit)){
            draw = false;
            ready = true;
            transit = false;
            hit = false;


            System.out.println("Backfire " );
        }
    }
    public void shoot(int direction){
        if(ready){
            Rectangle = new Rect((Constants.SCREEN_WIDTH/2) - 20,610,(Constants.SCREEN_WIDTH/2) + 20,630);
            draw = true;
            ready = false;
            setPathX(direction);
            transit = true;

        }

    }

    public void update(RectPlayer Player) {
        System.out.println("ready: " + ready);

        if (!ready) {
            path();
            setlock(Player);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        if(draw) {
            canvas.drawBitmap(Fireball, null, Rectangle, null);
        }
    }

    @Override
    public void update() {

    }
}

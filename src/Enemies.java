package com.example.seansantarsiero.game_tutorial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap;

public class Enemies implements GameObject {

    private Rect rectangle;
    private boolean draw;
    private boolean hit;
    private Bitmap Enemy;
    private static int patrolPath;
    private static int direction;
    private int hitpoint;
    private int delay;


    public Enemies(Rect rect, Bitmap enemy){
        this.rectangle = rect;
        this.Enemy = enemy;
        this.draw = true;
        this.patrolPath = 0;
        this.direction = 1;
        this.hitpoint = rectangle.top + 5;
        this.delay = 0;
        this.hit = true;
        this.direction = -1;

    }



    public Rect getRectangle() { return rectangle; }

    public void delay(){

        if (delay == 50){
            delay = 0;
            hit = true;
        }
        else {
            delay++;
        }
    }

    public void WallCollision(boolean HitWall){

        if (HitWall){
            if(direction == 1) {
                direction = -1;
            }
            else if(direction == -1){
                direction = 1;
            }
        }
    }

    public void PlayerCollision(RectPlayer Player) {

        boolean contact = false;
        if ((Player.getRectangle().right > rectangle.left) && (Player.getRectangle().left < rectangle.right)) {
            contact = true;
        }

        if((Player.getRectangle().bottom > hitpoint) && (Player.getRectangle().bottom < hitpoint + 10) && (contact)) {
            draw = false;
        }
        else if ((contact) && (Player.getRectangle().bottom > rectangle.top) && (hit) && (!Player.getSuper())){
            if(Player.getLives() != 0) {
                Player.DecrementLives();
            }
            hit = false;
        }


    }

    public void FireCollision(Fireball fireball){

        if((fireball.getRectangle().left < rectangle.right) &&
                (fireball.getPathX() < 0)){
            draw = false;
            fireball.setHit(true);
        }
        else  if ((fireball.getRectangle().right > rectangle.left) &&
        (fireball.getPathX() > 0)){
            draw = false;
            fireball.setHit(true);
        }



    }

    public int patrol(){

        if ((direction == 1)){
            patrolPath++;
            return 5;
        }
        else if ((direction == -1)) {
            patrolPath++;
            return -5;
        }

        return 0;

    }
        @Override
    public void update() {

    }
    public void update(RectPlayer player, int velocity, Fireball fireball, boolean HitWall) {
        if(draw) {
            PlayerCollision(player);

            if((player.getFire()) && (fireball.getTransit())){
                FireCollision(fireball);
            }
        }


        WallCollision(HitWall);

        System.out.println("Patrol: " + patrol());
        rectangle.right -= 1.5*(velocity);
        rectangle.left -= 1.5*(velocity);
        System.out.println("Delay: " + delay + hit);
        if(!hit){
            delay();
        }


        rectangle.left += patrol();
        rectangle.right += patrol();
    }

    @Override
    public void draw(Canvas canvas) {
        if(draw){

            canvas.drawBitmap(Enemy,null,rectangle,null);
            //canvas.drawRect(rectangle, new Paint(Color.BLACK));
        }
    }
}

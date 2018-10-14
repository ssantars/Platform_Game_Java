package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Door implements GameObject {
    private Rect rectangle;
    private Bitmap door;

    public Door(Rect rect, Bitmap door){
        this.rectangle = rect;
        this.door = door;
    }


void setDoor (Bitmap door){this.door = door;}
public Rect getRectangle(){return  rectangle; }
public void resetDoor(){
        rectangle.right += rectangle.right;
        rectangle.left += rectangle.left;
}

    @Override
    public void update() {

    }

    public void update(RectPlayer player, int velocity) {

        rectangle.right -= 1.5*velocity;
        rectangle.left -= 1.5*velocity;


    }

    public void draw(Canvas c){
            c.drawBitmap(door,null,rectangle,null);

    }
}

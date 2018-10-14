package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class FireFlower implements GameObject{
    private Rect rectangle;
    private boolean draw;
    private boolean GiveFire;
    private Bitmap fireflower;

    public FireFlower(Rect rect,Bitmap fireflower){
        this.rectangle = rect;
        this.draw = true;
        this.fireflower = fireflower;
        this.GiveFire = true;
    }

    public void collectFireflower(RectPlayer player) {

        if ((player.getPlayerCenter() > rectangle.left) && (player.getPlayerCenter() < rectangle.right) && (GiveFire)) {
            draw = false;
            GiveFire = false;
            player.setFireMario(true);
        }
    }

    public void update(RectPlayer player, int velocity) {
        collectFireflower(player);
        rectangle.right -= 1.5*velocity;
        rectangle.left -= 1.5*velocity;

        }

    public void draw(Canvas c){
        if(draw){
            c.drawBitmap(fireflower,null,rectangle,null);
        }
    }

    @Override
    public void update() {

    }
}

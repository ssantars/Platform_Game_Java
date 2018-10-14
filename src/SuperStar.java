package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SuperStar implements GameObject{
    private Rect rectangle;
    private boolean draw;
    private boolean GiveStar;
    private Bitmap Star;

    public SuperStar(Rect rect,Bitmap star){
        this.rectangle = rect;
        this.draw = true;
        this.Star = star;
        this.GiveStar = true;
    }

    public void collectStar(RectPlayer player) {

        if ((player.getPlayerCenter() > rectangle.left) && (player.getPlayerCenter() < rectangle.right) && (GiveStar)) {
            draw = false;
            GiveStar = false;
            player.setSuperMario(true);
        }
    }

    public void update(RectPlayer player, int velocity) {
        collectStar(player);
        rectangle.right -= 1.5*velocity;
        rectangle.left -= 1.5*velocity;

    }

    public void draw(Canvas c){
        if(draw){
            c.drawBitmap(Star,null,rectangle,null);
        }
    }

    @Override
    public void update() {

    }
}

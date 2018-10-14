package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class FollowerEnemy extends Enemies
{
    private Rect rectangle;
    private boolean draw;
    private boolean hit;
    private Bitmap b;
    private static int height;
    private static int patrolPath;
    private static int direction;
    private int hitpoint;
    private int delay;

    public FollowerEnemy(Rect rect, Bitmap b)
    {
        super(rect, b);
        this.rectangle = rect;
        this.draw =true;
        this.b = b;
        //this.rectangle.top -= height;
        //this.rectangle.bottom -= height;
    }

    private float dist(RectPlayer player)
    {
        Rect prect = player.getRectangle();
        float pcenterx = (prect.right + prect.left)/2;
        float pcentery = (prect.bottom + prect.top)/2;
        float tcenterx = (rectangle.right + rectangle.left)/2;
        float tcentery = (rectangle.bottom + rectangle.top)/2;

        return (float)Math.sqrt((pcenterx - tcenterx)*(pcenterx-tcenterx) + (pcentery - tcentery)*(pcentery - tcentery));
    }

    public int patrol(RectPlayer player){
        float d = dist(player);
        if(d < 1000)
        {
            if(d < 100)
                return 0;
            return (rectangle.left - player.getRectangle().left > 0 ? -1 : 1) * 3;
        }
        else
        {
            return 0;
        }
    }


    public void update(RectPlayer player, int velocity, Fireball fireball, boolean hitwall) {
        if(draw) {
            PlayerCollision(player);

            if((player.getFire()) && (fireball.getTransit())){
                FireCollision(fireball);
            }
        }
        int pat = patrol(player);
        System.out.println("Patrol: " + pat);
        rectangle.right -= (1.5*(velocity)-pat);
        rectangle.left -= (1.5*(velocity)-pat);
        System.out.println("Delay: " + delay);
        if(!hit){
            delay();
        }
    }

    public void draw(Canvas canvas) {
        if(draw){
            canvas.drawBitmap(b,null,rectangle,null);
        }
    }
}

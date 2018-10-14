package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Structure implements Interactable
{
    private Rect hitbox;
    private final float x_anchor;
    private final int width;
    private final int height;
    private final int color;
    private Bitmap Pipe;

    public Structure(float x_anchor, int width, int height, int color, Bitmap Pipe)
    {
        //TODO: set ground to actual ground level
        hitbox = new Rect();
        this.width = width;
        this.height = height;
        this.x_anchor = x_anchor;
        this.hitbox.bottom = Constants.GROUND_LEVEL;
        this.hitbox.top = Constants.GROUND_LEVEL -  this.height;
        this.hitbox.left = (int)x_anchor;
        this.hitbox.right = (int)x_anchor + this.width;
        this.color = color;
        this.Pipe = Pipe;


    }

    public void update(float vel_x)
    {
        //TODO: set ground to actual ground level

        this.hitbox.left -= 1.5*vel_x;
        this.hitbox.right -= 1.5*vel_x;

    }


    public void draw(Canvas c)
    {
        Paint p = new Paint();
        p.setColor(color);
        c.drawRect(this.hitbox, p);
        c.drawBitmap(Pipe, null, hitbox, null);
    }



    public int getColor()
    {
        return this.color;
    }

    public byte isHit(RectPlayer player)
    {
        Rect p = player.getRectangle();
        if((hitbox.left > p.left && hitbox.left < p.right) || (hitbox.right > p.left && hitbox.right < p.right))
        {
            if((p.bottom >= (this.hitbox.bottom)))
            {
                return (hitbox.left - 560 > 0 ? (byte)1 : (byte)2);
            }
        }
        return 0;
    }

    public Rect getHitbox()
    {
        return this.hitbox;
    }
    
    public int isOver(RectPlayer player)
    {
        Rect p = player.getRectangle();
        if((hitbox.left > p.left && hitbox.left < p.right) || (hitbox.right > p.left && hitbox.right < p.right))
        {
            return hitbox.top;
        }
        return -1;
    }
}

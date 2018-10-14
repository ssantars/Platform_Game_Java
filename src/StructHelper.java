package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;

import static com.example.seansantarsiero.game_tutorial.Constants.GROUND_LEVEL;

public class StructHelper
{
    private ArrayList<Structure> structures;

    public StructHelper(int level, Bitmap Pipe)
    {
        this.structures = new ArrayList<Structure>();
        switch(level)
        {
            case 1:
            {
                this.structures.add(new Structure(200, 50, 50, Color.BLACK,Pipe));
                this.structures.add(new Structure(600, 50, 50, Color.WHITE, Pipe));
                this.structures.add(new Structure(1200, 50, 150, Color.BLUE,Pipe));
                this.structures.add(new Structure(2200, 50, 150, Color.CYAN,Pipe));
                this.structures.add(new Structure(3000, 50, 150, Color.DKGRAY,Pipe));
                this.structures.add(new Structure(4700, 50, 150, Color.GRAY,Pipe));
                this.structures.add(new Structure(4400, 50, 150, Color.GREEN,Pipe));
                this.structures.add(new Structure(5000, 50, 150, Color.MAGENTA,Pipe));
                this.structures.add(new Structure(5500, 50, 150, Color.RED,Pipe));
                this.structures.add(new Structure(6000, 50, 150, Color.YELLOW,Pipe));

                break;
            }
            case 2:
            {
                this.structures.add(new Structure(300, 50, 50, Color.BLACK,Pipe));
                this.structures.add(new Structure(500, 50, 50, Color.WHITE, Pipe));
                this.structures.add(new Structure(900, 50, 150, Color.BLUE,Pipe));
                this.structures.add(new Structure(1500, 50, 150, Color.CYAN,Pipe));
                this.structures.add(new Structure(3000, 50, 150, Color.DKGRAY,Pipe));
                this.structures.add(new Structure(4900, 50, 150, Color.GRAY,Pipe));
                this.structures.add(new Structure(4400, 50, 150, Color.GREEN,Pipe));
                this.structures.add(new Structure(5100, 50, 150, Color.MAGENTA,Pipe));
                this.structures.add(new Structure(5500, 50, 150, Color.RED,Pipe));
                this.structures.add(new Structure(6200, 50, 150, Color.YELLOW,Pipe));
                break;
            }
            case 3:
            {
                this.structures.add(new Structure(100, 50, 50, Color.BLACK,Pipe));
                this.structures.add(new Structure(500, 50, 50, Color.WHITE, Pipe));
                this.structures.add(new Structure(900, 50, 150, Color.BLUE,Pipe));
                this.structures.add(new Structure(1700, 50, 150, Color.CYAN,Pipe));
                this.structures.add(new Structure(2200, 50, 150, Color.DKGRAY,Pipe));
                this.structures.add(new Structure(4900, 50, 150, Color.GRAY,Pipe));
                this.structures.add(new Structure(4400, 50, 150, Color.GREEN,Pipe));
                this.structures.add(new Structure(5100, 50, 150, Color.MAGENTA,Pipe));
                this.structures.add(new Structure(5500, 50, 150, Color.RED,Pipe));
                this.structures.add(new Structure(6000, 50, 150, Color.YELLOW,Pipe));
                break;
            }
        }
    }

    public void update(float vel_x)
    {

        for(Structure s : structures)
        {
            s.update(vel_x);
        }
    }

    public void draw(Canvas c)
    {
        for(Structure s : structures)
        {
            s.draw(c);
        }
    }

    public byte isHit(RectPlayer player)
    {
        //System.out.println("Hit Helper");
        byte res;
        for(Structure s : structures) {
            res = s.isHit(player);
            if(res != 0)
            {
                return res;
            }
        }
        return 0;
    }

    public boolean isHit(Enemies enemy)
    {
            Rect r, e;
            for(Structure s : this.structures)
            {
                r = s.getHitbox();
                e = enemy.getRectangle();
                if((r.left > e.left && r.left < e.right) || (r.right > e.left && r.right < e.right))
                {
                    System.out.printf("HIT!\n");
                    return true;
                }
            }
            return false;
        }

    public int isOver(RectPlayer player)
    {
        int res;
        for(Structure s : structures)
        {
            res = s.isOver(player);
            if(res > 0)
                return res;
        }
        return GROUND_LEVEL;
    }
    
    }

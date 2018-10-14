package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;

import static com.example.seansantarsiero.game_tutorial.Constants.GROUND_LEVEL;

public class BreakBoxHelper {
    private ArrayList<BreakBox> BreakBoxes;

    public BreakBoxHelper(Bitmap Block, Bitmap Coin, int level)
    {
        this.BreakBoxes = new ArrayList<BreakBox>();
        switch(level)
        {
            case 1:
            {
                this.BreakBoxes.add(new BreakBox(new Rect((1150),470,1180,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((1770),470,1810,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((2770),470,2810,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((4070),470,4110,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((4570),470,4610,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((5170),470,5210,510),Block,Coin));
                break;
            }
                
            case 2:
            {
                this.BreakBoxes.add(new BreakBox(new Rect((550),470,580,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((1170),470,1210,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((2800),470,2840,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((4090),470,4130,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((4570),470,4610,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((5170),470,5210,510),Block,Coin));
                break;
            }
                
            case 3:
            {
                this.BreakBoxes.add(new BreakBox(new Rect((1550),470,1580,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((1870),470,1910,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((2800),470,2840,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((4090),470,4130,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((4570),470,4610,510),Block,Coin));
                this.BreakBoxes.add(new BreakBox(new Rect((5170),470,5210,510),Block,Coin));
                break;
            }
        }
    }


    public void draw(Canvas canvas) {

        for(BreakBox s : BreakBoxes) {

            s.draw(canvas);
        }

    }

    public void update(RectPlayer player, int velocity) {

        for(BreakBox s : BreakBoxes) {

            s.update(player,velocity);
        }

    }

}

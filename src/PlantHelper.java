package com.example.seansantarsiero.game_tutorial;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class PlantHelper {

    ArrayList<PiranhaPlant> PlantEnemies;

    public PlantHelper(byte level)
    {
        this.PlantEnemies = new ArrayList<PiranhaPlant>();
        switch(level)
        {
            case 1:
            {
                this.PlantEnemies.add(new PiranhaPlant(new Rect((1210),Constants.GROUND_LEVEL-150,(1240),Constants.GROUND_LEVEL-105),GamePanel.Piranha));
                this.PlantEnemies.add(new PiranhaPlant(new Rect((2210),Constants.GROUND_LEVEL-150,(2240),Constants.GROUND_LEVEL-105),GamePanel.Piranha));
                this.PlantEnemies.add(new PiranhaPlant(new Rect((3010),Constants.GROUND_LEVEL-150,(3040),Constants.GROUND_LEVEL-105),GamePanel.Piranha));
                this.PlantEnemies.add(new PiranhaPlant(new Rect((4410),Constants.GROUND_LEVEL-150,(4440),Constants.GROUND_LEVEL-105),GamePanel.Piranha));
                this.PlantEnemies.add(new PiranhaPlant(new Rect((5510),Constants.GROUND_LEVEL-150,(5540),Constants.GROUND_LEVEL-105),GamePanel.Piranha));
            }
        }
    }


    public void update(RectPlayer player, int velocity)
    {
        for(PiranhaPlant e : PlantEnemies)
        {
            e.update(player, velocity);
        }
    }

    public void draw(Canvas canvas)
    {
        for(PiranhaPlant e : PlantEnemies)
        {
            e.draw(canvas);
        }
    }
}

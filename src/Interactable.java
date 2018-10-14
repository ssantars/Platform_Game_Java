package com.example.seansantarsiero.game_tutorial;

import android.graphics.Canvas;

enum HitResult{
    clear,
    hit_obstacle,
    hit_enemy
};

public interface Interactable
{
    public void draw(Canvas c);
    public void update(float vel_x);
   // public HitResult isHit(RectPlayer player);
}
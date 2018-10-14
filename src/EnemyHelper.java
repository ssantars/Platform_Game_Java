package com.example.seansantarsiero.game_tutorial;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class EnemyHelper
{
    ArrayList<Enemies> enemies;

    public EnemyHelper(byte level)
    {
        this.enemies = new ArrayList<Enemies>();
        switch(level)
        {
            case 1:
            {
                this.enemies.add(new Enemies(new Rect((1000) - 23,600,(1000) + 23,655), GamePanel.Goomba));
                this.enemies.add(new Enemies(new Rect((2000) - 23 ,600,(2000) + 23,655), GamePanel.Goomba));
                this.enemies.add(new Enemies(new Rect((4000) - 23 ,600,(4000) + 23,655), GamePanel.Goomba));
                this.enemies.add(new FollowerEnemy(new Rect((3000) - 23 ,300,(3000) + 23,355), GamePanel.Koopa));
                this.enemies.add(new FollowerEnemy(new Rect((6000) - 23 ,300,(6000) + 23,355), GamePanel.Koopa));
                break;
            }
            case 2:
            {
                this.enemies.add(new Enemies(new Rect((5000) - 23,600,(5000) + 23,655), GamePanel.Koopa));
                this.enemies.add(new Enemies(new Rect((6000) - 23 ,600,(6000) + 23,655), GamePanel.Goomba));
                this.enemies.add(new Enemies(new Rect((4000) - 23 ,600,(4000) + 23,655), GamePanel.Koopa));
                this.enemies.add(new FollowerEnemy(new Rect((1000) - 23 ,300,(1000) + 23,355), GamePanel.Goomba));
                this.enemies.add(new FollowerEnemy(new Rect((2000) - 23 ,300,(2000) + 23,355), GamePanel.Koopa));
                break;
            }
            case 3:
            {
                this.enemies.add(new Enemies(new Rect((5000) - 23,600,(5000) + 23,655), GamePanel.Goomba));
                this.enemies.add(new Enemies(new Rect((6000) - 23 ,600,(6000) + 23,655), GamePanel.Koopa));
                this.enemies.add(new Enemies(new Rect((4000) - 23 ,600,(4000) + 23,655), GamePanel.Goomba));
                this.enemies.add(new FollowerEnemy(new Rect((1500) - 23 ,300,(1500) + 23,355), GamePanel.Koopa));
                this.enemies.add(new FollowerEnemy(new Rect((2000) - 23 ,300,(2000) + 23,355), GamePanel.Koopa));
                this.enemies.add(new FollowerEnemy(new Rect((4000) - 23 ,300,(4000) + 23,355), GamePanel.Goomba));
                break;
            }
        }
    }

    public void delay()
    {
        for(Enemies e: enemies)
        {
            e.delay();
        }
    }

    public void update(RectPlayer player, int velocity, Fireball fireball, StructHelper helper)
    {
        for(Enemies e : enemies)
        {
            e.update(player, velocity, fireball, helper.isHit(e));
        }
    }

    public void draw(Canvas canvas)
    {
        for(Enemies e : enemies)
        {
            e.draw(canvas);
        }
    }
}

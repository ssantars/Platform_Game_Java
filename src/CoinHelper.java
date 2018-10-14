package com.example.seansantarsiero.game_tutorial;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class CoinHelper
{
    private ArrayList<Coin> coins;

    public CoinHelper(byte level)
    {
        coins = new ArrayList<Coin>();
        switch(level)
        {
            case 1:
            {
                coins.add(new Coin(new Rect((1000) - 23,600,(1000) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((1500) - 23,600,(1500) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((3000) - 23,600,(3000) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((3500) - 23,600,(3500) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((4000) - 23,600,(4000) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5000) - 23,600,(5000) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5500) - 23,600,(5500) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((6000) - 23,600,(6000) + 23,630) , GamePanel.Coin));
                break;
            }
            case 2:
            {
                coins.add(new Coin(new Rect((1200) - 23,600,(1200) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((1600) - 23,600,(1600) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((2900) - 23,600,(2900) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((3200) - 23,600,(3200) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((4100) - 23,600,(4100) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5000) - 23,600,(5000) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5500) - 23,600,(5500) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((6000) - 23,600,(6000) + 23,630) , GamePanel.Coin));
                break;
            }
            case 3:
            {
                coins.add(new Coin(new Rect((900) - 23,600,(900) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((1200) - 23,600,(1200) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((2900) - 23,600,(2900) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((3200) - 23,600,(3200) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((4100) - 23,600,(4100) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5000) - 23,600,(5000) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5500) - 23,600,(5500) + 23,630) , GamePanel.Coin));
                coins.add(new Coin(new Rect((5900) - 23,600,(5900) + 23,630) , GamePanel.Coin));
                break;
            }
        }
    }

    public void collectCoin(RectPlayer player)
    {
        for(Coin c : coins)
        {
            c.collectCoin(player);
        }
    }

    public void update(RectPlayer player, int velocity)
    {
        for(Coin c : coins)
        {
            c.update(player, velocity);
        }
    }

    public void draw(Canvas c)
    {
        for(Coin coin : coins)
        {
            coin.draw(c);
        }
    }
}

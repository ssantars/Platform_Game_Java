package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

public class Coin implements GameObject {

    private int value;
    private Rect rectangle;
    private boolean draw;
    private Bitmap coin;
    private boolean GiveCoin;

    public Coin(Rect rect,Bitmap coin){
        this.rectangle = rect;
        this.value = 1;
        this.draw = true;
        this.coin = coin;
        this.GiveCoin = true;

    }

    public int getValue(){return value;}


    public void collectCoin(RectPlayer Player){

        if((Player.getPlayerCenter() > rectangle.left) && (Player.getPlayerCenter() < rectangle.right) && (Player.getRectangle().bottom > rectangle.top)){
            draw = false;
            if(GiveCoin) {
                Constants.CoinCount++;
            }
            GiveCoin = false;

        }


    }

    @Override
    public void update() {

    }

    public void update(RectPlayer player, int velocity) {
        collectCoin(player);
        rectangle.right -= 1.5*velocity;
        rectangle.left -= 1.5*velocity;


    }

    public void draw(Canvas c){
        if(draw){
            c.drawBitmap(coin,null,rectangle,null);
        }
    }
}

package com.example.seansantarsiero.game_tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class BreakBox implements GameObject {

    private Rect Rectangle;
    private Bitmap Block;
    private Bitmap Coin;
    private final int Stop;
    private boolean draw;
    private boolean drawCoin;
    private boolean GiveCoin;

    public boolean getdraw(){return draw;}
    public Rect getRectangle(){return Rectangle;}
    public Bitmap getBlock() { return Block;}
    public Bitmap getCoin(){return Coin;}

    public BreakBox(Rect rectangle, Bitmap block, Bitmap coin){

        this.Rectangle = rectangle;
        this.Block = block;
        this.Coin = coin;
        this.draw = true;
        this.drawCoin = false;
        this.GiveCoin = true;
        this.Stop = rectangle.top;
    }

    void CoinAnimation() {

        if (Rectangle.top < (Stop - 50)){
            drawCoin = false;
        }
        Rectangle.bottom += -3;
        Rectangle.top += -3;

    }

    void PlayerCollision(RectPlayer Player){

        Rect P_Rect = Player.getRectangle();
        if((P_Rect.top < Rectangle.bottom)
            && (P_Rect.left > (Rectangle.left - 40)) && (P_Rect.right < (Rectangle.right + 40))){
            draw = false;
            drawCoin = true;
            if(GiveCoin) {
                Constants.CoinCount++;
                GiveCoin = false;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(draw){
            canvas.drawBitmap(Block,null,Rectangle,null);
        }
        else if((!draw) && (drawCoin)){

            canvas.drawBitmap(Coin, null,Rectangle,null );
        }

    }

    public void update(RectPlayer player, int velocity) {

        Rectangle.right -= 1.5*velocity;
        Rectangle.left -= 1.5*velocity;

        PlayerCollision(player);
        if((!draw) && (drawCoin)){
            CoinAnimation();
        }


    }

    @Override
    public void update() {

    }
}

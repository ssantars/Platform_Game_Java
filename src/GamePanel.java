package com.example.seansantarsiero.game_tutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private RectPlayer player;
    private Coin coin;
    private FireFlower flower;
    private SuperStar star;
    private Point playerPoint;
    private Bitmap Level;
    private Bitmap LevelOne;
    private Bitmap LevelTwo;
    private Bitmap LevelThree;
    private Velocity velocity;
    private PiranhaPlant e3;
    private Door door;


    private BreakBoxHelper Boxhelper;
    private EnemyHelper enemyHelper;
    private PlantHelper plantHelper;
    private CoinHelper coinHelper;

    public static Bitmap Mario;
    public static Bitmap Coin;
    public static Bitmap FirePlant;
    public static Bitmap Poorio;
    public static Bitmap SuperMario;
    public static Bitmap Fire;
    public static Bitmap Heart;
    public static Bitmap Enemy;
    public static Bitmap Star;
    public static Bitmap Pipe;
    public static Bitmap Piranha;
    public static Bitmap box;
    public static Bitmap Goomba;
    public static Bitmap Koopa;
    public static Bitmap Door;
    public static Bitmap Door2;


    private StructHelper helper;
    float start_x = 0f,start_y = 0f;
    private Rect dst,src;
    private Paint ptext,gameover;
    private Fireball fireball;

    private static boolean shoot;




    public GamePanel(Context context) {
        super(context);
        velocity = new Velocity(0);
        dst = new Rect(1,0,1184,720);
        src = new Rect(1,0,781,474);
        //playerPoint set to be the center of rectangle
        Mario = BitmapFactory.decodeResource(getResources(), R.drawable.d);
        Coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        FirePlant = BitmapFactory.decodeResource(getResources(), R.drawable.fireflower);
        Poorio = BitmapFactory.decodeResource(getResources(), R.drawable.poorio);
        Fire = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
        Heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        Enemy = BitmapFactory.decodeResource(getResources(), R.drawable.poorio);
        Star = BitmapFactory.decodeResource(getResources(), R.drawable.star);
        SuperMario = BitmapFactory.decodeResource(getResources(), R.drawable.supermario);
        Pipe = BitmapFactory.decodeResource(getResources(), R.drawable.pipe);
        Piranha = BitmapFactory.decodeResource(getResources(), R.drawable.piranha);
        box = BitmapFactory.decodeResource(getResources(), R.drawable.mario_bg);
        Goomba = BitmapFactory.decodeResource(getResources(), R.drawable.goom);
        Koopa = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_1);
        Door = BitmapFactory.decodeResource(getResources(), R.drawable.door);
        Door2 = BitmapFactory.decodeResource(getResources(), R.drawable.door2);

        player = new RectPlayer(new Rect((Constants.SCREEN_WIDTH/2) - 35,598,(Constants.SCREEN_WIDTH/2) + 35,658),Mario,Poorio,Fire,SuperMario);
        playerPoint = new Point(60,607);




        Boxhelper = new BreakBoxHelper(box,Coin,1);
        enemyHelper = new EnemyHelper((byte)1);
        plantHelper = new PlantHelper((byte)1);
        coinHelper = new CoinHelper((byte)1);
        helper = new StructHelper(1,Pipe);



        coin = new Coin(new Rect((1000) - 23,600,(1000) + 23,630),Coin);
        e3 = new PiranhaPlant(new Rect((1210),Constants.GROUND_LEVEL-150,(1240),Constants.GROUND_LEVEL-105),Piranha);
        flower = new FireFlower(new Rect((1500) - 25,600,(1500) + 25,630),FirePlant);
        star = new SuperStar(new Rect((2500) - 25,600,(2500) + 25,630),Star);
        door = new Door(new Rect(7810-55,Constants.GROUND_LEVEL-100,7810+55,Constants.GROUND_LEVEL), Door);
        fireball = new Fireball(Fire);

        LevelOne = BitmapFactory.decodeResource(getResources(), R.drawable.levelone);

        shoot = new Boolean(false);
        ptext = new Paint();
        gameover = new Paint();
        ptext.setColor(Color.BLACK);
        ptext.setTextSize(36);
        gameover.setColor(Color.BLACK);
        gameover.setTextSize(256);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
                retry = false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                final float x = event.getRawX();
                final float y = event.getRawY();

                start_x = x;
                start_y = y;

                if((player.getFire()) && (x > 600)) {
                    fireball.shoot(Constants.MOVERIGHT);
                }
                else if (player.getFire() && (x < 600)){
                    fireball.shoot(Constants.MOVELEFT);
                }
                System.out.println("X is: " + start_x + ", Y is: " + start_y);

            }
            case MotionEvent.ACTION_MOVE: {

                //test if movement was Jump Right

                if (((int) event.getY() < 400) && ((int) event.getX() > 594) && ((int) event.getX() < 900)) {
                    playerPoint.set((int) event.getX(),Constants.JUMP);
                    velocity.setRun(playerPoint.x, Constants.JUMPRIGHT);
                    break;
                }
                //test if movement was Jump Left
                else if (((int) event.getY() < 400) && ((int) event.getX() > 284) && ((int) event.getX() < 594)) {
                    playerPoint.set((int) event.getX(),Constants.JUMP);
                    velocity.setRun(playerPoint.x, Constants.JUMPLEFT);
                    break;
                }
                //test if moment was LEFT or Right Run movement
                else if((int) event.getX() < 284){
                    velocity.setRun(playerPoint.x, 0);
                    player.force(velocity.getDirection());
                }
                else {
                    velocity.setRun(playerPoint.x, 0);
                    player.force(velocity.getDirection());
                }



                break;
            }
            case MotionEvent.ACTION_UP: {

                //Stops Scroll BackGround
                shoot = true;

                playerPoint.set((int) event.getX(),(int) event.getY());
                velocity.setRun(playerPoint.x, Constants.STOP);

                break;
            }

        }

        return true;
    }

    public void update(){

        System.out.println("Velo: " + velocity.getRun());
        System.out.println("Lives: " + player.getLives());

        if(player.getLives() != 0) {
            byte res = helper.isHit(player);
            int prev = velocity.getRun();

            if (prev < 0 && res == 2) {
                velocity.setRun(0);
            } else if (prev > 0 && res == 1) {
                velocity.setRun(0);
            }


            player.update(playerPoint);
            if ((src.left < 10) && (velocity.getRun() < 0)) {
                velocity.setRun(0);
            } else if ((src.right > 5590) && (velocity.getRun() > 0)) {
                velocity.setRun(0);
            }
            helper.update(velocity.getRun());
            coin.update(player, velocity.getRun());
            flower.update(player, velocity.getRun());
            Boxhelper.update(player, velocity.getRun());
            plantHelper.update(player, velocity.getRun());
            coinHelper.update(player, velocity.getRun());
            enemyHelper.update(player, velocity.getRun(), fireball, helper);
            star.update(player, velocity.getRun());
            door.update(player, velocity.getRun());

            if (player.getFire()) {
                fireball.update(player);
            }
        }



    }

    public void drawLives(Canvas c){
        if(player.getLives() == 3){
            c.drawBitmap(Heart,null,new Rect(870,75,900,105),null );
            c.drawBitmap(Heart,null,new Rect(900,75,930,105),null );
            c.drawBitmap(Heart,null,new Rect(930,75,960,105),null );
        }
        else if (player.getLives() == 2) {
            c.drawBitmap(Heart,null,new Rect(870,75,900,105),null );
            c.drawBitmap(Heart,null,new Rect(900,75,930,105),null );
        }
        else if (player.getLives() == 1) {
            c.drawBitmap(Heart,null,new Rect(870,75,900,105),null );
        }
        else if(player.getLives() == 0){
            c.drawText(("GAME OVER!"), Constants.SCREEN_WIDTH/2-50, Constants.SCREEN_HEIGHT/2, ptext);
            player.getRectangle().top += 10;
            player.getRectangle().bottom += 10;
        }

    }

    public void ScollBackgound(){

    /*first if allows simple movement between ends of screen
        first else if allows user to move left if they have reach right end of lvl
            second else if allows user to move right if they reach left end of lvl
     */


        if(player.getLives() != 0) {
            if (Level == null) {
                Level = LevelOne;
            }

            if (src.left < 0) {
                src.left = 0;
                playerPoint.set(Constants.STOP, player.getRectangle().top);
                velocity.setRun(playerPoint.x, 0);
            } else if (src.right > 5600) {
                System.out.println("Inside");
                src.left = 1;
                src.top = 0;
                src.right = 781;
                src.bottom = 474;

                if (Level == LevelOne) {
                    LevelOne = null;
                    LevelTwo = BitmapFactory.decodeResource(getResources(), R.drawable.level_2);
                    helper = new StructHelper(2, Pipe);
                    enemyHelper = new EnemyHelper((byte) 2);
                    coinHelper = new CoinHelper((byte) 2);
                    Boxhelper = new BreakBoxHelper(box, Coin, 2);
                    Level = LevelTwo;
                } else if (Level == LevelTwo) {

                    LevelTwo = null;
                    LevelThree = BitmapFactory.decodeResource(getResources(), R.drawable.level_3);
                    helper = new StructHelper(3, Pipe);
                    enemyHelper = new EnemyHelper((byte) 3);
                    coinHelper = new CoinHelper((byte) 3);
                    Boxhelper = new BreakBoxHelper(box, Coin, 3);
                    Level = LevelThree;
                } else if (Level == LevelThree) {
                    playerPoint.set(Constants.STOP, player.getRectangle().top);
                    velocity.setRun(playerPoint.x, 0);
                }

                //playerPoint.set(Constants.STOP, player.getRectangle().top);
                //velocity.setRun(playerPoint.x, 0);
            } else if ((src.left > 0) && (src.right < 6600)) {
                src.left += velocity.getRun();
                src.right += velocity.getRun();
           /* } else if ((src.right == 6600) && (velocity.getRun() < 0)) {
                src.left += velocity.getRun();
                src.right += velocity.getRun();
                */
            } else if ((src.left == 0) && (velocity.getRun() > 0)) {
                src.left += velocity.getRun();
                src.right += velocity.getRun();
            }
        }
        /* Next set of else statements sets velocity to 0 if end of level has be reached
            and sets left or right most source to either end of screen stopping  background scrolling
         */




    }

    public String Score(){

        return Integer.toString(200 * Constants.CoinCount);

    }

    @Override
    public void draw(Canvas c){
        super.draw(c);

        ScollBackgound();

        System.out.println("SRC: " + src.right);

            c.drawBitmap(Level, src, dst, null);
            c.drawText("Score: " + Score(), 200, 100, ptext);
            c.drawText(("X" + Integer.toString(Constants.CoinCount)), 500, 100, ptext);
            c.drawBitmap(Coin, null, new Rect(470, 75, 500, 105), null);


            //System.out.println("SRC-RIGHT: " + src.right);
            if (player.getFire()) {
                shoot = false;
                fireball.draw(c);
            }

            door.draw(c);
            player.draw(c, helper);


            coin.draw(c);
            Boxhelper.draw(c);
            enemyHelper.draw(c);
            plantHelper.draw(c);
            coinHelper.draw(c);
            helper.draw(c);
            flower.draw(c);
            star.draw(c);

            drawLives(c);

    }




}

package com.example.seansantarsiero.game_tutorial;

import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Makes screen horizontal
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //gives device Width and Lenght if particular device
        Display display = getWindowManager().getDefaultDisplay();
        Constants.SCREEN_WIDTH = display.getWidth();
        Constants.SCREEN_HEIGHT = display.getHeight();
        System.out.println("Screen Height: " + Constants.SCREEN_HEIGHT + " Screen Width: " + Constants.SCREEN_WIDTH);
        setContentView(new GamePanel(this));
    }
}

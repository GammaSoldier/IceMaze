// TODO: 15.09.2018: player is not displayed on teleport tile


package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    Rules theRules;
    GameView theGridBitmap;
    RelativeLayout surface;
    Level theLevel;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        theGridBitmap = new GameView(this);

        surface = (RelativeLayout)findViewById(R.id.imageView);
        surface.addView(theGridBitmap);

        Intent anIntent = getIntent();
        theLevel = (Level)anIntent.getSerializableExtra("Level");

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startGame();
            }// onClick
        });


        Button buttonEditor = (Button) findViewById(R.id.buttonEditor);
        buttonEditor.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), EditorActivity.class);
                startActivity(myIntent);
            }// onClick
        });



        View contentView = (View) findViewById(R.id.mainLayout);

        contentView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.NORTH);
            }

            public void onSwipeRight() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.EAST);
            }

            public void onSwipeLeft() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.WEST);
            }

            public void onSwipeBottom() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.SOUTH);
            }

        });


    }// onCreate


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void startGame() {
        theRules = new Rules();
        theRules.initLevel(theLevel);
        render(true);
    }// startGame


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeMove(Direction direction) {
        MoveResult moveResult;

        moveResult = theRules.move(direction, true);
        render(false);

        while(moveResult == MoveResult.CONTINUE ) {
            moveResult = theRules.move(direction, false);
            render(false);
        }

        switch( moveResult) {
            case PLAYER_STOP:
                break;
            case LEVEL_COMPLETE:
                // TODO: 12.09.2018: start the next level
                break;
            case LEVEL_RESTART:
                // TODO: 12.09.2018: pass the same level
                theRules.initLevel(theLevel);
                render(true);
                break;

        }// switch
    }// makeMove


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render(boolean full) {

        theGridBitmap.render(this, theRules.getTheMap(), theRules.getThePlayer(), full);
    }// render


}

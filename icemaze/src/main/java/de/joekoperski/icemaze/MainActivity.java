package de.joekoperski.icemaze;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    Rules theRules;
    GameView theGridBitmap;
    RelativeLayout surface;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        theGridBitmap = new GameView(this);

        surface = (RelativeLayout)findViewById(R.id.imageView);
        surface.addView(theGridBitmap);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                startGame();
            }// onClick
        });

        View contentView = (View) findViewById(R.id.mainLayout);
        contentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        contentView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.NORTH);
//                Toast.makeText(MainActivity.this, "top: " + (System.currentTimeMillis() - startTime) + "ms", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.EAST);
//                Toast.makeText(MainActivity.this, "right: " + (System.currentTimeMillis() - startTime) + "ms", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.WEST);
//                Toast.makeText(MainActivity.this, "left: " + (System.currentTimeMillis() - startTime) + "ms", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                long startTime = System.currentTimeMillis();
                makeMove(Direction.SOUTH);
//                Toast.makeText(MainActivity.this, "bottom: " + (System.currentTimeMillis() - startTime) + "ms", Toast.LENGTH_SHORT).show();
            }

        });

    }// onCreate


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void startGame() {
        theRules = new Rules();
        theRules.initLevel();
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
                theRules.initLevel();
                render(true);
                break;

        }// switch
    }// makeMove


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render(boolean full) {

        theGridBitmap.render(this, theRules.getTheMap(), theRules.getThePlayer(), full);
    }// render


}

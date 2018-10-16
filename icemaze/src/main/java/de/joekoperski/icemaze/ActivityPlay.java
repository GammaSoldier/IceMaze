// TODO: 05.10.2018: Smooth animation 
// TODO: 05.10.2018: different screens
// TODO: 05.10.2018: make editor only available in debug version
package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ActivityPlay extends Activity {

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
        setContentView(R.layout.activity_play);

        theGridBitmap = new GameView(this);

        Intent anIntent = getIntent();
        theLevel = (Level) anIntent.getSerializableExtra("Level");

        surface = findViewById(R.id.imageView);
        surface.addView(theGridBitmap);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }// onClick
        });

        Button buttonEditor = findViewById(R.id.buttonEditor);
        buttonEditor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), ActivityEditor.class);
                startActivity(myIntent);
            }// onClick
        });

        View contentView = findViewById(R.id.mainLayout);

        contentView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                makeMove(Direction.NORTH);
            }

            public void onSwipeRight() {
                makeMove(Direction.EAST);
            }

            public void onSwipeLeft() {
                makeMove(Direction.WEST);
            }

            public void onSwipeBottom() {
                makeMove(Direction.SOUTH);
            }

        });

    }// onCreate


    @Override
    protected void onResume() {
        super.onResume();
        startGame();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void startGame() {
        theRules = new Rules();
        theRules.initLevel(theLevel);
        // TODO: 12.10.2018: set size of gameview
        final Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        size.x = ((size.x * 9 / 10) / theRules.getTheMap().getWidth()) * theRules.getTheMap().getWidth();
        size.y = size.x;
        theGridBitmap.setViewSize(size.x, size.y);

        ViewGroup vg = findViewById (R.id.mainLayout);
        vg.invalidate();
        render(true);
    }// startGame


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void makeMove(Direction direction) {
        MoveResult moveResult;

        moveResult = theRules.move(direction, true);
        render(false);

        while (moveResult == MoveResult.CONTINUE) {
            moveResult = theRules.move(direction, false);
            render(false);
        }

        switch (moveResult) {
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

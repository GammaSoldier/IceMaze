package de.joekoperski.icemaze;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Rules theRules;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startGame();
//                render();
            }// onClick
        });

        View contentView = (View)findViewById(R.id.mainLayout);
        contentView.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }// onCreate


    private void startGame() {
        theRules = new Rules();
        theRules.initLevel();
        render();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render(){
        gridBitmap theGridBitmap = new gridBitmap(this);
        theGridBitmap.render( this, theRules.getTheMap(), theRules.getThePlayer() );
    }// render


}

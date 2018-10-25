
package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityLevelTitle extends Activity {

    Levels theLevels;
    int levelIndex;          // the 1-based level index

    ////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_level_title);

        Intent anIntent = getIntent();
        theLevels = (Levels) anIntent.getSerializableExtra("Levels");
        levelIndex = anIntent.getIntExtra("int", 0);

        //init gui elements
        String string;
        // level title
        TextView textView = findViewById(R.id.textViewLevelNumber);
        textView.setText(getString(R.string.levelHeader, levelIndex));

        // level description
        textView = findViewById(R.id.textViewLevelDescription);
        string = theLevels.levelArray.get(levelIndex - 1).getLevelDescription();
        if (string.contentEquals("")) {
            textView.setText(R.string.levelDescriptionDefault);
        }//if
        else {
            int resId = getResources().getIdentifier(string, "string", getPackageName());
            textView.setText(resId);
        }// else

        // level image
        ImageView levelImage = findViewById(R.id.imageViewLevelImage);
        string = theLevels.levelArray.get(levelIndex - 1).getImageResource();
        if (string.contentEquals("")) {
        }//if
        else {
            int resId = getResources().getIdentifier(string, "drawable", getPackageName());
            levelImage.setImageResource(resId);
        }// else

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), ActivityPlay.class);

                myIntent.putExtra("Levels", theLevels);
                myIntent.putExtra("int", levelIndex);
                startActivity(myIntent);
            }// onClick
        });

    }
}

// TODO: 16.10.2018: create background images for each display density

package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class ActivityLevelSelector extends Activity {

    //    static final int MAX_LEVELS = 100;
    static final int BUTTONS_PER_LINE = 4;
    static final int LEVEL_SELECTOR_BUTTON_HEIGHT_DIVIDER = 6;
    static final int LEVEL_SELECTOR_HORIZONTAL_MARGIN_DIVIDER = 30;
    static final int LEVEL_SELECTOR_VERTICAL_MARGIN_DIVIDER = 40;
    ScrollView scrollView;
    LinearLayout layout;
    Levels levels;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_level_selector);

        // read levels
        String myJson = inputStreamToString(this.getResources().openRawResource(R.raw.levels));
        levels = new Gson().fromJson(myJson, Levels.class);

        //create GUI
        scrollView = findViewById(R.id.scrollView);

        layout = findViewById(R.id.levelButtonList);
        Button button;
        LinearLayout layoutButtonLine;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.weight = 1;
        lp.gravity = Gravity.CENTER;
        lp.height = size.x / LEVEL_SELECTOR_BUTTON_HEIGHT_DIVIDER;

        int horizontal = size.x / LEVEL_SELECTOR_HORIZONTAL_MARGIN_DIVIDER;
        int vertical = size.x / LEVEL_SELECTOR_VERTICAL_MARGIN_DIVIDER;
        lp.setMargins(horizontal, vertical, horizontal, vertical);

        layoutButtonLine = new LinearLayout(this);
        layoutButtonLine.setOrientation(LinearLayout.HORIZONTAL);
        layoutButtonLine.setHorizontalGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);

        // generate buttons
        for (int i = 1; i <= levels.levelArray.size(); i++) {
            // TODO: 10.10.2018: differentiate between locked and unlocked level selection button

            if (i <= Preferences.getNextLevelToPlay(this)) {
                button = new Button(this);
                button.setText(String.valueOf(i));
                button.setBackground(getResources().getDrawable(R.drawable.button_level_selector));
                button.setLayoutParams(lp);

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Button b = (Button) v;
                        try {
                            int num = Integer.parseInt(b.getText().toString());
                            Intent myIntent = new Intent(getBaseContext(), ActivityLevelTitle.class);

                            myIntent.putExtra("Levels", levels);
                            myIntent.putExtra("int", num);
                            startActivity(myIntent);
                        }//try
                        catch (NumberFormatException e) {
                            // button text does not contain a number. This might be the case for locked buttons. Do nothing
                        }// catch

                    }// onClick
                });
                layoutButtonLine.addView(button);
            }// if
            else {
                button = new Button(this);
                button.setBackground(getResources().getDrawable(R.drawable.button_level_locked));
                button.setLayoutParams(lp);

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Perform action on click
                        Toast.makeText(ActivityLevelSelector.this, R.string.locked_level, Toast.LENGTH_SHORT).show();
                    }// onClick
                });
                layoutButtonLine.addView(button);

            }// else

            if (i % BUTTONS_PER_LINE == 0) {
                layout.addView(layoutButtonLine);
                layoutButtonLine = new LinearLayout(this);
                layoutButtonLine.setOrientation(LinearLayout.HORIZONTAL);
                layoutButtonLine.setHorizontalGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);
            }// if
        }// for i

    }// onCreate


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();

        // TODO: 22.10.2018: read actual level from settings file
        int level = 1;

        double scrollPosition = ((Math.ceil((double) level / BUTTONS_PER_LINE) - 1) / (levels.levelArray.size() / BUTTONS_PER_LINE));
        scrollView.post(new Runnable() {
            double yPosition;

            public void run() {

                int height = layout.getHeight();
                int pos = (int) (height * yPosition);
                scrollView.scrollTo(0, pos);

                Log.d("ActivityLevelSelector", "height: " + height + " scroll position: " + pos);
            }// run

            Runnable init(double yPos) {
                yPosition = yPos;
                return (this);
            }// init
        }.init(scrollPosition));
    }// onResume


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }// inputStreamToString

}

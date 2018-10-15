package de.joekoperski.icemaze;

import android.app.Activity;
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

public class LevelSelectorActivity extends Activity {

    ScrollView scrollView;
    LinearLayout layout;
    static final int MAX_LEVELS = 100;
    static final int BUTTONS_PER_LINE = 4;
    static final int LEVEL_SELECTOR_BUTTON_HEIGHT_DIVIDER = 6;
    static final int LEVEL_SELECTOR_HORIZONTAL_MARGIN_DIVIDER = 25;
    static final int LEVEL_SELECTOR_VERTICAL_MARGIN_DIVIDER = 40;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_level_selector);

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

        for (int i = 1; i <= MAX_LEVELS; i++) {
            button = new Button(this);
            // TODO: 10.10.2018: differentiate between locked and unlocked level selection button
            button.setText(String.valueOf(i));
            button.setBackground(getResources().getDrawable(R.drawable.button_level_selector));
            button.setLayoutParams(lp);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    Button b = (Button) v;
                    try {
                        int num = Integer.parseInt(b.getText().toString());
                    }//try
                    catch (NumberFormatException e) {
                        // button text does not contain a number. This might be the case for locked buttons
                    }// catch
                    // TODO: 09.10.2018: Start PlayActivity with level number
                }// onClick
            });
            layoutButtonLine.addView(button);

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
        // TODO: 09.10.2018: read the actual level from saved data
        int level = 23;
        double scrollPosition = ((Math.ceil((double) level / BUTTONS_PER_LINE) - 1) / (MAX_LEVELS / BUTTONS_PER_LINE));
        scrollView.post(new Runnable() {
            double yPosition;

            public void run() {

                int height = layout.getHeight();
                int pos = (int) (height * yPosition);
                scrollView.scrollTo(0, pos);

                Log.d("LevelSelectorActivity", "height: " + height + " scroll position: " + pos);
            }// run

            Runnable init(double yPos) {
                yPosition = yPos;
                return (this);
            }// init
        }.init(scrollPosition));
    }// onResume

}

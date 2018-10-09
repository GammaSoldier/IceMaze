package de.joekoperski.icemaze;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class LevelSelectorActivity extends Activity {

    ScrollView scrollView;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);

        scrollView = findViewById(R.id.scrollView);

        layout = findViewById(R.id.levelButtonList);
        Button button;
        LinearLayout layoutButtonLine;

        layoutButtonLine = new LinearLayout(this);
        layoutButtonLine.setOrientation(LinearLayout.HORIZONTAL);
        layoutButtonLine.setHorizontalGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);

        for (int i = 1; i <= 100; i++) {
            button = new Button(this);
            button.setText(String.valueOf(i));
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Perform action on click
                    Button b = (Button) v;
                    int num = Integer.parseInt(b.getText().toString());
                    // TODO: 09.10.2018: Start MainActivity with level number
                }// onClick
            });
            layoutButtonLine.addView(button);


            if (i % 4 == 0) {
                layout.addView(layoutButtonLine);
                layoutButtonLine = new LinearLayout(this);
                layoutButtonLine.setOrientation(LinearLayout.HORIZONTAL);
                layoutButtonLine.setHorizontalGravity(LinearLayout.TEXT_ALIGNMENT_GRAVITY);
            }// if
        }// for i


    }// onCreate


    @Override
    protected void onResume() {
        super.onResume();
        // REWORK: 09.10.2018: scroll to actual level

        int level = 94;
        int line = level / 4;
        scrollView.post(new Runnable() {
            public void run() {
                int height = scrollView.getHeight();
                scrollView.smoothScrollTo(0, height / 2);
            }
        });    }
}

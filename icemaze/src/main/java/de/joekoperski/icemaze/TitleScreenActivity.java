package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TitleScreenActivity extends Activity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private final View.OnClickListener mButtonEditClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(getBaseContext(), EditorActivity.class);
            startActivity(myIntent);
        }// onClick
    };


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private final View.OnClickListener mButtonExitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Exit the app
            moveTaskToBack(true);
        }// onClick
    };


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_title_screen);

        // init buttons
        findViewById(R.id.button_editor).setOnClickListener(mButtonEditClickListener);
        findViewById(R.id.button_exit).setOnClickListener(mButtonExitClickListener);

    }
}

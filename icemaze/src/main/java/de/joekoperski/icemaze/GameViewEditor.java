package de.joekoperski.icemaze;

import android.view.MotionEvent;


public class GameViewEditor extends GameView {

    private EditorActivity parentActivity;
    private int numTilesX;
    private int numTilesY;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public GameViewEditor(EditorActivity context) {
        super(context);
        parentActivity = context;
    }// GameViewEditor


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setDimensions( int x, int y ) {
        numTilesX = x;
        numTilesY = y;
    }// setDimensions


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP) {
            int touchX = (int) event.getX();
            int touchY = (int) event.getY();

            int x = (touchX) / (getWidth() / numTilesX);
            int y = (touchY) / (getHeight() / numTilesY);

            parentActivity.TileTouched(x, y);
        }// if
        return true;
    }// onTouchEvent

}// GameViewEditor

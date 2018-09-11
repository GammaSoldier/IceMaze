package de.joekoperski.icemaze;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import static android.graphics.Bitmap.Config.RGB_565;

public class gridBitmap {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public gridBitmap(){ }// gridBitmap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render( Activity activity) {
        int width = 1000;
        int height = 1000;

        int tileWidth = 100;
        int tileHeight = 100;

        Bitmap bmTile = BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile);
        Bitmap bmpContent = Bitmap.createBitmap( width, height, RGB_565);
        Canvas canvas = new Canvas(bmpContent);

        ImageView imageView = activity.findViewById(R.id.imageView);

        for( int i = 0; i < 10; i++ ){
            for( int j = 0; j < 10; j++ ) {
                canvas.drawBitmap(bmTile, null, new Rect( i*tileWidth, j*tileHeight, (i+1)*tileWidth, (j+1)*tileHeight), null);
            }// for j
        }// for i
        imageView.setImageDrawable(new BitmapDrawable(activity.getResources(), bmpContent));
    }// render
}

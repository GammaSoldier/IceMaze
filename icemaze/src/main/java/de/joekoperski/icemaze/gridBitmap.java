package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import static android.graphics.Bitmap.Config.RGB_565;

public class gridBitmap {

    private Bitmap bmpPlayer;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public gridBitmap(MainActivity context){
        bmpPlayer = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_character);
    }// gridBitmap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render( Activity activity, Map map, PlayerCharacter player) {
        // TODO: 12.09.2018: this might be variable
        int width = 1000;
        int height = 1000;

        // TODO: 12.09.2018: this shall be given be the level
        int tileWidth = 100;
        int tileHeight = 100;

        Bitmap bmpContent = Bitmap.createBitmap( width, height, RGB_565);
        Canvas canvas = new Canvas(bmpContent);

        ImageView imageView = activity.findViewById(R.id.imageView);

        for( int i = 0; i < 10; i++ ){
            for( int j = 0; j < 10; j++ ) {
                ITile tile = TileFactory.getTile(map.getSourceMap(i, j));
                // TODO: 12.09.2018: Maybe this is too slow decoding just in time. Could also decode in constructor and keep all bitmaps in array
                canvas.drawBitmap(tile.getBitmap(activity), null, new Rect( i*tileWidth, j*tileHeight, (i+1)*tileWidth, (j+1)*tileHeight), null);
                canvas.drawBitmap(bmpPlayer, null, new Rect( player.getPosition().x*tileWidth, player.getPosition().y*tileHeight, (player.getPosition().x+1)*tileWidth, (player.getPosition().y+1)*tileHeight), null);
            }// for j
        }// for i
        imageView.setImageDrawable(new BitmapDrawable(activity.getResources(), bmpContent));
    }// render
}

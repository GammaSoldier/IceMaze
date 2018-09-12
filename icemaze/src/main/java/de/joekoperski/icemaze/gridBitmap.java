package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.graphics.Bitmap.Config.RGB_565;

public class gridBitmap {

    private Bitmap bmpPlayer;
    private ArrayList<Bitmap> bmpTile;
    private Bitmap bmpContent[];
    private int activeContent;

    // TODO: 12.09.2018: this might be variable
    private int width = 1000;
    private int height = 1000;



    ////////////////////////////////////////////////////////////////////////////////////////////////
    public gridBitmap(MainActivity context) {
        activeContent = 0;
        bmpContent = new Bitmap[2];
        bmpContent[activeContent] = Bitmap.createBitmap(width, height, RGB_565);
        bmpPlayer = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_character);
        bmpTile = new ArrayList<Bitmap>();
        for (int i = 0; i < TileID.NUM_TILE_IDS; i++) {
            bmpTile.add(TileFactory.getTile(i).getBitmap(context));
        }// for i

    }// gridBitmap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render(Activity activity, Map map, PlayerCharacter player, boolean fullRender) {
        // TODO: 12.09.2018: this might be variable
        int width = 1000;
        int height = 1000;

        // TODO: 12.09.2018: this shall be given be the level
        int tileWidth = 100;
        int tileHeight = 100;

        bmpContent[1 - activeContent] = Bitmap.createBitmap(width, height, RGB_565);
        Canvas canvas = new Canvas(bmpContent[1 - activeContent]);
        canvas.drawBitmap(bmpContent[activeContent], null, new Rect(0, 0, width, height), null);

        ImageView imageView = activity.findViewById(R.id.imageView);

        // TODO: 12.09.2018: just for debugging purpose
        long startTime = System.currentTimeMillis();

        if (fullRender) {
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    canvas.drawBitmap(bmpTile.get(map.getSourceMap(i, j)), null, new Rect(i * tileWidth, j * tileHeight, (i + 1) * tileWidth, (j + 1) * tileHeight), null);
                    canvas.drawBitmap(bmpPlayer, null, new Rect(player.getPosition().x * tileWidth, player.getPosition().y * tileHeight, (player.getPosition().x + 1) * tileWidth, (player.getPosition().y + 1) * tileHeight), null);
                }// for j
            }// for i
        }// if
        else {
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    if (map.getSourceMap(i, j) != map.getResultMap(i, j)) {
                        canvas.drawBitmap(bmpTile.get(map.getSourceMap(i, j)), null, new Rect(i * tileWidth, j * tileHeight, (i + 1) * tileWidth, (j + 1) * tileHeight), null);
                    }// if
                    Point pt = player.getPosition();
                    if (pt.x == i && pt.y == j) {
                        Point prev = player.getPreviousPosition();
                        canvas.drawBitmap(bmpTile.get(map.getSourceMap(prev.x, prev.y)), null, new Rect(player.getPreviousPosition().x * tileWidth, player.getPreviousPosition().y * tileHeight, (player.getPreviousPosition().x + 1) * tileWidth, (player.getPreviousPosition().y + 1) * tileHeight), null);
                        canvas.drawBitmap(bmpPlayer, null, new Rect(player.getPosition().x * tileWidth, player.getPosition().y * tileHeight, (player.getPosition().x + 1) * tileWidth, (player.getPosition().y + 1) * tileHeight), null);
                    }// if
                }// for j
            }// for i

        }
        imageView.setImageDrawable(new BitmapDrawable(activity.getResources(), bmpContent[1 - activeContent]));
        activeContent = 1 - activeContent;

        Log.d("gridBitmap", "Render duration: " + (System.currentTimeMillis() - startTime) + "ms");
    }// render
}

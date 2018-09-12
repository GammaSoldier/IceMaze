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

    private Bitmap bmpTiles[];
    private Bitmap bmpPlayer;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public gridBitmap(MainActivity context){

        bmpTiles = new Bitmap[TileID.NUM_TILE_IDS];
        bmpTiles[TileID.TILE_START] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_ICE] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_ROCK] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_rock);
        bmpTiles[TileID.TILE_GROW_ROCK] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_LOCK] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_KEY] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_REDIRECT_NORTH] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_REDIRECT_SOUTH] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_REDIRECT_WEST] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_REDIRECT_EAST] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_TELEPORT] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_TELEPORT_TARGET] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_STICK_ONCE] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_ENTER_NORTH] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_ENTER_SOUTH] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_ENTER_WEST] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_ENTER_EAST] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_KILL] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_ice);
        bmpTiles[TileID.TILE_FINISH] = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_finish);

        bmpPlayer = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_character);


    }// gridBitmap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render( Activity activity, Map map, PlayerCharacter player) {
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
                canvas.drawBitmap(bmpTiles[map.getSourceMap(i, j)], null, new Rect( i*tileWidth, j*tileHeight, (i+1)*tileWidth, (j+1)*tileHeight), null);
                canvas.drawBitmap(bmpPlayer, null, new Rect( player.getPosition().x*tileWidth, player.getPosition().y*tileHeight, (player.getPosition().x+1)*tileWidth, (player.getPosition().y+1)*tileHeight), null);
            }// for j
        }// for i
        imageView.setImageDrawable(new BitmapDrawable(activity.getResources(), bmpContent));
    }// render
}

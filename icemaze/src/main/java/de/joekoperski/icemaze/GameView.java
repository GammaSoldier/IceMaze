package de.joekoperski.icemaze;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.graphics.Bitmap.Config.RGB_565;


public class GameView extends SurfaceView implements Callback {

    private SurfaceHolder surfaceHolder;
    private GfxLoopThread gfxLoopThread;
    private Activity mContext;

    private Bitmap bmpPlayer;
    private ArrayList<Bitmap> bmpTile;
    private Bitmap bmpContent[];
    private int activeContent;

    // TODO: 12.09.2018: this might be variable
    private int width = 500;
    private int height = 500;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public GameView(Activity context) {
        super(context);

        mContext = (Activity) context;

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        activeContent = 0;
        bmpContent = new Bitmap[2];
        bmpContent[activeContent] = Bitmap.createBitmap(width, height, RGB_565);
        bmpPlayer = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.player_character);
        bmpTile = new ArrayList<Bitmap>();
        for (int i = 0; i < TileID.NUM_TILE_IDS; i++) {
            bmpTile.add(TileFactory.getTile(i).getBitmap(mContext));
        }// for i
    }// GameView


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Point size = new Point();
        mContext.getWindowManager().getDefaultDisplay().getSize(size);
        android.view.ViewGroup.LayoutParams lp = this.getLayoutParams();

//        lp.width = (size.x); // TODO: 12.10.2018: / tilewidth * tilewidth
//        lp.height = lp.width; // required height
        lp.width = width; // TODO: 12.10.2018: / tilewidth * tilewidth
        lp.height = height; // required height
        this.setLayoutParams(lp);

        gfxLoopThread = new GfxLoopThread(this);
        gfxLoopThread.setRunning(true);
        if (!gfxLoopThread.isAlive()) {
            gfxLoopThread.start();
        }// if
    }// surfaceCreated


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }// surfaceChanged


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        gfxLoopThread.setRunning(false);
        while (retry) {

            try {
                gfxLoopThread.join(1000);
                retry = false;
            }// try
            catch (InterruptedException e) {
            }// catch
        }// while
    }// surfaceDestroyed


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);
            canvas.drawBitmap(bmpContent[activeContent], 0, 0, null);
        }// if
    }// draw


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void render(Activity activity, Map map, PlayerCharacter player, boolean fullRender) {
//        int width = getWidth();
//        int height = getHeight();

        int tileWidth = width / map.getWidth();
        int tileHeight = height / map.getHeight();

        bmpContent[1 - activeContent] = Bitmap.createBitmap(width, height, RGB_565);
        Canvas canvas = new Canvas(bmpContent[1 - activeContent]);
        canvas.drawBitmap(bmpContent[activeContent], null, new Rect(0, 0, width, height), null);

        // TODO: 12.09.2018: just for debugging purpose
        long startTime = System.currentTimeMillis();

        if (fullRender) {
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    canvas.drawBitmap(bmpTile.get(map.getSourceMap(i, j)), null, new Rect(i * tileWidth, j * tileHeight, (i + 1) * tileWidth, (j + 1) * tileHeight), null);
                    if (player != null) {
                        canvas.drawBitmap(bmpPlayer, null, new Rect(player.getPosition().x * tileWidth, player.getPosition().y * tileHeight, (player.getPosition().x + 1) * tileWidth, (player.getPosition().y + 1) * tileHeight), null);
                    }// if
                }// for j
            }// for i
        }// if
        else {
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    if (map.getSourceMap(i, j) != map.getResultMap(i, j)) {
                        canvas.drawBitmap(bmpTile.get(map.getSourceMap(i, j)), null, new Rect(i * tileWidth, j * tileHeight, (i + 1) * tileWidth, (j + 1) * tileHeight), null);
                    }// if
                    if (player != null) {
                        Point pt = player.getPosition();
                        if (pt.x == i && pt.y == j) {
                            Point prev = player.getPreviousPosition();
                            canvas.drawBitmap(bmpTile.get(map.getSourceMap(prev.x, prev.y)), null, new Rect(player.getPreviousPosition().x * tileWidth, player.getPreviousPosition().y * tileHeight, (player.getPreviousPosition().x + 1) * tileWidth, (player.getPreviousPosition().y + 1) * tileHeight), null);
                            canvas.drawBitmap(bmpPlayer, null, new Rect(player.getPosition().x * tileWidth, player.getPosition().y * tileHeight, (player.getPosition().x + 1) * tileWidth, (player.getPosition().y + 1) * tileHeight), null);
                        }// if
                    }// if
                }// for j
            }// for i
        }// else
        activeContent = 1 - activeContent;

        Log.d("gridBitmap", "Render duration: " + (System.currentTimeMillis() - startTime) + "ms");

        try {
            TimeUnit.MILLISECONDS.sleep(80);
        } // try
        catch (InterruptedException exeption) {
            Log.d("GameView", "renderPlayfield InterruptedException");
        }// catch

    }// render


    ////////////////////////////////////////////////////////////////////////////////////////////////
    void setViewSize(int x, int y) {
        android.view.ViewGroup.LayoutParams lp = this.getLayoutParams();

        width = x;
        height = y;

        lp.width = x;
        lp.height = y;
        this.setLayoutParams(lp);

//        invalidate();
    }// setViewSize



}// GameView

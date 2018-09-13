package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.graphics.Bitmap.Config.RGB_565;


public class GameView extends SurfaceView implements Callback {

	private SurfaceHolder surfaceHolder;
	private GfxLoopThread gfxLoopThread;
	private MainActivity mContext;

	private Bitmap bmpPlayer;
	private ArrayList<Bitmap> bmpTile;
	private Bitmap bmpContent[];
	private int activeContent;

	// TODO: 12.09.2018: this might be variable
	private int width = 1000;
	private int height = 1000;



	////////////////////////////////////////////////////////////////////////////////////////////////
	public GameView(MainActivity context ) {
		super(context);

		mContext = (MainActivity) context;
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

	}


	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		gfxLoopThread = new GfxLoopThread(this);
		gfxLoopThread.setRunning(true);
		if (!gfxLoopThread.isAlive()) {
			gfxLoopThread.start();
		}// if

	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
		// TODO Auto-generated method stub
	}


	////////////////////////////////////////////////////////////////////////////////////////////////
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		
		gfxLoopThread.setRunning(false);
		while(retry) {

			try {
				gfxLoopThread.join(1000 );
				retry=false;
			}
			catch(InterruptedException e) {
			}
		}
	}




	////////////////////////////////////////////////////////////////////////////////////////////////
	public void draw( Canvas canvas ) {

		if (canvas != null) {
			super.draw(canvas);
//			canvas.drawBitmap(bmpBackground, 0, 0, null);
			canvas.drawBitmap(bmpContent[activeContent], 0, 0, null);
		}// if

	}


	////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////
	public void render(Activity activity, Map map, PlayerCharacter player, boolean fullRender) {
		// TODO: 12.09.2018: this might be variable
		int width = getWidth();
		int height = getHeight();

		// TODO: 12.09.2018: this shall be given be the level
		int tileWidth = width / map.getWidth();
		int tileHeight = height / map.getHeight();

		bmpContent[1 - activeContent] = Bitmap.createBitmap(width, height, RGB_565);
		Canvas canvas = new Canvas(bmpContent[1 - activeContent]);
		canvas.drawBitmap(bmpContent[activeContent], null, new Rect(0, 0, width, height), null);

//		ImageView imageView = activity.findViewById(R.id.imageView);

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
//		imageView.setImageDrawable(new BitmapDrawable(activity.getResources(), bmpContent[1 - activeContent]));
		activeContent = 1 - activeContent;

		Log.d("gridBitmap", "Render duration: " + (System.currentTimeMillis() - startTime) + "ms");
		try {
			TimeUnit.MILLISECONDS.sleep(80);
		} // try
		catch (InterruptedException exeption) {
			Log.d("GameView", "renderPlayfield InterruptedException");
		}// catch

	}// render

}// GameView

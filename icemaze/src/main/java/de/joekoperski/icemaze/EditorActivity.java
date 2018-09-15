package de.joekoperski.icemaze;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class EditorActivity extends Activity {
    private GameViewEditor theGridBitmap;
    private RelativeLayout surface;
    private int selectedTile;
    private int width;
    private int height;
    private Map theMap;
    private PlayerCharacter thePlayer;
    private Level theLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_editor);

        theGridBitmap = new GameViewEditor(this);

        surface = (RelativeLayout)findViewById(R.id.gridView);
        surface.addView(theGridBitmap);

        selectedTile = TileID.TILE_ICE;

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
                theLevel = new Level(theMap);
                myIntent.putExtra("Level", theLevel );
                startActivity(myIntent);
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonIce = (Button) findViewById(R.id.buttonIce);
        buttonIce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ICE;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        buttonRock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ROCK;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonGrowRock = (Button) findViewById(R.id.buttonGrowRock);
        buttonGrowRock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_GROW_ROCK;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonLock = (Button) findViewById(R.id.buttonLock);
        buttonLock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_LOCK;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonKey = (Button) findViewById(R.id.buttonKey);
        buttonKey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_KEY;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirNorth = (Button) findViewById(R.id.buttonRedirNorth);
        buttonRedirNorth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_NORTH;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirSouth = (Button) findViewById(R.id.buttonRedirSouth);
        buttonRedirSouth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_SOUTH;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirWest = (Button) findViewById(R.id.buttonRedirWest);
        buttonRedirWest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_WEST;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirEast = (Button) findViewById(R.id.buttonRedirEast);
        buttonRedirEast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_EAST;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonTeleport = (Button) findViewById(R.id.buttonTeleport);
        buttonTeleport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_TELEPORT;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonTeleportTarget = (Button) findViewById(R.id.buttonTeleportTarget);
        buttonTeleportTarget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_TELEPORT_TARGET;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonStickOnce = (Button) findViewById(R.id.buttonStickOnce);
        buttonStickOnce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_STICK_ONCE;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewayNorth = (Button) findViewById(R.id.buttonOnewayNorth);
        buttonOnewayNorth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_NORTH;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewaySouth = (Button) findViewById(R.id.buttonOnewaySouth);
        buttonOnewaySouth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_SOUTH;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewayEast = (Button) findViewById(R.id.buttonOnewayEast);
        buttonOnewayEast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_EAST;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewayWest = (Button) findViewById(R.id.buttonOnewayWest);
        buttonOnewayWest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_WEST;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonKill = (Button) findViewById(R.id.buttonKill);
        buttonKill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_KILL;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonFinish = (Button) findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_FINISH;
            }// onClick
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_START;
            }// onClick
        });



        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonGenerate = (Button) findViewById(R.id.buttonGenerate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // read Editviews
                EditText edit = (EditText) findViewById(R.id.editTextWidth);
                width = Integer.parseInt(edit.getText().toString());
                edit = (EditText) findViewById(R.id.editTextHeight);
                height = Integer.parseInt(edit.getText().toString());
                generateMap( width, height);
            }// onClick
        });

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void generateMap(int width, int height) {
        theMap = new Map(width, height);
        thePlayer = new PlayerCharacter(new Point(0,0));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                theMap.setSourceMap(i, j, TileID.TILE_ICE);
                theMap.setResultMap(i, j, TileID.TILE_ICE);
            }// for j
        }// for i

        theGridBitmap.setDimensions(width, height);
        theGridBitmap.render(this, theMap, null, true);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void TileTouched(int x, int y) {
        theMap.setSourceMap(x, y, selectedTile);
        theMap.setResultMap(x, y, selectedTile);

        if( selectedTile == TileID.TILE_START) {
            thePlayer.setPosition(new Point(x,y));
        }// if
        Log.d("Editor", "TileTouched: x = " + x + ", y = "+ y);
        theGridBitmap.render(this, theMap, thePlayer, true);

    }
}

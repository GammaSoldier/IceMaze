
package de.joekoperski.icemaze;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityEditor extends Activity {
    public static final int REQUEST_WRITE_STORAGE = 112;
    public static final int FILE_SELECT_CODE = 123;

    public static final int FILE_FORMAT_BINARY = 0;
    public static final int FILE_FORMAT_TEXT = 1;

    private GameViewEditor theGridBitmap;
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

        theMap = null;
        theGridBitmap = new GameViewEditor(this);

        thePlayer = null;

        RelativeLayout surface = findViewById(R.id.gridView);
        surface.addView(theGridBitmap);

        selectedTile = TileID.TILE_ICE;

        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonTest = findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(getBaseContext(), ActivityPlay.class);
                theLevel = new Level(theMap);
                myIntent.putExtra("Level", theLevel);
                startActivity(myIntent);
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonLoad = findViewById(R.id.buttonLoad);
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFile();
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveFile(FILE_FORMAT_BINARY);
                Toast.makeText(ActivityEditor.this, "Level saved", Toast.LENGTH_SHORT).show();
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonExport = findViewById(R.id.buttonExport);
        buttonExport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveFile(FILE_FORMAT_TEXT);
                Toast.makeText(ActivityEditor.this, "Level exported", Toast.LENGTH_SHORT).show();
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonIce = findViewById(R.id.buttonIce);
        buttonIce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ICE;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRock = findViewById(R.id.buttonRock);
        buttonRock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ROCK;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonGrowRock = findViewById(R.id.buttonGrowRock);
        buttonGrowRock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_GROW_ROCK;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonLock = findViewById(R.id.buttonLock);
        buttonLock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_LOCK;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonKey = findViewById(R.id.buttonKey);
        buttonKey.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_KEY;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirNorth = findViewById(R.id.buttonRedirNorth);
        buttonRedirNorth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_NORTH;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirSouth = findViewById(R.id.buttonRedirSouth);
        buttonRedirSouth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_SOUTH;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirWest = findViewById(R.id.buttonRedirWest);
        buttonRedirWest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_WEST;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonRedirEast = findViewById(R.id.buttonRedirEast);
        buttonRedirEast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_REDIRECT_EAST;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonTeleport = findViewById(R.id.buttonTeleport);
        buttonTeleport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_TELEPORT;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonTeleportTarget = findViewById(R.id.buttonTeleportTarget);
        buttonTeleportTarget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_TELEPORT_TARGET;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonStickOnce = findViewById(R.id.buttonStickOnce);
        buttonStickOnce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_STICK_ONCE;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewayNorth = findViewById(R.id.buttonOnewayNorth);
        buttonOnewayNorth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_NORTH;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewaySouth = findViewById(R.id.buttonOnewaySouth);
        buttonOnewaySouth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_SOUTH;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewayEast = findViewById(R.id.buttonOnewayEast);
        buttonOnewayEast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_EAST;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonOnewayWest = findViewById(R.id.buttonOnewayWest);
        buttonOnewayWest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_ONEWAY_WEST;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonKill = findViewById(R.id.buttonKill);
        buttonKill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_KILL;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_FINISH;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_START;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonCrack = findViewById(R.id.buttonCrack);
        buttonCrack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedTile = TileID.TILE_CRACK;
            }// onClick
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////
        Button buttonGenerate = findViewById(R.id.buttonGenerate);
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // read Editviews
                EditText edit = findViewById(R.id.editTextWidth);
                width = Integer.parseInt(edit.getText().toString());
                edit = findViewById(R.id.editTextHeight);
                height = Integer.parseInt(edit.getText().toString());
                generateMap(width, height);

            }// onClick
        });

    }// onCreate


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onResume() {
        super.onResume();
        if( theMap != null ) {
            resizeGameView();
        }// if
    }// onResume


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void generateMap(int width, int height) {
        theMap = new Map(width, height);
        thePlayer = new PlayerCharacter(new Point(0, 0));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                theMap.setSourceMap(i, j, TileID.TILE_ICE);
                theMap.setResultMap(i, j, TileID.TILE_ICE);
            }// for j
        }// for i

        theGridBitmap.setDimensions(width, height);
        resizeGameView();
        theGridBitmap.render(this, theMap, null, true);
    }// generateMap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void TileTouched(int x, int y) {
        if (selectedTile == TileID.TILE_START) {
            if( thePlayer == null ) {
                thePlayer = new PlayerCharacter(new Point(0,0));
            }// if
            thePlayer.setPosition(new Point(x, y));
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (theMap.getSourceMap(i, j) == TileID.TILE_START) {
                        theMap.setSourceMap(i, j, TileID.TILE_ICE);
                        theMap.setResultMap(i, j, TileID.TILE_ICE);
                    }// if
                }// for j
            }// for i
        }// if
        theMap.setSourceMap(x, y, selectedTile);
        theMap.setResultMap(x, y, selectedTile);

        Log.d("Editor", "TileTouched: x = " + x + ", y = " + y);
        theGridBitmap.render(this, theMap, thePlayer, true);

    }// TileTouched


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void loadFile() {
        String type = "*/*";

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(type);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "select file"), FILE_SELECT_CODE);
    }// loadFile


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK && data != null) {
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/IceMaze");

            Uri uploadfileuri = data.getData();
            File file = new File(uploadfileuri.getPath());

            final String[] split = file.getPath().split("/");
            String filePath = dir + "/" + split[split.length - 1];

            if (filePath != null) {
                try {
                    FileInputStream f = new FileInputStream(filePath);
                    DataInputStream din = new DataInputStream(f);

                    int width = din.readInt();
                    int height = din.readInt();
                    theMap = new Map(width, height);
                    for (int i = 0; i < theMap.getWidth(); i++) {
                        for (int j = 0; j < theMap.getHeight(); j++) {
                            theMap.setSourceMap(i, j, din.readInt());
                            if (theMap.getSourceMap(i, j) == TileID.TILE_START) {
                                thePlayer = new PlayerCharacter(new Point(i, j));
                            }// if
                        }// for j
                    }// for i

                    theGridBitmap.setDimensions(width, height);
                    resizeGameView();
                    theGridBitmap.render(this, theMap, thePlayer, true);

                    f.close();
                    Toast.makeText(this, "File loaded", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }// if
            else {
                Toast.makeText(this, "File NOT loaded: " + filePath, Toast.LENGTH_SHORT).show();
            }// else
        }// if
    }// onActivityResult


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void saveFile(int format) {
        boolean hasPermission = (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }// if
        else {
            // write to external storage:
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/IceMaze");
            if (!dir.mkdirs()) {
                Log.d("ActivityEditor", "could not create directory: " + dir);
            }// if

            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String strDt = simpleDate.format(new Date());

            if (format == FILE_FORMAT_BINARY) {
                File file = new File(dir, "icemaze" + strDt + ".bin");
                writeBinaryData(file);
            }// if
            else {
                File file = new File(dir, "icemaze" + strDt + ".txt");
                writeTextData(file);
            }// else
        }// else
    }// saveFile


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void writeBinaryData(File file) {
        try {
            FileOutputStream f = new FileOutputStream(file);
            DataOutputStream dout = new DataOutputStream(f);

            dout.writeInt(theMap.getWidth());
            dout.writeInt(theMap.getHeight());

            for (int i = 0; i < theMap.getWidth(); i++) {
                for (int j = 0; j < theMap.getHeight(); j++) {
                    dout.writeInt(theMap.getSourceMap(i, j));
                }// for j
            }// for i
            f.close();

        }// try
        catch (IOException e) {
            e.printStackTrace();
        }// catch

    }// writeBinaryData


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void writeTextData(File file) {
        try {
            FileOutputStream f = new FileOutputStream(file);
            DataOutputStream dout = new DataOutputStream(f);

            dout.writeBytes(String.valueOf(theMap.getWidth()));
            dout.write(',');
            dout.writeBytes(String.valueOf(theMap.getHeight()));
            dout.write('\n');

            for (int i = 0; i < theMap.getWidth(); i++) {
                if (i > 0) {
                    dout.write(',');
                }// if
                dout.writeBytes("{ ");
                for (int j = 0; j < theMap.getHeight(); j++) {
                    if (j > 0) {
                        dout.write(',');
                    }// if
                    dout.writeBytes(String.valueOf(theMap.getSourceMap(i, j)));
                }// for j
                dout.writeBytes(" }");
                dout.write('\n');

            }// for i
            f.close();
        }// try
        catch (IOException e) {
            e.printStackTrace();
        }// catch

    }// writeTextData


    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_WRITE_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "The app was allowed to write to your storage!", Toast.LENGTH_LONG).show();
                    // Reload the activity with permission granted or use the features what required the permission
                }// if
                else {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }// else
                break;
        }// switch
    }// onRequestPermissionsResult


    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void resizeGameView() {
        final Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        size.x = ((size.x * 9 / 10) / theMap.getWidth()) * theMap.getWidth();
        size.y = size.x;
        theGridBitmap.setViewSize(size.x, size.y);

    }
}

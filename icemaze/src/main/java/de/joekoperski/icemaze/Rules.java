package de.joekoperski.icemaze;

import android.graphics.Point;
import android.graphics.Rect;

public class Rules {
    private int levelWidth;
    private int levelHeight;
    private Map theMap;
    private PlayerCharacter thePlayer;

    // REWORK: 11.09.2018: Testlevel
    private int level[][] = {
              { 2, 0, 1, 1, 1, 2, 1, 1, 1, 5}
            , { 2, 1, 1, 3, 1, 2, 1, 1, 1, 1}
            , { 2, 1, 1, 1, 1, 3, 1, 1, 3, 1}
            , { 2, 4, 4, 4, 4, 3, 4, 4, 3, 2}
            , { 2, 1, 1, 3, 1, 1, 3, 1, 1, 2}
            , { 2, 1, 3, 1, 1, 1, 1, 1, 1, 2}
            , { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            , { 2, 1, 1, 1, 1, 1, 2, 1, 1, 1}
            , { 2, 2, 1, 1, 1, 1, 1,18, 3, 1}
            , { 2, 3, 1, 3, 1, 1, 1, 1, 1, 1}
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Rules() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Map getTheMap() {
        return theMap;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public PlayerCharacter getThePlayer() {
        return thePlayer;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TODO pass a level to this method
    public void initLevel() {
        levelWidth = 10;
        levelHeight = 10;
        theMap = new Map(levelWidth, levelHeight);
//        for (int i = 0; i < levelWidth; i++) {
//            for (int j = 0; j < levelHeight; j++) {
//                theMap.setSourceMap(i, j, level[i][j]);
//                theMap.setResultMap(i, j, level[i][j]);
//            }// for j
//        }// for i
        // TODO: 14.09.2018 Testcode. Use code above 
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                theMap.setSourceMap(i, j, level[j][i]);
                theMap.setResultMap(i, j, level[j][i]);
            }// for j
        }// for i
        thePlayer = new PlayerCharacter(theMap.find(TileID.TILE_START));

//        thePlayer.setPosition(theMap.find(TileID.TILE_START));
    }// initLevel


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public MoveResult move(Direction direction, boolean firstStep) {
        MoveResult retVal;

        if (firstStep) {
            thePlayer.setImpulse(direction);
        }// if
        thePlayer.move(new Rect(0, 0, levelWidth - 1, levelHeight - 1));

        if (thePlayer.getImpulse() != Direction.STILL) {
            ITile Tile;
            MoveResult moveResult;
            Tile = TileFactory.getTile(theMap.getSourceMap(thePlayer.getPosition().x, thePlayer.getPosition().y));
            retVal = Tile.doAction(theMap, thePlayer);
        }// if
        else {
            retVal = MoveResult.PLAYER_STOP;
        }// else
        return retVal;
    }// move
}

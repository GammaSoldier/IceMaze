package de.joekoperski.icemaze;

import android.graphics.Rect;

public class Rules {
    private int levelWidth;
    private int levelHeight;
    private Map theMap;
    private PlayerCharacter thePlayer;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    Rules() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    Map getTheMap() {
        return theMap;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    PlayerCharacter getThePlayer() {
        return thePlayer;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    void initLevel(Level level) {
        theMap = level.getMap();
        levelWidth = theMap.getWidth();
        levelHeight = theMap.getHeight();

        thePlayer = new PlayerCharacter(theMap.find(TileID.TILE_START));

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
            Tile = TileFactory.getTile(theMap.getSourceMap(thePlayer.getPosition().x, thePlayer.getPosition().y));
            retVal = Tile.doAction(theMap, thePlayer);
        }// if
        else {
            retVal = MoveResult.PLAYER_STOP;
        }// else
        return retVal;
    }// move
}

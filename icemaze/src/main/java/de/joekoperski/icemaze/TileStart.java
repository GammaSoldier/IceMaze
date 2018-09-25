/***************************************************************************************************
 * To implement a new tile do the folowing steps:
 * - insert a new PNG for the tile in drawable folder
 * - add the ID of the tile in "TileID"
 * - implement the class for the tile here
 * - add the class to "TileFactory"
 * - add a button to editor
 */


package de.joekoperski.icemaze;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;


/**
 * ***********************************************************************************************
 */
public class TileStart implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_ice);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileIce implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_ice);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileFinish implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.LEVEL_COMPLETE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_finish);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileRock implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.STILL);
        Point pt = new Point();
        pt.x = playerCharacter.getPreviousPosition().x;
        pt.y = playerCharacter.getPreviousPosition().y;
        playerCharacter.setPosition(pt);
        return MoveResult.PLAYER_STOP;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_rock);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileGrowRock implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        map.setSourceMap(playerCharacter.getPosition().x, playerCharacter.getPosition().y, TileID.TILE_ROCK);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_grow_rock);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileLock implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.STILL);
        Point pt = new Point();
        pt.x = playerCharacter.getPreviousPosition().x;
        pt.y = playerCharacter.getPreviousPosition().y;
        playerCharacter.setPosition(pt);
        return MoveResult.PLAYER_STOP;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_lock);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileKey implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getSourceMap(i, j) == TileID.TILE_LOCK) {
                    map.setSourceMap(i, j, TileID.TILE_ICE);
                }// if
            }// for j
        }// for i
        map.setSourceMap(playerCharacter.getPosition().x, playerCharacter.getPosition().y, TileID.TILE_ICE);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_key);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileRedirectNorth implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.NORTH);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_redirect_north);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileRedirectSouth implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.SOUTH);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_redirect_south);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileRedirectWest implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.WEST);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_redirect_west);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileRedirectEast implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.EAST);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_redirect_east);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileTeleport implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        if (!playerCharacter.isJump()) {
            playerCharacter.setJump(true);
        }// if
        else {
            for (int i = 0; i < map.getWidth(); i++) {
                for (int j = 0; j < map.getHeight(); j++) {
                    if (map.getSourceMap(i, j) == TileID.TILE_TELEPORT_TARGET) {
                        playerCharacter.setPosition(new Point(i, j));
                        playerCharacter.setJump(false);
                    }// if
                }// for j
            }// for i
        }// else

        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_teleport);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileTeleportExit implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_teleport_exit);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileStickOnce implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        map.setSourceMap(playerCharacter.getPosition().x, playerCharacter.getPosition().y, TileID.TILE_ICE);
        return MoveResult.PLAYER_STOP;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_stick_once);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileOnewayNorth implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        MoveResult retVal;
        if (playerCharacter.getImpulse() != Direction.NORTH) {
            playerCharacter.setImpulse(Direction.STILL);
            Point pt = new Point();
            pt.x = playerCharacter.getPreviousPosition().x;
            pt.y = playerCharacter.getPreviousPosition().y;
            playerCharacter.setPosition(pt);
            retVal = MoveResult.PLAYER_STOP;
        } else {
            retVal = MoveResult.CONTINUE;
        }
        return retVal;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_oneway_north);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileOnewaySouth implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        MoveResult retVal;
        if (playerCharacter.getImpulse() != Direction.SOUTH) {
            playerCharacter.setImpulse(Direction.STILL);
            Point pt = new Point();
            pt.x = playerCharacter.getPreviousPosition().x;
            pt.y = playerCharacter.getPreviousPosition().y;
            playerCharacter.setPosition(pt);
            retVal = MoveResult.PLAYER_STOP;
        } else {
            retVal = MoveResult.CONTINUE;
        }
        return retVal;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_oneway_south);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileOnewayWest implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        MoveResult retVal;
        if (playerCharacter.getImpulse() != Direction.WEST) {
            playerCharacter.setImpulse(Direction.STILL);
            Point pt = new Point();
            pt.x = playerCharacter.getPreviousPosition().x;
            pt.y = playerCharacter.getPreviousPosition().y;
            playerCharacter.setPosition(pt);
            retVal = MoveResult.PLAYER_STOP;
        } else {
            retVal = MoveResult.CONTINUE;
        }
        return retVal;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_oneway_west);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileOnewayEast implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        MoveResult retVal;
        if (playerCharacter.getImpulse() != Direction.EAST) {
            playerCharacter.setImpulse(Direction.STILL);
            Point pt = new Point();
            pt.x = playerCharacter.getPreviousPosition().x;
            pt.y = playerCharacter.getPreviousPosition().y;
            playerCharacter.setPosition(pt);
            retVal = MoveResult.PLAYER_STOP;
        } else {
            retVal = MoveResult.CONTINUE;
        }
        return retVal;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_oneway_east);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileKill implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        playerCharacter.setImpulse(Direction.STILL);
        return MoveResult.LEVEL_RESTART;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_kill);
    }// getBitmap
}


/**
 * ***********************************************************************************************
 */
class TileCrack implements ITile {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        map.setSourceMap(playerCharacter.getPosition().x, playerCharacter.getPosition().y, TileID.TILE_KILL);
        return MoveResult.CONTINUE;
    }// doAction

    @Override
    public Bitmap getBitmap(Activity activity) {
        return BitmapFactory.decodeResource(activity.getResources(), R.drawable.tile_crack);
    }// getBitmap
}


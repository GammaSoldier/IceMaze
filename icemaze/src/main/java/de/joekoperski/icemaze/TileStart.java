/***************************************************************************************************
 * To implement a new tile do the folowing steps:
 * - insert a new PNG for the tile in drawable folder
 * - add the ID of the tile in "TileID"
 * - implement the class for the tile here
 * - add the class to "TileFactory"
 */


package de.joekoperski.icemaze;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/** ************************************************************************************************
 *
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


/** ************************************************************************************************
 *
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


/** ************************************************************************************************
 *
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


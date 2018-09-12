package de.joekoperski.icemaze;

import android.app.Activity;
import android.graphics.Bitmap;

public interface ITile {
    MoveResult doAction(Map map, PlayerCharacter playerCharacter);
    Bitmap getBitmap(Activity activity);
}

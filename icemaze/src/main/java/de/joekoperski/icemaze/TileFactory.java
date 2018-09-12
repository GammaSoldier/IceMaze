package de.joekoperski.icemaze;

public class TileFactory {
    public static ITile getTile(int id){
        ITile retVal;

        switch (id) {
            case TileID.TILE_START:
                retVal = new TileStart();
                break;
            case TileID.TILE_ICE:
                retVal = new TileIce();
                break;
            case TileID.TILE_ROCK:
                retVal = new TileRock();
                break;
//            case TileID.TILE_GROW_ROCK:
//                break;
//            case TileID.TILE_LOCK:
//                break;
//            case TileID.TILE_KEY:
//                break;
//            case TileID.TILE_REDIRECT_NORTH:
//                break;
//            case TileID.TILE_REDIRECT_SOUTH:
//                break;
//            case TileID.TILE_REDIRECT_WEST:
//                break;
//            case TileID.TILE_REDIRECT_EAST:
//                break;
//            case TileID.TILE_TELEPORT:
//                break;
//            case TileID.TILE_TELEPORT_TARGET:
//                break;
//            case TileID.TILE_STICK_ONCE:
//                break;
//            case TileID.TILE_ENTER_NORTH:
//                break;
//            case TileID.TILE_ENTER_SOUTH:
//                break;
//            case TileID.TILE_ENTER_WEST:
//                break;
//            case TileID.TILE_ENTER_EAST:
//                break;
//            case TileID.TILE_KILL:
//                break;
            case TileID.TILE_FINISH:
                retVal = new TileFinish();
                break;
            default:
                retVal =  new TileIce();

        }// switch
        return retVal;
    }
}

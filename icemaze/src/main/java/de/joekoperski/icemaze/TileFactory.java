package de.joekoperski.icemaze;

class TileFactory {
    static ITile getTile(int id){
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
            case TileID.TILE_GROW_ROCK:
                retVal = new TileGrowRock();
                break;
            case TileID.TILE_LOCK:
                retVal = new TileLock();
                break;
            case TileID.TILE_KEY:
                retVal = new TileKey();
                break;
            case TileID.TILE_REDIRECT_NORTH:
                retVal = new TileRedirectNorth();
                break;
            case TileID.TILE_REDIRECT_SOUTH:
                retVal = new TileRedirectSouth();
                break;
            case TileID.TILE_REDIRECT_WEST:
                retVal = new TileRedirectWest();
                break;
            case TileID.TILE_REDIRECT_EAST:
                retVal = new TileRedirectEast();
                break;
            case TileID.TILE_TELEPORT:
                retVal = new TileTeleport();
                break;
            case TileID.TILE_TELEPORT_TARGET:
                retVal = new TileTeleportExit();
                break;
            case TileID.TILE_STICK_ONCE:
                retVal = new TileStickOnce();
                break;
            case TileID.TILE_ONEWAY_NORTH:
                retVal = new TileOnewayNorth();
                break;
            case TileID.TILE_ONEWAY_SOUTH:
                retVal = new TileOnewaySouth();
                break;
            case TileID.TILE_ONEWAY_WEST:
                retVal = new TileOnewayWest();
                break;
            case TileID.TILE_ONEWAY_EAST:
                retVal = new TileOnewayEast();
                break;
            case TileID.TILE_KILL:
                retVal = new TileKill();
                break;
            case TileID.TILE_FINISH:
                retVal = new TileFinish();
                break;
            case TileID.TILE_CRACK:
                retVal = new TileCrack();
                break;
            default:
                retVal =  new TileIce();

        }// switch
        return retVal;
    }
}

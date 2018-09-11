package de.joekoperski.icemaze;

public class ActionTileStart implements TileAction {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.CONTINUE;
    }
}


class ActionTileIce implements TileAction {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.CONTINUE;
    }
}


class ActionTileFinish implements TileAction {
    @Override
    public MoveResult doAction(Map map, PlayerCharacter playerCharacter) {
        return MoveResult.LEVEL_COMPLETE;
    }
}


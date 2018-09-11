package de.joekoperski.icemaze;

public interface TileAction {
    MoveResult doAction(Map map, PlayerCharacter playerCharacter);
}

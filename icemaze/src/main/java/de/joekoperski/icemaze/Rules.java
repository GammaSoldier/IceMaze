package de.joekoperski.icemaze;

public class Rules {
    int levelWidth;
    int levelHeight;
    private Map theMap;
    private PlayerCharacter thePlayer;

    // REWORK: 11.09.2018 Testlevel
    int level[][] = {
             { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            ,{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 18}
    };

    public Rules() {
    }

    public Map getTheMap() {
        return theMap;
    }


    public PlayerCharacter getThePlayer() {
        return thePlayer;
    }


    // TODO pass a level to this method
    public void initLevel() {
        levelWidth = 10;
        levelHeight = 10;
        theMap = new Map(levelWidth, levelHeight);
        thePlayer = new PlayerCharacter();

        for(int i = 0; i < levelWidth; i++ ) {
            for(int j = 0; j< levelHeight; j++) {
                theMap.setSourceMap(i, j, level[i][j]);
                theMap.setResultMap(i, j, level[i][j]);
            }// for j
        }// for i
        thePlayer.setPosition( theMap.find(TileID.TILE_START));
    }// initLevel
}

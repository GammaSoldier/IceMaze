package de.joekoperski.icemaze;

import java.io.Serializable;

public class Level implements Serializable {
    private int mMap[][];
    private int width;
    private int height;

    public Level(Map map) {
        width = map.getWidth();
        height = map.getHeight();
        mMap = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mMap[i][j] = map.getSourceMap(i, j);
            }// for j
        }// for i
    }


    public Map getMap() {
        Map map = new Map(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map.setSourceMap(i,j,mMap[i][j]);
                map.setResultMap(i,j,mMap[i][j]);
            }// for j
        }// for i
        return map;
    }

}

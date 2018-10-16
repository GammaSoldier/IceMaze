package de.joekoperski.icemaze;

import java.io.Serializable;

public class Level implements Serializable {
    private int map[][];
    private int width;
    private int height;
    private String levelDescription;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public String getLevelDescription() {
        return levelDescription;
    }// getLevelDescription


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setLevelDescription(String mLevelDescription) {
        this.levelDescription = mLevelDescription;
    }// setLevelDescription


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public int getWidth() {
        return width;
    }// getWidth


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setWidth(int width) {
        this.width = width;
    }// setWidth


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public int getHeight() {
        return height;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setHeight(int height) {
        this.height = height;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    Level(Map map) {
        levelDescription = null;
        width = map.getWidth();
        height = map.getHeight();
        this.map = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.map[i][j] = map.getSourceMap(i, j);
            }// for j
        }// for i
    }// Level


    ////////////////////////////////////////////////////////////////////////////////////////////////
    Map getMap() {
        Map map = new Map(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map.setSourceMap(i,j, this.map[i][j]);
                map.setResultMap(i,j, this.map[i][j]);
            }// for j
        }// for i
        return map;
    }// getMap

}

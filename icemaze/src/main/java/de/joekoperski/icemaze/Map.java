package de.joekoperski.icemaze;

import android.graphics.Point;

import java.io.Serializable;

public class Map implements Serializable {

    public static final int INVALID_COORDS = -1;
    private int sourceMap[][];
    private int resultMap[][];
    private int width;
    private int height;


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Map(int width, int height) {
        sourceMap = new int[width][height];
        resultMap = new int[width][height];
        this.width = width;
        this.height = height;
    }// Map


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public int getWidth() {
        return width;
    }// getWidth


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public int getHeight() {
        return height;
    }// getHeight


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setSourceMap(int x, int y, int value) {
        if (x <= width && x >= 0 && y <= height && y >= 0) {
            sourceMap[x][y] = value;
        }// if
    }// setSourceMap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void setResultMap(int x, int y, int value) {
        if (x <= width && x >= 0 && y <= height && y >= 0) {
            resultMap[x][y] = value;
        }// if
    }// setResultMap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public int getSourceMap(int x, int y) {
        if (x <= width && x >= 0 && y <= height && y >= 0) {
            return sourceMap[x][y];
        }// if
        else {
            return TileID.TILE_NO_TILE;
        }// else
    }// getSourceMap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public int getResultMap(int x, int y) {
        if (x <= width && x >= 0 && y <= height && y >= 0) {
            return resultMap[x][y];
        }// if
        else {
            return TileID.TILE_NO_TILE;
        }// else
    }// getResultMap


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void syncMaps() {
        for(int i = 0; i < width; i++ ) {
            for(int j = 0; j< height; j++) {
                resultMap[i][j] = sourceMap[i][j];
            }// for j
        }// for i
    }// syncMaps


    ////////////////////////////////////////////////////////////////////////////////////////////////
    public Point find( int id) {
        Point retVal = new Point(0,0);
        for(int i = 0; i < width; i++ ) {
            for(int j = 0; j< height; j++) {
                if( sourceMap[i][j] == id) {
                    retVal = new Point(i,j);
                    return retVal;
                }
            }// for j
        }// for i
        return retVal;
    }// find
}

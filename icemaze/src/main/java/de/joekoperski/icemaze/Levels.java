/*

Level data is stored in JSON file

a level looks like this:

{
    "description": "Text to describe the level",
    "image": "Image resource name or empty string",
    "map": [
        [1,1,1,2],
        [1,1,1,2],
        [1,1,1,2],
        [1,1,1,2]
    ]
}

the collection of all levels looks like this:

{
    "levels": [
        {
            "description": "Text to describe the level",
            "image": "Image resource name or empty string",
            "map": [
                [1,1,1,2],
                [1,1,1,2],
                [1,1,1,2],
                [1,1,1,2]
            ]
        },

        {
            "description": "Text to describe the level",
            "image": "Image resource name or empty string",
            "map": [
                [1,1,1,2],
                [1,1,1,2],
                [1,1,1,2],
                [1,1,1,2]
            ]
        }
    ]
}



*/


package de.joekoperski.icemaze;

import java.util.ArrayList;
import java.util.logging.Level;

public class Levels {

    private static ArrayList<Level> levelArray;
    private int map[][];

    public Levels() {
        levelArray = new ArrayList<Level>();



//        levelArray.add(new Level());
    }// Levels
}

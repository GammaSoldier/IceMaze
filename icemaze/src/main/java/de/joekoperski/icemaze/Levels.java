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

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;

public class Levels {

    private static ArrayList<Level> levelArray;

    public Levels(String file, Activity context) {
////        levelArray = new ArrayList<>();
//        try {
////            AssetFileDescriptor descriptor = context.getAssets().openFd("levels.json");
////            JsonReader reader = new JsonReader(new FileReader(descriptor.getFileDescriptor()));
//            String myJson=inputStreamToString(mActivity.getResources().openRawResource(R.raw.my_json));
//            JsonReader reader = new JsonReader(new FileReader(new InputStreamReader(context.getAssets().open("levels.jon"))));
//
//            Gson gson = new Gson();
//
//            Type listType = new TypeToken<ArrayList<de.joekoperski.icemaze.Level>>() {
//            }.getType();
//            levelArray = gson.fromJson(reader, listType);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }// try
    }// Levels
}

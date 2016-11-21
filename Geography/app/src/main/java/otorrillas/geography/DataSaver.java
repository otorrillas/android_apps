package otorrillas.geography;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DataSaver extends ActionBarActivity {

    private Map<String, ArrayList<PolygonOptions>> worldMap = new HashMap<>();
    File path = Environment.getExternalStorageDirectory();
    File myfile = new File(path, "/otorrillas.txt");
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_saver);
        //readWorldMap();
        saveWorldMap();
        openWorldMap();
    }

    private void readWorldMap() {
        /*
        ArrayList<PolygonOptions> myPoli = new ArrayList<>();
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(42.569962, 1.78172 ))
                .add(new LatLng(42.509438, 1.723611))  // North of the previous point, but at the same longitude
                .add(new LatLng(42.601944, 1.445833))  // Same latitude, and 30km to the west
                .add(new LatLng(42.569962, 1.78172)) // Same longitude, and 16km to the south
                .fillColor(Color.TRANSPARENT)
                .strokeColor(Color.CYAN);;
        myPoli.add(rectOptions);
        worldMap.put("Andorra", myPoli);
        */
        File file = new File("worldcoordinates.csv");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String splits[], varSplits[];
        try {
            while ((line = br.readLine()) != null) {

                splits = line.split("\"");
                // splits [1] will contain coordinates
                // splits [2] will contain other vars

                /* Coordinates section */
                ArrayList<PolygonOptions> countryPolygons = new ArrayList<>();
                /* Iterate through splits[1] to get the coordinates */
                {

                    PolygonOptions onePoly = new PolygonOptions();
                    ArrayList<LatLng> outerBoundaries = new ArrayList<>();
                    ArrayList<LatLng> innerBoundaries = new ArrayList<>();


                    /* Adding borders and holes to countryPolygon */
                    if(!outerBoundaries.isEmpty())
                        onePoly.addHole(outerBoundaries);
                    if(!innerBoundaries.isEmpty())
                        onePoly.addAll(innerBoundaries);

                    countryPolygons.add(onePoly);
                }
                /* Meta info, like country name */
                varSplits = splits[2].split(",");
                String countryName = varSplits[2];


                /* Adding to worldmap */
                worldMap.put(countryName, countryPolygons);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void saveWorldMap() {
        ArrayList<PolygonOptions> myPoli = new ArrayList<>();
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(42.569962, 1.78172 ))
                .add(new LatLng(42.509438, 1.723611))  // North of the previous point, but at the same longitude
                .add(new LatLng(42.601944, 1.445833))  // Same latitude, and 30km to the west
                .add(new LatLng(42.569962, 1.78172)) // Same longitude, and 16km to the south
                .fillColor(Color.TRANSPARENT)
                .strokeColor(Color.CYAN);;
        myPoli.add(rectOptions);
        worldMap.put("Andorra", myPoli);


        try {
            String json = gson.toJson(worldMap);
            BufferedWriter fw = new BufferedWriter(new FileWriter(myfile));
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map<String, ArrayList<PolygonOptions>> openWorldMap() {
        if(worldMap.isEmpty()) {
            try {

                BufferedReader fr = new BufferedReader(new FileReader(myfile));
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = fr.readLine()) != null) {
                    sb.append(line);
                }
                fr.close();
                String json = sb.toString();

                worldMap = gson.fromJson(json, new TypeToken<Map<String, ArrayList<PolygonOptions>>>() {}.getType());

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return worldMap;
    }


}

package DB;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Models.Scadenza;
import Models.Vehicle;
import tk.nicolazaltron.scadenzeauto.R;

/**
 * Created by luisazurlo on 16/08/2017.
 */

public class JSON_DBPopulator {

    protected static ArrayList<Vehicle> vehiclesArray;

    protected JSONObject jsonObject;
    protected String strJSONValue;
    protected Activity activity;

    protected Vehicle currentVehicle;
    protected Scadenza currentScadenza;
    protected ArrayList<Scadenza> currentScadenzeArray;

    SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");

    JSONManager reader;


    //------------------------------------------------------------------------------------------
    //                               READER + SWITCH FUNCTION
    //------------------------------------------------------------------------------------------
    public JSON_DBPopulator(Activity activity){
        this.activity = activity;

        reader = new JSONManager(activity.getResources(), R.raw.vehicle_info);
        strJSONValue = reader.getString();
        try {
            parseVehiclesJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //------------------------------------------------------------------------------------------
    //                               Vehicles JSON Reading
    //------------------------------------------------------------------------------------------
    public void parseVehiclesJSON() throws JSONException {
        vehiclesArray = new ArrayList<>();
        jsonObject = new JSONObject(strJSONValue);

        FileOutputStream outputStream;

        JSONArray vehiclesArrayObj = jsonObject.getJSONArray(activity.getResources().getString(R.string.jsonParsing_vehicle));

        System.out.println("JSON        ---       " + vehiclesArrayObj.length() + " vehicles found!!");
        for (int i = 0; i < vehiclesArrayObj.length(); i++) {

            currentVehicle = null;
            currentVehicle = new Vehicle();
            currentVehicle.setName(vehiclesArrayObj.getJSONObject(i).getString(activity.getResources().getString(R.string.jsonParsing_name)));

            switch (vehiclesArrayObj.getJSONObject(i).getString(activity.getResources().getString(R.string.jsonParsing_icon))){
                case "car": currentVehicle.setIcon(Vehicle.Icon.car);break;
                case "bike": currentVehicle.setIcon(Vehicle.Icon.bike);break;
                case "motorbike": currentVehicle.setIcon(Vehicle.Icon.motorbike);break;
                case "van": currentVehicle.setIcon(Vehicle.Icon.van);break;
                case "pickup": currentVehicle.setIcon(Vehicle.Icon.pickup);break;
                case "ape": currentVehicle.setIcon(Vehicle.Icon.ape);break;
                case "troc": currentVehicle.setIcon(Vehicle.Icon.troc);break;
                case "boat": currentVehicle.setIcon(Vehicle.Icon.boat);break;
                default: currentVehicle.setIcon(Vehicle.Icon.car);break;
            }

            currentScadenzeArray = new ArrayList<Scadenza>();
            JSONArray scadenzeArrayObj = vehiclesArrayObj.getJSONObject(i).getJSONArray(activity.getResources().getString(R.string.jsonParsing_scadenze));
            for (int j = 0; j < scadenzeArrayObj.length(); j++) {
                currentScadenza = new Scadenza();
                currentScadenza.setName((String) scadenzeArrayObj.getJSONObject(j).get(activity.getResources().getString(R.string.jsonParsing_name_scadenza)));
                try {
                    currentScadenza.setScadenza(dateFormat.parse((String) scadenzeArrayObj.getJSONObject(j).get(activity.getResources().getString(R.string.jsonParsing_date))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                currentScadenzeArray.add(currentScadenza);
            }
            currentVehicle.setScadenze(currentScadenzeArray);
            vehiclesArray.add(currentVehicle);
        }
    }


    //------------------------------------------------------------------------------------------
    //                                      Getters
    //------------------------------------------------------------------------------------------

    public static ArrayList<Vehicle> getVehicles(){
        return vehiclesArray;
    }

}
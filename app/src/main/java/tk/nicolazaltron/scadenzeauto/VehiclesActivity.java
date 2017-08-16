package tk.nicolazaltron.scadenzeauto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

public class VehiclesActivity extends AppCompatActivity {

    private Activity activity;
    private ListView vehicleListView;
    private static ArrayList<Vehicle> vehicleList;
    private static VehicleAdapter vehicleAdapter;

    public static int currentEditPosition = 0;
    public static int currentDeletePosition = 0;
    public static String currentEditName = "";

    private static FragmentManager fm;

    public VehiclesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        vehicleListView = (ListView) findViewById(R.id.vehiclesListView);
        vehicleList = new ArrayList<>();

        vehicleAdapter = new VehicleAdapter(this, R.layout.vehicle_row,vehicleList);
        vehicleListView.setAdapter(vehicleAdapter);
        vehicleListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                currentEditPosition = pos;
                currentEditName = vehicleList.get(pos).getName();
                showEditDialog();
                return true;
            }
        });

        vehicleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                Intent showScadenzaIntent = new Intent(activity, ShowScadenze.class);
                activity.startActivity(showScadenzaIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vehicles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showAddDialog();
        }
        else if (id == R.id.action_add) {
            showAddDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notifyChanges();
    }

    private void showAddDialog(){
        VehicleAddDialog addListItemDialog = new VehicleAddDialog();
        addListItemDialog.show(fm, "");
    }

    public static void showEditDialog(){
        VehicleEditDialog addListItemDialog = new VehicleEditDialog();
        addListItemDialog.show(fm, "");
    }

    public static void addVehicle(String name){
        vehicleList.add(new Vehicle(name));
    }

    public static void editVehicleName(String name){
        vehicleList.get(currentEditPosition).setName(name);
    }

    public static void editVehicle(int pos){
        currentEditPosition = pos;
        currentEditName = vehicleList.get(pos).getName();
        showEditDialog();
    }

    public static void deleteVehicleDialog(int position){
        currentDeletePosition = position;
        DeleteDialog deleteItemDialog = new DeleteDialog();
        deleteItemDialog.show(fm, "");
    }

    public static void deleteVehicle(){
        vehicleList.remove(currentDeletePosition);
    }

    public static void notifyChanges(){
        vehicleAdapter.notifyDataSetChanged();
    }
}

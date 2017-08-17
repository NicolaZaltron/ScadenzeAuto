package tk.nicolazaltron.scadenzeauto.Vehicles;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import DB.JSON_DBPopulator;
import Models.Vehicle;
import tk.nicolazaltron.scadenzeauto.R;
import tk.nicolazaltron.scadenzeauto.SharedActivities.DeleteDialog;

public class VehiclesActivity extends AppCompatActivity {

    private static Activity activity;
    private ListView vehicleListView;
    /*private*/ public static ArrayList<Vehicle> vehicleList;
    private static VehicleAdapter vehicleAdapter;

    public static int currentEditPosition = 0;
    public static int currentDeletePosition = 0;
    public static String currentEditName = "";
    public static boolean delete_dialog2 = false;

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

        activity = this;
        new JSON_DBPopulator(activity);
        vehicleList = JSON_DBPopulator.getVehicles();

        vehicleAdapter = new VehicleAdapter(this, R.layout.vehicle_row,vehicleList);
        vehicleListView.setAdapter(vehicleAdapter);
        notifyChanges();

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
        AddVehicleDialog addListItemDialog = new AddVehicleDialog();
        addListItemDialog.show(fm, "");
    }

    public static void showEditDialog(){
        VehicleEditDialog addListItemDialog = new VehicleEditDialog();
        addListItemDialog.show(fm, "");
    }

    public static void showDeleteDialog(){
        delete_dialog2 = true;
        DeleteDialog deleteItemDialog = new DeleteDialog();
        deleteItemDialog.show(fm, "");
    }

    public static void addVehicle(String name){
        vehicleList.add(new Vehicle(name));
    }

    public static void editVehicle(int pos){
        currentEditPosition = pos;
        currentEditName = vehicleList.get(pos).getName();
        showEditDialog();
    }

    public static void deleteVehicle(int pos){
        currentDeletePosition = pos;
        showDeleteDialog();
    }

    public static void editVehicleName_confirmed(String name){
        vehicleList.get(currentEditPosition).setName(name);
    }

    public static void deleteVehicle_confirmed(){
        vehicleList.remove(currentDeletePosition);
        notifyChanges();
    }

/*    public void openVehicle(int pos){
        Intent showScadenzeIntent = new Intent(getBaseContext(), DeadlinesActivity.class);
        showScadenzeIntent.putExtra("EXTRA_SESSION_ID", (Parcelable) vehicleList.get(pos));
        startActivity(showScadenzeIntent);
    }*/

    public static void notifyChanges(){
        vehicleAdapter.notifyDataSetChanged();
    }
}

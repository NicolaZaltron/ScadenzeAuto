package tk.nicolazaltron.scadenzeauto;

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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

public class VehiclesActivity extends AppCompatActivity {

    private ListView vehicleListView;
    private static ArrayList<Vehicle> vehicleList;
    private static VehicleAdapter vehicleAdapter;
    private static int currentEditPosition = 0;
    public static String currentEditName = "";

    public VehiclesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehicleListView = (ListView) findViewById(R.id.vehiclesListView);
        vehicleList = new ArrayList<>();

        vehicleAdapter = new VehicleAdapter(this, R.layout.vehicle_row,vehicleList);
        vehicleListView.setAdapter(vehicleAdapter);
        vehicleListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                showEditDialog();
                currentEditPosition = pos;
                currentEditName = vehicleList.get(pos).getName();
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
        FragmentManager fm = getSupportFragmentManager();
        VehicleAddDialog addListItemDialog = new VehicleAddDialog();
        addListItemDialog.show(fm, "");
        notifyChanges();
    }

    private void showEditDialog(){
        FragmentManager fm = getSupportFragmentManager();
        VehicleEditDialog addListItemDialog = new VehicleEditDialog();
        addListItemDialog.show(fm, "");
            notifyChanges();
    }

    public static void addVehicle(String name){
        vehicleList.add(new Vehicle(name));
    }

    public static void editVehicle(String name){
        vehicleList.get(currentEditPosition).setName(name);
    }

    public static void notifyChanges(){
        vehicleAdapter.notifyDataSetChanged();
    }
}

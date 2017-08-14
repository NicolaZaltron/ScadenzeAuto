package tk.nicolazaltron.scadenzeauto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class VehiclesActivity extends AppCompatActivity {

    private ListView vehicleListView;
    private ArrayList<Vehicle> vehicleList;
    private ArrayAdapter<Vehicle> vehicleAdapter;

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

        vehicleAdapter=new ArrayAdapter<Vehicle>(this, R.layout.vehicle_row,vehicleList);
        vehicleListView.setAdapter(vehicleAdapter);

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAddDialog(){
        FragmentManager fm = getSupportFragmentManager();
        AddVehicleDialog addListItemDialog = new AddVehicleDialog();
        addListItemDialog.show(fm, "");
    }

}

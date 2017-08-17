package tk.nicolazaltron.scadenzeauto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import Models.Scadenza;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class DeadlinesActivity extends AppCompatActivity {

    private ListView scadenzeListView;
    private static ArrayList<Scadenza> scadenzeList;
    private static DeadlineAdapter deadlineAdapter;
    private static int currentEditPosition = 0;

    public DeadlinesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scadenze);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scadenzeListView = (ListView) findViewById(R.id.scadenzeListView);
        scadenzeList = new ArrayList<>();

        deadlineAdapter = new DeadlineAdapter(this, R.layout.scadenza_row,scadenzeList);
        scadenzeListView.setAdapter(deadlineAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });

        deadlineAdapter = new DeadlineAdapter(this, R.layout.vehicle_row,scadenzeList);
        scadenzeListView.setAdapter(deadlineAdapter);

/*
        scadenzeListView.setOnItemLongClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                showEditDialog();
                currentEditPosition = pos;
                currentEditName = scadenzeList.get(pos).getName();
            }
        });*/

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

    private void showAddDialog(){
        FragmentManager fm = getSupportFragmentManager();
        VehicleAddDialog addListItemDialog = new VehicleAddDialog();
        addListItemDialog.show(fm, "");
        deadlineAdapter.notifyDataSetChanged();
    }

    private void editAddDialog(){
        FragmentManager fm = getSupportFragmentManager();
        VehicleEditDialog addListItemDialog = new VehicleEditDialog();
        addListItemDialog.show(fm, "");
        deadlineAdapter.notifyDataSetChanged();
    }

    public static void addScadenza(String name, Date date){
        deadlineAdapter.add(new Scadenza(name, date));
    }

/*    public static void editScadenza(String name){
        deadlineAdapter.get(currentEditPosition).setName(name);
    }*/
}

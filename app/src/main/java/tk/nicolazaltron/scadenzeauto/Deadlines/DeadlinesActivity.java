package tk.nicolazaltron.scadenzeauto.Deadlines;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import Models.Deadline;
import Models.Vehicle;
import tk.nicolazaltron.scadenzeauto.Deadlines.DeadlineAdapter;
import tk.nicolazaltron.scadenzeauto.Deadlines.AddScadenzaDialog;
import tk.nicolazaltron.scadenzeauto.SharedActivities.DeleteDialog;
import tk.nicolazaltron.scadenzeauto.Deadlines.EditScadenzaDialog;
import tk.nicolazaltron.scadenzeauto.R;
import tk.nicolazaltron.scadenzeauto.Vehicles.VehiclesActivity;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class DeadlinesActivity extends AppCompatActivity {

    private ListView scadenzeListView;
    private static ArrayList<Deadline> scadenzeList;
    private static DeadlineAdapter deadlineAdapter;

    private static int vehiclePosition;
    private static Vehicle vehicle;
    private static int currentEditPosition = 0;
    public static String currentEditName = "";
    public static Date currentEditDate = null;
    private static FragmentManager fm;
    public static boolean delete_dialog = false;

    public DeadlinesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scadenze);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vehiclePosition = this.getIntent().getIntExtra("position", -1);
        vehicle = VehiclesActivity.vehicleList.get(vehiclePosition);

        scadenzeListView = (ListView) findViewById(R.id.scadenzeListView);
        scadenzeList = vehicle.getScadenze();

        deadlineAdapter = new DeadlineAdapter(this, R.layout.scadenza_row,scadenzeList);
        scadenzeListView.setAdapter(deadlineAdapter);

        fm = getSupportFragmentManager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });

        deadlineAdapter = new DeadlineAdapter(this, R.layout.scadenza_row,scadenzeList);
        scadenzeListView.setAdapter(deadlineAdapter);

        scadenzeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                currentEditPosition = pos;
                currentEditName = scadenzeList.get(pos).getName();
                currentEditDate = scadenzeList.get(pos).getDateScadenza();
                editAddDialog();
                return true;
            }
        });


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
        getMenuInflater().inflate(R.menu.menu_scadenze, menu);
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
        AddScadenzaDialog addListItemDialog = new AddScadenzaDialog();
        addListItemDialog.show(fm, "");
        deadlineAdapter.notifyDataSetChanged();
    }

    private static void editAddDialog(){
        EditScadenzaDialog editListItemDialog = new EditScadenzaDialog();
        editListItemDialog.show(fm, "");
    }

    public static void editScadenza(int pos){
        currentEditPosition = pos;
        currentEditName = scadenzeList.get(pos).getName();
        currentEditDate = scadenzeList.get(pos).getDateScadenza();
        editAddDialog();
    }

    public static void addScadenza(String name, Date date){
        deadlineAdapter.add(new Deadline(name, date));
    }

    public static void deleteDeadline(int pos){
        currentEditPosition = pos;
        showDeleteDialog();
    }

    public static void showDeleteDialog(){
        delete_dialog = true;
        DeleteDialog deleteItemDialog = new DeleteDialog();
        deleteItemDialog.show(fm, "");
    }

/*    public static void editScadenza(String name){
        deadlineAdapter.get(currentEditPosition).setName(name);
    }*/

    public static void editScadenzaName_confirmed(String name){
        scadenzeList.get(currentEditPosition).setName(name);
    }

    public static void editScadenzaDate_confirmed(Date date){
        scadenzeList.get(currentEditPosition).setScadenza(date);
    }

    public static void deleteDeadline_confirmed(){
        scadenzeList.remove(currentEditPosition);
        deadlineAdapter.notifyDataSetChanged();
    }
}

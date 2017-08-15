package tk.nicolazaltron.scadenzeauto;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class ShowScadenzeAdapter extends ArrayAdapter {
    public ShowScadenzeAdapter(@NonNull Context context, @LayoutRes int resource, List vehicleScadenze) {
        super(context, resource, vehicleScadenze);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Vehicle vehicle = (Vehicle) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_scadenze_row, parent, false);
        }
        // Lookup view for data population
        TextView textView_scadenzaName = (TextView) convertView.findViewById(R.id.textView_vehicleName);
        // Populate the data into the template view using the data object
        textView_scadenzaName.setText(vehicle.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
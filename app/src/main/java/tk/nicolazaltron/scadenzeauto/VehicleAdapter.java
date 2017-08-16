package tk.nicolazaltron.scadenzeauto;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nicola Zaltron on 8/15/2017.
 */

public class VehicleAdapter extends ArrayAdapter {
    public VehicleAdapter(@NonNull Context context, @LayoutRes int resource, List vehicles) {
        super(context, resource, vehicles);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Vehicle vehicle = (Vehicle) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_row, parent, false);
        }
        // Lookup view for data population
        TextView textView_vehicleName = (TextView) convertView.findViewById(R.id.textView_vehicleName);
        // Populate the data into the template view using the data object
        textView_vehicleName.setText(vehicle.getName());

        ImageButton buttonDelete = (ImageButton) convertView.findViewById(R.id.button_vehicleDelete);
        ImageButton buttonEdit = (ImageButton) convertView.findViewById(R.id.button_vehicleEdit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                                     VehiclesActivity.editVehicle(position);
                                          }
                                      }
        );
        
        // Return the completed view to render on screen
        return convertView;
    }
}

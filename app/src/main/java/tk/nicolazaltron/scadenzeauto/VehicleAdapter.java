package tk.nicolazaltron.scadenzeauto;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import Models.Vehicle;

/**
 * Created by Nicola Zaltron on 8/15/2017.
 */

public class VehicleAdapter extends ArrayAdapter {
    public VehicleAdapter(@NonNull Context context, @LayoutRes int resource, List vehicles) {
        super(context, resource, vehicles);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Vehicle vehicle = (Vehicle) getItem(position);
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
        ImageButton buttonVehicleImage = (ImageButton) convertView.findViewById(R.id.button_vehicleImage);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                                     VehiclesActivity.editVehicle(position);
                                          }
                                      }
        );

        buttonDelete.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              VehiclesActivity.deleteVehicle(position);
                                          }
                                      }
        );

        buttonVehicleImage.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                /*VehiclesActivity.openVehicle(position);*/
                                                Intent showScadenzeIntent = new Intent(getContext(), DeadlinesActivity.class);

                                                //Bundle bundle = new Bundle();
                                                //bundle.putSerializable("vehicle", vehicle);

                                                //showScadenzeIntent.putExtras(bundle);
                                                showScadenzeIntent.putExtra("position", position);
                                                getContext().startActivity(showScadenzeIntent);
                                            }
                                        }
        );

        textView_vehicleName.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      /*VehiclesActivity.openVehicle(position);*/
                                                  }
                                              }
        );

        switch (vehicle.getIcon()){
            case car: buttonVehicleImage.setImageResource(R.drawable.car);break;
            case bike: buttonVehicleImage.setImageResource(R.drawable.bike);break;
            case motorbike: buttonVehicleImage.setImageResource(R.drawable.motorbike);break;
            case van: buttonVehicleImage.setImageResource(R.drawable.van);break;
            case pickup: buttonVehicleImage.setImageResource(R.drawable.pickup);break;
            case ape: buttonVehicleImage.setImageResource(R.drawable.ape);break;
            case troc: buttonVehicleImage.setImageResource(R.drawable.troc);break;
            case boat: buttonVehicleImage.setImageResource(R.drawable.boat);break;
            default: buttonVehicleImage.setImageResource(R.drawable.car);break;
        }

        
        // Return the completed view to render on screen
        return convertView;
    }
}

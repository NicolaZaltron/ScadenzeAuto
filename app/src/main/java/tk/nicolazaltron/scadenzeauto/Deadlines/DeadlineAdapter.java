package tk.nicolazaltron.scadenzeauto.Deadlines;

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

import Models.Deadline;
import tk.nicolazaltron.scadenzeauto.R;

/**
 * Created by luisazurlo on 17/08/2017.
 */

public class DeadlineAdapter extends ArrayAdapter {
    public DeadlineAdapter(@NonNull Context context, @LayoutRes int resource, List vehicles) {
        super(context, resource, vehicles);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Deadline scadenza = (Deadline) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.scadenza_row, parent, false);
        }
        // Lookup view for data population
        TextView textView_scadenzaName = (TextView) convertView.findViewById(R.id.textView_scadenzaName);
        TextView textView_scadenzaDate = (TextView) convertView.findViewById(R.id.textView_scadenzaDate);
        // Populate the data into the template view using the data object
        textView_scadenzaName.setText(scadenza.getName());
        textView_scadenzaDate.setText(scadenza.getDateScadenza().toString());

        ImageButton buttonDelete = (ImageButton) convertView.findViewById(R.id.button_scadenzaDelete);
        ImageButton buttonEdit = (ImageButton) convertView.findViewById(R.id.button_scadenzaEdit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              DeadlinesActivity.editScadenza(position);
                                          }
                                      }
        );

        buttonDelete.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                DeadlinesActivity.deleteDeadline(position);
                                            }
                                        }
        );

/*      textView_vehicleName.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        VehiclesActivity.openVehicle(position);
                                                    }
                                                }
        );*/

        // Return the completed view to render on screen
        return convertView;
    }


}
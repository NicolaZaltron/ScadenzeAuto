package tk.nicolazaltron.scadenzeauto.SharedActivities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tk.nicolazaltron.scadenzeauto.R;
import tk.nicolazaltron.scadenzeauto.Deadlines.DeadlinesActivity;
import tk.nicolazaltron.scadenzeauto.Vehicles.VehiclesActivity;

/**
 * Created by luisazurlo on 16/08/2017.
 */

public class DeleteDialog extends DialogFragment {

    private Button saveButton;
    private Button cancelButton;

    public DeleteDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_fragment, container);

        saveButton = (Button) view.findViewById(R.id.yes);
        cancelButton = (Button) view.findViewById(R.id.no);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inserire codice per eliminare leemento
                if (VehiclesActivity.delete_dialog2 == true) {
                    VehiclesActivity.delete_dialog2 = false;
                    VehiclesActivity.deleteVehicle_confirmed();
                    getDialog().dismiss();
                } else if (DeadlinesActivity.delete_dialog == true){
                    DeadlinesActivity.delete_dialog = false;
                    DeadlinesActivity.deleteDeadline_confirmed();
                    getDialog().dismiss();
                }

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        return view;
    }

}
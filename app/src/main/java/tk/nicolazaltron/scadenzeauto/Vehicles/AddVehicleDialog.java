package tk.nicolazaltron.scadenzeauto.Vehicles;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import Models.Vehicle;
import tk.nicolazaltron.scadenzeauto.R;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class AddVehicleDialog extends DialogFragment implements TextView.OnEditorActionListener{

    public interface AddItemDialogListener {
        void onFinishAddDialog(String inputText);
    }

    private EditText vehicleNameEditText;
    private Spinner vehicleSpinner;
    private Button saveButton;
    private Button cancelButton;

    static int spinnerPosition = 0;

    public AddVehicleDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view               = inflater.inflate(R.layout.add_vehicle_fragment, container);
        vehicleNameEditText     = (EditText) view.findViewById(R.id.vehicle_name);
        vehicleSpinner          = (Spinner) view.findViewById(R.id.spinner);
        saveButton              = (Button) view.findViewById(R.id.save);
        cancelButton            = (Button) view.findViewById(R.id.cancel);

        // Show soft keyboard automatically
        vehicleNameEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        vehicleSpinner.setAdapter(new VehicleSpinnerAdapter());
        vehicleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_vehicle = vehicleNameEditText.getText().toString();

                if(temp_vehicle.equals("")){
                    Toast.makeText(getActivity(), R.string.insert_error , Toast.LENGTH_SHORT).show();
                } else {
                    Vehicle.Icon icon;
                    switch (spinnerPosition){
                        case 0: icon= Vehicle.Icon.car; break;
                        case 1: icon= Vehicle.Icon.scooter; break;
                        case 2: icon= Vehicle.Icon.motorbike; break;
                        case 3: icon= Vehicle.Icon.van; break;
                        case 4: icon= Vehicle.Icon.pickup; break;
                        case 5: icon= Vehicle.Icon.ape; break;
                        case 6: icon= Vehicle.Icon.troc; break;
                        case 7: icon= Vehicle.Icon.bike; break;
                        case 8: icon= Vehicle.Icon.boat; break;
                        default: icon= Vehicle.Icon.car; break;
                    }
                    VehiclesActivity.addVehicle(temp_vehicle, icon);
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

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            AddItemDialogListener activity = (AddItemDialogListener) getActivity();
            activity.onFinishAddDialog(vehicleNameEditText.getText().toString());
            activity.onFinishAddDialog(vehicleSpinner.getSelectedItem().toString()); //settare immagine
            this.dismiss();
            return true;
        }
        return false;
    }
}


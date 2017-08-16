package tk.nicolazaltron.scadenzeauto;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class VehicleAddDialog extends DialogFragment implements TextView.OnEditorActionListener{

    public interface AddItemDialogListener {
        void onFinishAddDialog(String inputText);
    }

    private EditText vehicleNameEditText;
    private Spinner vehicleSpinner;
    private Button saveButton;
    private Button cancelButton;

    public VehicleAddDialog() {
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



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_vehicle = vehicleNameEditText.getText().toString();

                if(temp_vehicle.equals("")){
                    Toast.makeText(getActivity(), R.string.insert_error , Toast.LENGTH_SHORT).show();
                } else {
                    VehiclesActivity.addVehicle(temp_vehicle);
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


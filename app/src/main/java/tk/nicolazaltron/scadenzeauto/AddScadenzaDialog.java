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

import java.text.ParseException;
import java.util.Date;

import DB.JSON_DBPopulator;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class AddScadenzaDialog extends DialogFragment implements TextView.OnEditorActionListener{

    public interface AddItemDialogListener {
        void onFinishAddDialog(String inputText, Date date);
    }

    private EditText scadenzaEditText;
    private EditText scadenzaDateEditText;
    private Button saveButton;
    private Button cancelButton;

    public AddScadenzaDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view               = inflater.inflate(R.layout.add_vehicle_fragment, container);
        scadenzaEditText     = (EditText) view.findViewById(R.id.scadenza_name);
        scadenzaDateEditText     = (EditText) view.findViewById(R.id.scadenza_date);
        saveButton              = (Button) view.findViewById(R.id.save);
        cancelButton            = (Button) view.findViewById(R.id.cancel);

        // Show soft keyboard automatically
        scadenzaEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        scadenzaDateEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_scadenza_name = scadenzaEditText.getText().toString();
                Date temp_scadenza_date = null;
                try {
                    temp_scadenza_date = JSON_DBPopulator.dateFormat.parse(scadenzaDateEditText.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(temp_scadenza_name.equals("")||temp_scadenza_date.equals("")){
                    Toast.makeText(getActivity(), R.string.insert_error , Toast.LENGTH_SHORT).show();
                } else {
                    ShowScadenze.addScadenza(temp_scadenza_name, temp_scadenza_date);
                    //VehiclesActivity.addVehicle(temp_vehicle);
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
            AddScadenzaDialog.AddItemDialogListener activity = (AddScadenzaDialog.AddItemDialogListener) getActivity();
            Date date = new Date();
            try {
                date = (JSON_DBPopulator.dateFormat.parse(scadenzaDateEditText.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            activity.onFinishAddDialog(scadenzaEditText.getText().toString(), date);

            this.dismiss();
            return true;
        }
        return false;
    }
}


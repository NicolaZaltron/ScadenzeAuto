package tk.nicolazaltron.scadenzeauto.Deadlines;

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
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import DB.JSON_DBPopulator;
import tk.nicolazaltron.scadenzeauto.R;
import tk.nicolazaltron.scadenzeauto.Vehicles.VehiclesActivity;

/**
 * Created by luisazurlo on 15/08/2017.
 */

public class EditScadenzaDialog extends DialogFragment implements TextView.OnEditorActionListener{

    public interface AddItemDialogListener {
        void onFinishAddDialog(String inputText);
    }

    private EditText scadenzaNameEditText;
    private EditText scadenzaDateEditText;
    private Button saveButton;
    private Button cancelButton;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");

    public EditScadenzaDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_scadenza_fragment, container);
        scadenzaNameEditText  = (EditText) view.findViewById(R.id.scadenza_name);
        scadenzaDateEditText  = (EditText) view.findViewById(R.id.scadenza_date);

        // Show soft keyboard automatically
        scadenzaNameEditText.setText(DeadlinesActivity.currentEditName);
        scadenzaNameEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        scadenzaDateEditText.setText((DeadlinesActivity.currentEditDate).toString());

        saveButton      = (Button) view.findViewById(R.id.save);
        cancelButton    = (Button) view.findViewById(R.id.cancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_scadenza = scadenzaNameEditText.getText().toString();
                String temp_scadenza_date =  scadenzaDateEditText.getText().toString();

                if(temp_scadenza.equals("")||temp_scadenza_date.equals("")){
                    Toast.makeText(getActivity(), R.string.insert_error , Toast.LENGTH_SHORT).show();
                } else {
                    DeadlinesActivity.editScadenzaName_confirmed(temp_scadenza);
                    try {
                        DeadlinesActivity.editScadenzaDate_confirmed(JSON_DBPopulator.dateFormat.parse(temp_scadenza_date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DeadlinesActivity.notifyChanges();
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
            EditScadenzaDialog.AddItemDialogListener activity = (EditScadenzaDialog.AddItemDialogListener) getActivity();
            activity.onFinishAddDialog(scadenzaNameEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
}


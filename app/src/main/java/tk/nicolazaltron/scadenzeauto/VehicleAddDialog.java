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

public class AddVehicleDialog extends DialogFragment implements TextView.OnEditorActionListener{

    public interface AddItemDialogListener {
        void onFinishAddDialog(String inputText);
    }

    private EditText vehicleNameEditText;
//    private EditText quantityEditText;
//    private Spinner unitSpinner;
    private Button saveButton;
    private Button cancelButton;

    public AddVehicleDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_vehicle_fragment, container);
        vehicleNameEditText  = (EditText) view.findViewById(R.id.vehicle_name);
//        quantityEditText    = (EditText) view.findViewById(R.id.txt_quantity);
//        unitSpinner         = (Spinner) view.findViewById(R.id.spinner);

        // Show soft keyboard automatically
        vehicleNameEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        //ingredientEditText.setOnEditorActionListener(this);
        saveButton      = (Button) view.findViewById(R.id.save);
        cancelButton    = (Button) view.findViewById(R.id.cancel);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp_vehicle = vehicleNameEditText.getText().toString();
//                String temp_quantity = quantityEditText.getText().toString();

                if(temp_vehicle.equals("")){
                    Toast.makeText(getActivity(), R.string.insert_error , Toast.LENGTH_SHORT).show();
                }/* else {
                    Double temp_doubleQuantity;
                    try {
                        temp_doubleQuantity = Double.parseDouble(temp_quantity);
                    } catch (NumberFormatException e) {
                        temp_doubleQuantity = -1.0;
                    } catch (NullPointerException e) {
                        temp_doubleQuantity = -1.0;
                    }
                    if (temp_doubleQuantity == -1.0) {
                        Toast.makeText(getActivity(), "Insert a valid quantity, please", Toast.LENGTH_SHORT).show();
                    } else {
                        if(ShoppingList.getIngredientsQuantityArray().add(
                                new IngredientQuantity(
                                        temp_vehicle,
                                        (float) Double.parseDouble(quantityEditText.getText().toString()),
                                        unitSpinner.getSelectedItem().toString()))){
                            //mRecyclerView.scrollToPosition(position);
                            ShoppingList.adjustListDialog(ShoppingList.getIngredientsQuantityArray());
                            ShoppingList.listAdapter.notifyDataSetChanged();
                            ShoppingList.onNotifiedListChanged();
                            getDialog().dismiss();
                        } else{
                            Toast.makeText(getActivity(), "Error occurred while saving ingredient", Toast.LENGTH_SHORT).show();
                        }
                    }
                }*/
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
/*            activity.onFinishAddDialog(quantityEditText.getText().toString());
            activity.onFinishAddDialog(unitSpinner.getSelectedItem().toString());*/
            this.dismiss();
            return true;
        }
        return false;
    }
}


package tk.nicolazaltron.scadenzeauto.Vehicles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.lang.reflect.Array;

import tk.nicolazaltron.scadenzeauto.R;

/**
 * Created by Nicola Zaltron on 8/27/2017.
 */

public class VehicleSpinnerAdapter extends BaseAdapter{

    private int[] vehicles = {R.drawable.car, R.drawable.scooter, R.drawable.motorbike, R.drawable.van, R.drawable.pickup, R.drawable.ape, R.drawable.troc, R.drawable.bike, R.drawable.boat};

    @Override
    public int getCount() {
        return vehicles.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder vehicleViewHolder;
        if (convertView == null) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_spinner_row, parent, false);

        vehicleViewHolder = new ViewHolder();
        vehicleViewHolder.imageViewVehicle = (ImageView) itemView.findViewById(R.id.spinnerImage);
        itemView.setTag(vehicleViewHolder);
        } else  {
            vehicleViewHolder = (ViewHolder) itemView.getTag();
        }

        vehicleViewHolder.imageViewVehicle.setImageDrawable(parent.getResources().getDrawable(vehicles[position]));

        return itemView;
    }

    private static class ViewHolder{
        ImageView imageViewVehicle;
    }
}

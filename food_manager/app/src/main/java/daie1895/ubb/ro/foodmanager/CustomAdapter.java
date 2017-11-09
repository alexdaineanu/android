package daie1895.ubb.ro.foodmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by adaineanu on 11/6/2017.
 */

public class CustomAdapter extends ArrayAdapter<Recipe> {

    private ArrayList<Recipe> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtVersion;
        ImageView info;
    }

    public CustomAdapter(ArrayList<Recipe> data, Context context) {
        super(context, R.layout.activity_row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Recipe dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.nameEditText);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.recipe);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getRecipe());
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
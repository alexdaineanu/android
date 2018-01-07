package daie1895.ubb.ro.foodmanager.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.List;

import daie1895.ubb.ro.foodmanager.Domain.Recipe;
import daie1895.ubb.ro.foodmanager.R;

/**
 * Created by adaineanu on 1/7/2018.
 */

public class RecipeList extends ArrayAdapter<Recipe> {
    private Activity context;
    private List<Recipe> recipes;
    DatabaseReference databaseReference;
    EditText editText;

    public RecipeList(@NonNull Activity context, List<Recipe> recipes, DatabaseReference databaseReference, EditText editText) {
        super(context, R.layout.activity_row_item, recipes);
        this.context = context;
        this.recipes = recipes;
        this.databaseReference = databaseReference;
        this.editText = editText;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_row_item, null, true);

        TextView textView1 = (TextView) listViewItem.findViewById(R.id.rowItemName);
        TextView textView2 = (TextView) listViewItem.findViewById(R.id.rowItemApproved);

        final Recipe recipe = recipes.get(pos);
        textView1.setText(recipe.getName());
        if(recipe.getApproved().equals(true)){
            textView2.setText("Approved");
        }
        return listViewItem;
    }
}

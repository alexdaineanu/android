package daie1895.ubb.ro.foodmanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import daie1895.ubb.ro.foodmanager.Adapters.CustomAdapter;
import daie1895.ubb.ro.foodmanager.Database.RecipeDatabase;
import daie1895.ubb.ro.foodmanager.Domain.Recipe;

public class MyRecipesActivity extends AppCompatActivity {

    final Integer MY_RECIPES_ACTIVITY_REQUEST_CODE = 0;
    final Integer ADD_ACTIVITY_REQUEST_CODE = 1;
    private List<Recipe> recipes;
    private ArrayList<Recipe> recipeArrayList = new ArrayList<>();
    ListView listView;
    public CustomAdapter adapter;
    private RecipeDatabase db;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        listView = (ListView) findViewById(R.id.recipesListView);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db = Room.databaseBuilder(getApplicationContext(),
                        RecipeDatabase.class, "recipe").build();
                recipes = db.recipeDao().getAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                recipeArrayList.addAll(recipes);
                adapter = new CustomAdapter(recipeArrayList, getApplicationContext());
                listView.setAdapter(adapter);
            }
        }.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipeArrayList.get(position);
                Intent intent = new Intent(MyRecipesActivity.this, EditRecipeActivity.class);
                intent.putExtra("name", recipe.getName());
                intent.putExtra("recipe", recipe.getRecipe());
                intent.putExtra("position", (Integer) position);
                startActivityForResult(intent, MY_RECIPES_ACTIVITY_REQUEST_CODE);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(MyRecipesActivity.this).create();
                alertDialog.setTitle("Deleting");
                alertDialog.setMessage("Recipe deleted");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        Recipe recipe = recipeArrayList.get(position);
                        db.recipeDao().delete(recipe);
                        recipes.remove(position);
                        recipeArrayList.remove(position);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        adapter.notifyDataSetChanged();
                    }
                }.execute();

                return true;
            }
        });

    }
    @SuppressLint("StaticFieldLeak")
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_RECIPES_ACTIVITY_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String recipe = data.getStringExtra("recipe");
                recipes.get(data.getIntExtra("position", 0)).setName(name);
                recipes.get(data.getIntExtra("position", 0)).setRecipe(recipe);
                adapter.notifyDataSetChanged();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        db.recipeDao().updateRecipes(recipes.get(data.getIntExtra("position",0)));
                        return null;
                    }
                }.execute();
            }
        if(requestCode == ADD_ACTIVITY_REQUEST_CODE)
            if (resultCode == RESULT_OK){
                recipeArrayList.add(new Recipe(data.getStringExtra("name"), data.getStringExtra("recipe"), null));
                recipes.add(new Recipe(data.getStringExtra("name"), data.getStringExtra("recipe"), null));
                adapter.notifyDataSetChanged();
        }
    }

    public void createNewRecipe(View view){
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivityForResult(intent, ADD_ACTIVITY_REQUEST_CODE);
    }
}

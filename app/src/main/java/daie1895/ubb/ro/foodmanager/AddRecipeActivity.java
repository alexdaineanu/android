package daie1895.ubb.ro.foodmanager;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import daie1895.ubb.ro.foodmanager.Database.RecipeDatabase;
import daie1895.ubb.ro.foodmanager.Domain.Recipe;

public class AddRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }

    @SuppressLint("StaticFieldLeak")
    public void sendRecipe(View view) {
        EditText recipeNameInput = findViewById(R.id.recipeNameInput);
        EditText recipeContentInput = findViewById(R.id.recipeContentInput);
        String name = recipeNameInput.getText().toString();
        String recipe_content = recipeContentInput.getText().toString();
        final Recipe recipe = new Recipe(name, recipe_content, null);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                RecipeDatabase db = Room.databaseBuilder(getApplicationContext(),
                        RecipeDatabase.class, "recipe").build();
                db.recipeDao().insertAll(recipe);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                EditText recipeNameInput = findViewById(R.id.recipeNameInput);
                EditText recipeContentInput = findViewById(R.id.recipeContentInput);
                Intent intent = new Intent();
                intent.putExtra("name", recipeNameInput.getText().toString());
                intent.putExtra("recipe", recipeContentInput.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute();
    }
}

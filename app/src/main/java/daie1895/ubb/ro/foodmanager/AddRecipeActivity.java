package daie1895.ubb.ro.foodmanager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import daie1895.ubb.ro.foodmanager.Domain.Recipe;

public class AddRecipeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
    }

    @SuppressLint("StaticFieldLeak")
    public void addRecipe(View view) {
        EditText recipeNameInput = findViewById(R.id.recipeNameInput);
        EditText recipeContentInput = findViewById(R.id.recipeContentInput);
        String name = recipeNameInput.getText().toString();
        String recipe_content = recipeContentInput.getText().toString();
        String id = databaseReference.push().getKey();
        final Recipe recipe = new Recipe(id, name, recipe_content, mAuth.getCurrentUser().getEmail(), false);
        databaseReference.child(id).setValue(recipe);
        Toast.makeText(AddRecipeActivity.this, "Recipe Added Succesfully!", Toast.LENGTH_LONG).show();
        finish();
    }
}

package daie1895.ubb.ro.foodmanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import daie1895.ubb.ro.foodmanager.Adapters.RecipeList;
import daie1895.ubb.ro.foodmanager.Domain.Recipe;

public class MyRecipesActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    List<Recipe> recipes;
    EditText recipeName;
    int MY_RECIPES_ACTIVITY_REQUEST_CODE = 1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        mAuth = FirebaseAuth.getInstance();
        recipes = new ArrayList<Recipe>();
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        listView = (ListView) findViewById(R.id.recipesListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipes.get(position);
                if(!recipe.getEmail().equals(mAuth.getCurrentUser().getEmail())){
                    Toast.makeText(MyRecipesActivity.this, "That is not your recipe!", Toast.LENGTH_SHORT).show();
                    return;
                }
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
                Recipe recipe = recipes.get(position);
                if(recipe.getEmail().equals(mAuth.getCurrentUser().getEmail())) {
                    databaseReference.child(recipe.getId()).removeValue();
                }
                else{
                    Toast.makeText(MyRecipesActivity.this, "That is not your recipe!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_RECIPES_ACTIVITY_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String content = data.getStringExtra("recipe");
                Integer position = data.getIntExtra("position", 0);
                Recipe recipe = recipes.get(position);
                recipe.setName(name);
                recipe.setRecipe(content);
                databaseReference.child(recipe.getId()).setValue(recipe);
            }
    }


    public void createNewRecipe(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                recipes.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = postSnapshot.getValue(Recipe.class);
                    if (recipe.getApproved() || recipe.getEmail().equals(mAuth.getCurrentUser().getEmail())) {
                        recipes.add(recipe);
                    }
                }

                RecipeList recipeAdapter = new RecipeList(MyRecipesActivity.this, recipes, databaseReference, recipeName);
                listView.setAdapter(recipeAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

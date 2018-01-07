package daie1895.ubb.ro.foodmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

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

public class AdminRecipesActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    List<Recipe> recipes;
    EditText recipeName;
    private FirebaseAuth mAuth;
    int MY_ADMIN_RECIPES_REQUEST_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_recipes);

        mAuth = FirebaseAuth.getInstance();
        recipes = new ArrayList<Recipe>();
        databaseReference = FirebaseDatabase.getInstance().getReference("recipes");
        listView = (ListView) findViewById(R.id.listViewRecipesAdmin);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipes.get(position);
                Intent intent = new Intent(AdminRecipesActivity.this, ApproveRecipeActivity.class);
                intent.putExtra("name", recipe.getName());
                intent.putExtra("recipe", recipe.getRecipe());
                intent.putExtra("position", (Integer) position);
                startActivityForResult(intent, MY_ADMIN_RECIPES_REQUEST_CODE);
            }
        });
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
                    recipes.add(recipe);
                }

                RecipeList recipeAdapter = new RecipeList(AdminRecipesActivity.this, recipes, databaseReference, recipeName);
                listView.setAdapter(recipeAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_ADMIN_RECIPES_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                Integer position = data.getIntExtra("position", 0);
                Boolean approved = data.getBooleanExtra("approved", false);
                Recipe recipe = recipes.get(position);
                if(approved) {
                    recipe.setApproved(true);
                    databaseReference.child(recipe.getId()).setValue(recipe);
                }
                else{
                    databaseReference.child(recipe.getId()).removeValue();
                }
            }
    }
}
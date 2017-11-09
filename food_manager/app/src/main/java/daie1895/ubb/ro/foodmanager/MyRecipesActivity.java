package daie1895.ubb.ro.foodmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyRecipesActivity extends AppCompatActivity {

    final Integer MY_RECIPES_ACTIVITY_REQUEST_CODE = 0;
    private ArrayList<Recipe> recipes;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        listView = (ListView) findViewById(R.id.recipesListView);

        this.recipes = new ArrayList<>();

        this.recipes.add(new Recipe("Apple Pie", "aaaa"));
        this.recipes.add(new Recipe("Banana Bread", "aaaa"));
        this.recipes.add(new Recipe("Cupcake", "aaaa"));
        this.recipes.add(new Recipe("Donut", "aaaa"));
        this.recipes.add(new Recipe("Eclair", "aaaa"));
        this.recipes.add(new Recipe("Froyo", "aaaa"));
        this.recipes.add(new Recipe("Gingerbread", "aaaa"));
        this.recipes.add(new Recipe("Honeycomb", "aaaa"));
        this.recipes.add(new Recipe("Ice Cream Sandwich", "aaaa"));
        this.recipes.add(new Recipe("Jelly Bean", "aaaa"));
        this.recipes.add(new Recipe("Kitkat", "aaaa"));
        this.recipes.add(new Recipe("Lollipop", "aaaa"));
        this.recipes.add(new Recipe("Marshmallow", "aaaa"));

        adapter = new CustomAdapter(recipes, getApplicationContext());



        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = recipes.get(position);
                Intent intent = new Intent(MyRecipesActivity.this, EditRecipeActivity.class);
                intent.putExtra("name", recipe.getName());
                intent.putExtra("recipe", recipe.getRecipe());
                intent.putExtra("position", (Integer)position);
                startActivityForResult(intent, MY_RECIPES_ACTIVITY_REQUEST_CODE);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_RECIPES_ACTIVITY_REQUEST_CODE)
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String recipe = data.getStringExtra("recipe");
                recipes.get(data.getIntExtra("position", 0)).setName(name);
                recipes.get(data.getIntExtra("position", 0)).setRecipe(recipe);
                adapter.notifyDataSetChanged();
            }
    }
}

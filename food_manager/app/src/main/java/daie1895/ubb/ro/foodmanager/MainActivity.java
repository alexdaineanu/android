package daie1895.ubb.ro.foodmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "recipeNameInput";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void exitApplication(View view) {
        finish();
    }

    public void addRecipe(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    public void viewRecipes(View view) {
        Intent intent = new Intent(this, MyRecipesActivity.class);
        startActivity(intent);
    }
}

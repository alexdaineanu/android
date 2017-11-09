package daie1895.ubb.ro.foodmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }

    public void sendRecipe(View view) {
        EditText recipeNameInput = findViewById(R.id.recipeNameInput);
        EditText recipeContentInput = findViewById(R.id.recipeContentInput);
        String name = recipeNameInput.getText().toString();
        String recipe_content = recipeContentInput.getText().toString();
        Recipe recipe = new Recipe(name, recipe_content);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"alexdaineanu@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, recipe.toString());
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

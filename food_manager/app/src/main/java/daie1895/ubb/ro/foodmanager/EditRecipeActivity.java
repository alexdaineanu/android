package daie1895.ubb.ro.foodmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditRecipeActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        EditText nameEditText = findViewById(R.id.nameEditText);
        nameEditText.setText(this.intent.getStringExtra("name"), TextView.BufferType.EDITABLE);
        EditText contentEditText = findViewById(R.id.recipeEditText);
        contentEditText.setText(this.intent.getStringExtra("recipe"), TextView.BufferType.EDITABLE);
    }

    public void onSaveButtonClick(View view){
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText contentEditText = findViewById(R.id.recipeEditText);
        Intent intent = new Intent();
        intent.putExtra("name", nameEditText.getText().toString());
        intent.putExtra("recipe", contentEditText.getText().toString());
        intent.putExtra("position", this.intent.getIntExtra("position", 0));
        setResult(RESULT_OK, intent);
        finish();
    }
}

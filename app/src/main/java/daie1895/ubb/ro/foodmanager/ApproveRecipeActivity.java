package daie1895.ubb.ro.foodmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class ApproveRecipeActivity extends AppCompatActivity {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_recipe);
        EditText nameEditText = findViewById(R.id.nameEditText);
        nameEditText.setText(this.intent.getStringExtra("name"), TextView.BufferType.EDITABLE);
        EditText contentEditText = findViewById(R.id.recipeEditText);
        contentEditText.setText(this.intent.getStringExtra("recipe"), TextView.BufferType.EDITABLE);
    }

    public void onApproveButtonClick(View view){
        Intent intent = new Intent();
        intent.putExtra("approved", true);
        intent.putExtra("position", this.intent.getIntExtra("position", 0));
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onDeleteButtonClick(View view){
        Intent intent = new Intent();
        intent.putExtra("approved", false);
        intent.putExtra("position", this.intent.getIntExtra("position", 0));
        setResult(RESULT_OK, intent);
        finish();
    }
}

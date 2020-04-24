package com.example.mvvmcodinginflow;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE ="com.example.mvvmcodinginflow.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION ="com.example.mvvmcodinginflow.EXTRA_TITLE";
    public static final String EXTRA_PRIORITY ="com.example.mvvmcodinginflow.EXTRA_TITLE";
    private EditText edtTitle;
    private EditText edtDescription;
    private NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edtTitle = findViewById(R.id.edt_title);
        edtDescription = findViewById(R.id.edt_description);
        numberPicker = findViewById(R.id.picker_priority);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("add note");
    }

    private void saveNote() {
        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();
        int priority = numberPicker.getValue();
        if (title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(AddActivity.this, "please enter something", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

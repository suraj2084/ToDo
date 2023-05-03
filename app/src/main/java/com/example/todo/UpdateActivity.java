package com.example.todo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class UpdateActivity extends AppCompatActivity {
EditText editTask;
AppCompatButton btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        DBHelper dbHelper = new DBHelper(this);
        editTask=findViewById(R.id.editUserTaskUpdate);
        btnUpdate=findViewById(R.id.btnUpdate);
        int id = getIntent().getIntExtra("id", 0);
        Cursor cursor = dbHelper.getTaskByID(id);
        editTask.setText(cursor.getString(0));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = dbHelper.TaskUpdate(
                        editTask.getText().toString(),
                        id
                );
                if (isUpdated) {
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(UpdateActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
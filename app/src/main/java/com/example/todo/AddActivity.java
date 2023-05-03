package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.todo.databinding.ActivityAddBinding;

public class AddActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper dbHelper=new DBHelper(this);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = dbHelper.Task(binding.editUserTask.getText().toString());
                if (isInserted) {
                    Toast.makeText(AddActivity.this, "Instered", Toast.LENGTH_SHORT).show();
                    binding.editUserTask.setText("");
                    startActivity(new Intent(AddActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(AddActivity.this, "Not Instered", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
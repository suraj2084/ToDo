package com.example.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.viewholder> {
    ArrayList<ToDoModel> List;
    Context context;
    public TodoAdapter(ArrayList<ToDoModel> list,Context context){
        this.List=list;
        this.context=context;
    }
    @NonNull

    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder , int position) {
        final ToDoModel model=List.get(position);
        holder.Task.setText(model.getWork());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, UpdateActivity.class);
                i.putExtra("id",Integer.parseInt(model.getNumber()));
                context.startActivity(i);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item?")
                        .setIcon(R.drawable.baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper dbHelper=new DBHelper(context);
                                if (dbHelper.TaskDelete(Integer.parseInt(model.getNumber()))>=0){
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return List.size();
    }
    public static class viewholder extends RecyclerView.ViewHolder {
        TextView Task;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            Task=itemView.findViewById(R.id.ShowTask);
        }

    }
}

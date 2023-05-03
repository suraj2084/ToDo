package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
public class DBHelper extends SQLiteOpenHelper {
    final static String DBNAME="TODO.db";
    final static int DBVERSION=1;
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("" +
                "create table Todo"+
                "(id integer primary key autoincrement,"+
                "Task text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists Todo");
        onCreate(db);
    }
    public boolean Task(String Task) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Task", Task);
        long id = db.insert("Todo", null, cv);
        if(id<=0){
            return false;
        }else{
            return true;
        }
    }
    public ArrayList<ToDoModel> getTask(){
        ArrayList<ToDoModel> Tasks=new ArrayList<>();
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Todo",null);
        if(cursor.moveToNext()){
            while (cursor.moveToNext()){
                ToDoModel model=new ToDoModel();
                model.setNumber(cursor.getString(0));
                model.setWork(cursor.getString(1));
                Tasks.add(model);
            }
        }
        cursor.close();
        db.close();
        return Tasks;
    }
    public Cursor getTaskByID(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT Task FROM Todo where id ="+id,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public boolean TaskUpdate(String Task, int id) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Task", Task);
        long row = db.update("Todo",cv,"id ="+id,null);
        if(row<=0){
            return false;
        }else{
            return true;
        }
    }
    public int TaskDelete(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete("Todo","id ="+id,null);
    }

}

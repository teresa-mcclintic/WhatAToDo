package com.example.whatatodo;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

import android.database.Cursor;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

    public class TaskDatabase extends SQLiteOpenHelper {
        public static final int VERSION = 2;
        public static final String DB_NAME= "toDoListDB";
        public static final String TABLE_NAME = "ToDo";
        public static final String ID = "id";
        public static final String TASK_COL = "task";
        public static final String DATE_COL = "date";
        public static final String STATUS_COL= "status";

        public TaskDatabase(Context context){
        //, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, DB_NAME, null, VERSION);}


        @Override
        public void onCreate(SQLiteDatabase db) {
                 Log.i("INFO", "onCreate was called");
                 String CREATE_TASK_LIST = "CREATE TABLE "+ TABLE_NAME
                         + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                         TASK_COL + " TEXT, " + DATE_COL + " TEXT, " + STATUS_COL + " INTEGER)";
                db.execSQL(CREATE_TASK_LIST);
            }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }
        public ArrayList<Tasks> listTasks(){
            String sql = "select * from " + TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            ArrayList<Tasks> storeTasks = new ArrayList<>();
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()){
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    String task = cursor.getString(1);
                    String date = cursor.getString(2);
                    int checkbox = Integer.parseInt(cursor.getString(3));
                    storeTasks.add(new Tasks(id, task, date, checkbox));
                }while (cursor.moveToNext());

            }
            cursor.close();
            return storeTasks;
        }

        public void addTasks(Tasks tasks){
            ContentValues values = new ContentValues();
            values.put(TASK_COL, tasks.getTask());
            values.put(DATE_COL, tasks.getDate());
            values.put(STATUS_COL, tasks.getStatus());
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TaskDatabase.TABLE_NAME, null, values);
        }

        public void updateTask(Tasks tasks){
            ContentValues values = new ContentValues();
            values.put(ID, tasks.getId());
            values.put(TASK_COL, tasks.getTask());
            values.put(DATE_COL, tasks.getDate());
            values.put(STATUS_COL, tasks.getStatus());
            SQLiteDatabase db = this.getWritableDatabase();
            db.update(TABLE_NAME, values, ID + "=?", new String[] {String.valueOf(tasks.getId())});
        }
        public void deleteTask(int id){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME, ID +"=?", new String[]{String.valueOf(id)});

        }

    }


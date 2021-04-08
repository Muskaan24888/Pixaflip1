package com.example.pixaflip.sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
public class MyDbHelper extends SQLiteOpenHelper {
    //private static final String DB_NAME = "useractivity6";
    //private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "useract";
    // below variable is for our id column.
    private static final String     ID = "id";
    // below variable is for our course name column
    private static final String FROMACT = "fromact1";
    // below variable id for our course duration column.
    private static final String TOACT = "toact1";
    // below variable for our course description column.
    private static final String CREATEDAT = "createat1";
    // below variable for our course description column.
    private static final String create="CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FROMACT + " TEXT,"
            + TOACT + " TEXT,"
            + CREATEDAT + " TEXT)";
    private static final String create1 ="CREATE TABLE " + Params.TABLE_NAME + "("
            + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
            + " TEXT, " + Params.KEY_URL + " TEXT" + ")";
    public MyDbHelper(Context context)
    {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    public MyDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create);
        db.execSQL(create1);
        Log.d("dbh", "Query being run is : "+ create);
        Log.d("dbh", "Query being run is : "+ create1);
    }
    public boolean isExist(String name){
        boolean x = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Favourite contact = new Favourite();
            System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            if(cursor.getString(cursor.getColumnIndex("name")).equals(name)){
                x = true;
                break;}
        }
        return x;
    }
    public void addFavourite(Favourite pdfs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, pdfs.getName());
        values.put(Params.KEY_URL, pdfs.getUrl());
        db.insert(Params.TABLE_NAME, null, values);
        Log.d("dbPixa", "Successfully inserted");
        db.close();
    }

    public void Adduser(model m){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(ID, m.getFrom1 ());
        values.put(FROMACT, m.getFrom1 ());
        values.put(TOACT,m.getTo1 ());
        values.put(CREATEDAT,m.getTimestamp ());
        final long insert = db.insert(TABLE_NAME, null, values);
        Log.d("dbPixa", "Successfully inserted");
        db.close();
    }


    public void deleteById(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_NAME +"=?", new String[]{String.valueOf(name)});
        //  db.close();
    }
    public List<Favourite> getAll(){
        List<Favourite> pdfList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Generate the query to read from the database
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);
        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Favourite pdfs = new Favourite();
                pdfs.setId(Integer.parseInt(cursor.getString(0)));
                pdfs.setName(cursor.getString(1));
                pdfs.setUrl(cursor.getString(2));
                pdfList.add(pdfs);
            }while(cursor.moveToNext());
        }
        return pdfList;
    }
    public void adduseract(String s1, String s2, String s3) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        // on below line we are passing all values
        // along with its key and value pair.
        values.put( FROMACT, s1);
        values.put(TOACT, s2);
        values.put(CREATEDAT, s3);
        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        Log.d("dbh", "Successfully inserted");
        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
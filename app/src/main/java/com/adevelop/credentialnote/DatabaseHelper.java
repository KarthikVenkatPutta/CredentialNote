package com.adevelop.credentialnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="credentialNote";
    private static final int DB_VERSION=1;

    DatabaseHelper(Context context )
    {

        super(context,DB_NAME,null,DB_VERSION);
//        System.out.println("In Constructor");
    }

    public void onCreate(SQLiteDatabase db)
    {
//        System.out.println("OnCreate Called");
        db.execSQL("CREATE TABLE VAULT ( _id INTEGER PRIMARY KEY AUTOINCREMENT, AccountName TEXT, UserName TEXT, Password TEXT,  URL TEXT,  Notes TEXT);");
        db.execSQL("CREATE TABLE AUTH ( _id INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT, Password TEXT);");
//        insertAccount(db,"accountName","user","pwd","url","test");
        //registerUser(LoginActivity.passWord)

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertAccount(SQLiteDatabase db,String accountName, String userName,String pwd,String url,String notes )
    {
        ContentValues values=new ContentValues();
        values.put( "AccountName",accountName);
        values.put("UserName",userName);
        values.put("Password",pwd);
        values.put("URL",url);
        values.put("Notes",notes);
        db.insert("VAULT",null,values);
    }

    public void updateAccount(SQLiteDatabase db,String accountName, String userName,String pwd,String url,String notes,String id)
    {
        ContentValues values=new ContentValues();
        values.put( "AccountName",accountName);
        values.put("UserName",userName);
        values.put("Password",pwd);
        values.put("URL",url);
        values.put("Notes",notes);
        db.update("VAULT",values," _id=?",new String[]{id});
    }

    public boolean authenticateUser(SQLiteDatabase db,String pwd)
    {

        Cursor cursor=db.query("AUTH",new String[]{"Password"},"Password=?",new String[]{pwd},null,null,null);
       //cursor.moveToFirst();
        //System.out.println("PASSOWRD IS"+cursor.getString(0));
        if(cursor.getCount()>0)
        //cursor.get
        return true;
        else
            return false;
    }

    public void registerUser(SQLiteDatabase db,String passWord)
    {

        ContentValues values=new ContentValues();
        values.put( "Password",passWord);
        db.insert("AUTH",null,values);

    }
}

package com.adevelop.credentialnote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        TextView accountVal=findViewById(R.id.accountNameVal);
        TextView userNameVal=findViewById(R.id.userNameVal);
        TextView passwordVal=findViewById(R.id.passwordVal);
        TextView urlVal=findViewById(R.id.urlVal);
        TextView notestVal=findViewById(R.id.notestVal);
        long idVal=getIntent().getLongExtra("position",0);
        DatabaseHelper dbHelper = new DatabaseHelper(ViewAccountActivity.this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //Cursor cursor=db.query("VAULT",null, "_id = ? and AccountName = ?",new String[]{String.valueOf(idVal),getIntent().getStringExtra("accountName")},null,null,null);
        Cursor cursor=db.query("VAULT",null, "_id = ?",new String[]{String.valueOf(idVal)},null,null,null);
        cursor.moveToFirst();
        accountVal.setText(cursor.getString(1));
        userNameVal.setText(cursor.getString(2));
        //cursor.moveToNext();
        passwordVal.setText(cursor.getString(3));
        //cursor.moveToNext();
        urlVal.setText(cursor.getString(4));
        notestVal.setText(cursor.getString(5));
        db.close();
//        cursor.moveToNext();
//        notestVal.setText(cursor.getString(3));
//        CursorAdapter listAdapter=new SimpleCursorAdapter(MainActivity.this,android.R.layout.simple_list_item_1,cursor,new String[]{"AccountName"},new int[]{android.R.id.text1},0);
//        ListView list=(ListView) findViewById(R.id.list_options);
//        list.setAdapter(listAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_viewaccount, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        long idVal=getIntent().getLongExtra("position",0);

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_acct) {

            Intent intent=new Intent(ViewAccountActivity.this,EditAccountActivity.class);
            //intent.putExtra("account",getIntent().getStringExtra("accountName"));
            intent.putExtra("id",idVal);
            finish();
            startActivity(intent);
            return true;
        }

        if (id == R.id.delete_acct) {

            DatabaseHelper dbHelper = new DatabaseHelper(ViewAccountActivity.this);
            SQLiteDatabase db=dbHelper.getReadableDatabase();
           // db.execSQL("delete from VAULT where AccountName="+"'"+getIntent().getStringExtra("accountName")+"'");
            db.execSQL("delete from VAULT where _id="+"'"+String.valueOf(idVal)+"'");
            db.close();
            Intent intent=new Intent(ViewAccountActivity.this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume()
    {
        super.onResume();
        //System.out.println("VIEWACCOUNTACTIVITY ON RESUME");
    }
}

package com.adevelop.credentialnote;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar bar = getSupportActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFF00")));
        ListView list = (ListView) findViewById(R.id.list_options);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, ViewAccountActivity.class);
                //intent.putExtra("position",position+1);
                intent.putExtra("position", id);
                intent.putExtra("accountName", ((TextView) view).getText());
                //intent.putExtra("accountName",accountName);
                startActivity(intent);
//                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.add_acct) {
            Intent intent = new Intent(this, SaveAccountActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Activity OnPause
    public void onPause() {
        super.onPause();
//        System.out.println("Calling Activity onpause");
    }

    //Activity OnResume
    public void onResume() {
        super.onResume();
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("VAULT", new String[]{"_id", "AccountName"}, null, null, null, null, null);
        CursorAdapter listAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_1, cursor, new String[]{"AccountName"}, new int[]{android.R.id.text1}, 0);
        ListView list = (ListView) findViewById(R.id.list_options);
        list.setAdapter(listAdapter);
//        System.out.println("Calling Activity Resume");
//        System.out.println("Current List Count"+cursor.getCount());
        if (cursor.getCount() > 0) {
            TextView txt = (TextView) findViewById(R.id.NoRecords);
            txt.setVisibility(View.GONE);

        }
        db.close();


    }

    @Override
    public void onBackPressed() {
        // your code.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to Logout?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(MainActivity.this, "Exiting App", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onDestroy() {
        super.onDestroy();

    }
}



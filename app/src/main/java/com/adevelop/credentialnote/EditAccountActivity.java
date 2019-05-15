package com.adevelop.credentialnote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditAccountActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        EditText accountVal=findViewById(R.id.acct_name);
        EditText userNameVal=findViewById(R.id.user_name);
        EditText passwordVal=findViewById(R.id.pwd);
        EditText urlVal=findViewById(R.id.url);
        EditText notesVal=findViewById(R.id.notes);
        final DatabaseHelper dbHelper = new DatabaseHelper(EditAccountActivity.this);
        final SQLiteDatabase db=dbHelper.getReadableDatabase();
        final long idVal=getIntent().getLongExtra("id",0);
        Cursor cursor=db.query("VAULT",null, "_id = ?",new String[]{String.valueOf(idVal)},null,null,null);
        cursor.moveToFirst();
        accountVal.setText(cursor.getString(1));
        userNameVal.setText(cursor.getString(2));
        //cursor.moveToNext();
        passwordVal.setText(cursor.getString(3));
        //cursor.moveToNext();
        urlVal.setText(cursor.getString(4));
        notesVal.setText(cursor.getString(5));
        //cursor.close();

        Button saveAcctButton = findViewById(R.id.save);
        saveAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText accountName=(EditText)findViewById(R.id.acct_name);
                EditText userName=(EditText)findViewById(R.id.user_name);
                EditText password=(EditText)findViewById(R.id.pwd);
                EditText URL=(EditText)findViewById(R.id.url);
                EditText notes=(EditText)findViewById(R.id.notes);
                dbHelper.updateAccount(db,accountName.getText().toString(),userName.getText().toString(),password.getText().toString(),URL.getText().toString(),notes.getText().toString(),String.valueOf(idVal));
                db.close();
                Intent intent=new Intent(EditAccountActivity.this,ViewAccountActivity.class);
                intent.putExtra("position",idVal);
                finish();
                startActivity(intent);
            }
        });


    }

}

package com.adevelop.credentialnote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SaveAccountActivity extends AppCompatActivity {

//    private EditText accountName;
//    private EditText userName;
//    private EditText password;
//    private EditText URL;
//    private EditText notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_account);
        final DatabaseHelper dbHelper = new DatabaseHelper(SaveAccountActivity.this);
        final SQLiteDatabase db=dbHelper.getReadableDatabase();

        Button saveAcctButton = findViewById(R.id.button);
        saveAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText accountName=(EditText)findViewById(R.id.acct_name);
                EditText userName=(EditText)findViewById(R.id.user_name);
                EditText password=(EditText)findViewById(R.id.pwd);
                EditText URL=(EditText)findViewById(R.id.url);
                EditText notes=(EditText)findViewById(R.id.notes);
                if(accountName.getText().toString().isEmpty() || userName.getText().toString().isEmpty())
                Toast.makeText(SaveAccountActivity.this,"AccountName and UserName Required",Toast.LENGTH_SHORT).show();
                else{
                dbHelper.insertAccount(db,accountName.getText().toString(),userName.getText().toString(),password.getText().toString(),URL.getText().toString(),notes.getText().toString());
                db.close();
                Intent intent=new Intent(SaveAccountActivity.this,MainActivity.class);
                startActivity(intent);
                }
            }
        });
    }


}

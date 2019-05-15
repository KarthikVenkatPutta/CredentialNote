package com.adevelop.credentialnote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class NavigatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File database=getApplicationContext().getDatabasePath("credentialNote");
        if(database.exists())
        {
            System.out.println("Starting Login Activity");
            Intent intent =new Intent(this,LoginActivity.class);
            finish();
            startActivity(intent);
        }
        else
        {
            System.out.println("####Starting Register Activity");

            Intent intent =new Intent(this,RegisterActivity.class);
            finish();
            startActivity(intent);
        }

        //setContentView(R.layout.activity_navigator);
    }
}

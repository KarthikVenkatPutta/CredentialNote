package com.adevelop.credentialnote;

import android.content.Intent;
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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        Button registerButton = (Button) findViewById(R.id.Register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText password=(TextInputEditText) findViewById(R.id.password1);
                TextInputEditText password2=(TextInputEditText) findViewById(R.id.confirmpassword1);
                final String passwordVal=password.getText().toString();
                final String passwordVal2=password2.getText().toString();
                if(!(passwordValidation(passwordVal,passwordVal2)))
                {
                    Toast.makeText(RegisterActivity.this,"Password cannote be empty/Password-Confirm Password should match",Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseHelper dbHelper = new DatabaseHelper(RegisterActivity.this);
                SQLiteDatabase db=dbHelper.getReadableDatabase();
//                System.out.println("REGISTER ACTIVITY PASSWORD IS"+passwordVal+passwordVal2);
                dbHelper.registerUser(db,passwordVal);
                db.close();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });

    }

    private boolean passwordValidation(String pwd,String confirmpwd)
    {
        if(pwd.isEmpty()||confirmpwd.isEmpty())
        {
            return false;
        }
        if(!(pwd.equals(confirmpwd)))
        {
            return false;
        }
        return true;
    }

}

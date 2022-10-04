package com.example.odc_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Welcome extends AppCompatActivity {
    public static final String EMAIL_KEY = "email";
    public static final String PASS_KEY = "pass";
    public static final String USER_name = "name";
    public static final String SHARED_KEY = "AuthPref";
    public static final String ID = "IDENTIFICATION";
    public static SharedPreferences.Editor editor;

    TextView signIn;
    Button signUp,GoogleSignUp;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        dbHandler = new DBHandler(Welcome.this);
        signUp=findViewById(R.id.button2);
        GoogleSignUp=findViewById(R.id.GoogleSignUo);
        signIn=findViewById(R.id.textView9);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SignUp.SHARED_KEY, Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        GoogleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
                finish();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
            }
        });
    }
    void signIn(){
        Intent signInIntent=gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                GoogleSignInAccount acct=GoogleSignIn.getLastSignedInAccount(this);
                if(acct!=null){
                    String personName=acct.getDisplayName();
                    String personEmail =acct.getEmail();
                    dbHandler.addNewCourse(personName,personEmail,"");
                    editor.putString(SignUp.EMAIL_KEY,personEmail);

                    editor.putString(SignUp.USER_name,personName);

                    editor.putInt(SignUp.ID,dbHandler.getUserID(personEmail,""));
                    ;
                    editor.apply();
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    startActivity(i);

                }
            } catch (ApiException e) {
                Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
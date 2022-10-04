package com.example.odc_finalproject;

import static com.example.odc_finalproject.Welcome.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {
TextView sign,login;
EditText email,Pass;
Button GoogleSignUp;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    public static final String EMAIL_KEY = "email";
    public static final String PASS_KEY = "pass";
    public static final String USER_name = "name";
    public static final String ID = "IDENTIFICATION";
    private DBHandler dbHandler;
    TextView Sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        GoogleSignUp=findViewById(R.id.GoogleSignUo);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        Sign=findViewById(R.id.SignUp);
        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }
        });
        GoogleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        email=findViewById(R.id.Email);
        Pass=findViewById(R.id.pass);
        dbHandler = new DBHandler(Login.this);
        SharedPreferences sharedPreferences = getSharedPreferences("AuthPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        sign=findViewById(R.id.SignUp);
        login=findViewById(R.id.Login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=email.getText().toString();
                String pass=Pass.getText().toString();
                if(dbHandler.CheckUser(Email,pass)==true){
                    editor.putString(Login.EMAIL_KEY,Email);
                    editor.putString(Login.PASS_KEY,pass);
                    editor.putString(Login.USER_name,dbHandler.getUserName(email.getText().toString(),pass));
                    editor.putInt(Login.ID,dbHandler.getUserID(email.getText().toString(),pass));
                    editor.apply();
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                    finish();
            }}
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
                    if(dbHandler.CheckUser(personEmail,"")==true){
                    editor.putString(SignUp.EMAIL_KEY,personEmail);

                    editor.putString(SignUp.USER_name,personName);

                    editor.putInt(SignUp.ID,dbHandler.getUserID(personEmail,""));
                    ;
                    editor.apply();
                    Intent i = new Intent(getApplicationContext(),Home.class);
                    startActivity(i);
                        finish();

                }}
            } catch (ApiException e) {
                Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
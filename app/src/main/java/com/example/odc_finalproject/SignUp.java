package com.example.odc_finalproject;

import static com.example.odc_finalproject.Welcome.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {
    public static final String EMAIL_KEY = "email";
    public static final String PASS_KEY = "pass";
    public static final String USER_name = "name";
    public static final String SHARED_KEY = "AuthPref";
    public static final String ID = "IDENTIFICATION";

    //mAuth = FirebaseAuth.getInstance();

    Button sign,GoogleSignUp;
    EditText email,Uname,pass;
    private DBHandler dbHandler;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        GoogleSignUp=findViewById(R.id.GoogleSignUo);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SignUp.SHARED_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        SignIn=findViewById(R.id.k);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
        GoogleSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();

            }
        });
        dbHandler = new DBHandler(SignUp.this);

        sign=findViewById(R.id.SignUp);
        email=findViewById(R.id.Email);
        Uname=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // below line is to get data from all edit text fields.
                String UserName = Uname.getText().toString();
                String Email = email.getText().toString();
                String Password = pass.getText().toString();

                if(email.getText().toString().equals("") && pass.getText().toString().equals("")&& Uname.getText().toString().equals("")) {

                    Toast.makeText(SignUp.this, "Please Enter Data", Toast.LENGTH_SHORT).show();
                }else{

                    dbHandler.addNewCourse(UserName, Email, Password);


                    editor.putString(SignUp.EMAIL_KEY,email.getText().toString());
                    editor.putString(SignUp.PASS_KEY,pass.getText().toString());
                    editor.putString(SignUp.USER_name,Uname.getText().toString());

                    editor.putInt(SignUp.ID,dbHandler.getUserID(email.getText().toString(),pass.getText().toString()));
                    String TO=email.getText().toString();
                    editor.apply();

                    Uname.setText("");
                    email.setText("");
                    pass.setText("");
                    Toast.makeText(SignUp.this, "Welcome "+Uname.getText().toString(), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(),Home.class);
                    startActivity(i);
                   // finish();

                }
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

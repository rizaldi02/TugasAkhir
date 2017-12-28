package com.example.rizaldi.jadwalpelajaran;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    public static String loginsPref = "file.login.preferencess";
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    SharedPreferences preferences;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = getSharedPreferences(loginsPref, 0);
        if (preferences.contains("user")) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        getSupportActionBar().setTitle("Login");

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
    }

    public void Login(View view) {
        if (username.getText().toString().contains(" ")) {
            Toast.makeText(this, "Username tidak boleh mengandung spasi", Toast.LENGTH_LONG).show();
        } else {
            final DatabaseReference usersRef = ref.child("users");

            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(username.getText().toString())) {
                        if (dataSnapshot.child(username.getText().toString()).child("password")
                                .getValue().toString().equals(password.getText().toString())){

                            preferences = getSharedPreferences(loginsPref, 0);
                            preferences.edit().putString("user", username.getText().toString()).apply();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Password salah", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Username tidak ada", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(LoginActivity.this, "Ada masalah", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}

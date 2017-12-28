package com.example.rizaldi.jadwalpelajaran;

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

public class RegisterActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    EditText telepon, username, nama, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Daftar");

        telepon = findViewById(R.id.register_telepon);
        username = findViewById(R.id.register_username);
        nama = findViewById(R.id.register_nama);
        password = findViewById(R.id.register_password);
    }

    public void addNew(View view) {
        if (username.getText().toString().contains(" ")) {
            Toast.makeText(this, "Username tidak boleh mengandung spasi", Toast.LENGTH_LONG).show();
        } else {
            final DatabaseReference usersRef = ref.child("users");

            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(username.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "Username sudah ada", Toast.LENGTH_SHORT).show();
                    } else {
                        DatabaseReference newRef = usersRef.child(username.getText().toString());
                        newRef.child("telepon").setValue(telepon.getText().toString());
                        newRef.child("nama").setValue(nama.getText().toString());
                        newRef.child("password").setValue(password.getText().toString());
                        Toast.makeText(RegisterActivity.this, "User baru ditambahkan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(RegisterActivity.this, "Ada masalah", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

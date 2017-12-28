package com.example.rizaldi.jadwalpelajaran;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    EditText makul, hari, jam, ruangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        makul = findViewById(R.id.tambah_makul);
        hari = findViewById(R.id.tambah_hari);
        jam = findViewById(R.id.tambah_jam);
        ruangan = findViewById(R.id.tambah_ruangan);
    }

    public void tambah(View view) {
        SharedPreferences preferences = getSharedPreferences(LoginActivity.loginsPref, 0);
        String nmakul, nhari, njam, nruangan;

        nmakul = makul.getText().toString();
        nhari = hari.getText().toString();
        njam = jam.getText().toString();
        nruangan = ruangan.getText().toString();

        if (nmakul.isEmpty() && nhari.isEmpty() && njam.isEmpty() && nruangan.isEmpty()) {
            Toast.makeText(this, "Ada field kosong", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference userRef = ref.child("users").child(
                    preferences.getString("user", "")
            ).child("jadwal");

            userRef.push().setValue(new Jadwal(nmakul, nhari, njam, nruangan));
            Toast.makeText(this, "Jadwal ditambahkan", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

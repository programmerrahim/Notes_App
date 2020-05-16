package com.nayer.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    CircleImageView circleImageView;
    EditText edtDisplayName;
    Button updateProfileButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        circleImageView = findViewById(R.id.circleImageViewId);
        edtDisplayName = findViewById(R.id.edtDisplayNameId);
        updateProfileButton = findViewById(R.id.updateButtonId);
        progressBar = findViewById(R.id.progressBarId);
    }

    public void updateProfile(View view) {
    }
}

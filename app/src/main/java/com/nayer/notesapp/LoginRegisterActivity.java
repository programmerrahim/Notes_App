package com.nayer.notesapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {
    private static final String TAG = "LoginRegisterActivity";
    int AUTHUI_UI_REQUEST_CODE = 1001;

    //main Method Starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(this,MainActivity.class));
            this.finish();
        }
    }

    public void handleLoginRegister(View view) {
        List<AuthUI.IdpConfig>provider = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build()
        );

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(provider)
                .setLogo(R.drawable.notes)
                .setAlwaysShowSignInMethodScreen(true)
                .build();

            startActivityForResult(intent,AUTHUI_UI_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AUTHUI_UI_REQUEST_CODE){
            if (resultCode==RESULT_OK){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "onActivityResult: "+ user.getEmail());
                if (user.getMetadata().getCreationTimestamp()==user.getMetadata().getLastSignInTimestamp()){
                    Toast.makeText(this,"Welcome new User",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"Welcome back again!!!",Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }
            else {
                IdpResponse response =IdpResponse.fromResultIntent(data);
                if (response==null){
                    Log.d(TAG, "onActivityResult: the user has cancellled the sign in request");
                }
                else {
                    Log.d(TAG, "onActivityResult: ",response.getError());
                }
            }
        }
    }
}

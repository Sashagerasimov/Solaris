package com.example.a3634_assigment.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a3634_assigment.Databases.SessionInfo;
import com.example.a3634_assigment.Models.Images;
import com.example.a3634_assigment.Models.User;
import com.example.a3634_assigment.R;

public class AvatarActivity extends AppCompatActivity {

    //declare variables
    int newAvatar =0;
    ImageView avatarSample;
    TextView usernameSample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient));

        avatarSample = findViewById(R.id.sample);
        usernameSample = findViewById(R.id.usernameSample);

        Bundle bundle = getIntent().getExtras();
        usernameSample.setText(bundle.getString(RegisterActivity.NEW_USERNAME));
    }

    //sets sample avatar image
    public void selectAvatar(View view){
        int[] avatars = {
                R.id.avatar1,
                R.id.avatar2,
                R.id.avatar3,
                R.id.avatar4,
                R.id.avatar5,
                R.id.avatar6,
        };

        //int newAvatar = 0;
        while (newAvatar < avatars.length) {
            if (view.getId() == avatars[newAvatar])
                break;
            newAvatar++;
        }
        avatarSample.setImageResource(Images.avatars[newAvatar]);
    }

    //registers user
    public void onClickRegister(View view){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                    User user = new User(bundle.getString(RegisterActivity.NEW_USERNAME), bundle.getString(RegisterActivity.NEW_PASSWORD), newAvatar);
                    //add user to database
                    SessionInfo.mUserDatabase.userDao().insertOneUser(user);
                    //set current user
                    SessionInfo.currentUser = user;
            }
        }) .start();

        //takes user to Main Page/Dashboard
        Intent intent = new Intent(AvatarActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }
}

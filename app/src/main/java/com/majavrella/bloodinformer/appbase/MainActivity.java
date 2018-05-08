package com.majavrella.bloodinformer.appbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.majavrella.bloodinformer.FirstFragment;
import com.majavrella.bloodinformer.R;
import com.majavrella.bloodinformer.user.UserActivity;

public class MainActivity extends BaseActivity {

    static private FirebaseAuth mFirebaseAuth;
    static private FirebaseUser mFirebaseUser;
    static private FragmentManager mFragmentManager;
    private boolean exit = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            loadLogInView();
        }
        else {
            Intent intent = new Intent(this, UserActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void loadLogInView() {
        add(FirstFragment.newInstance());
    }
}
package com.mobcom114.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager nFragmentManager = getSupportFragmentManager();
        HomeFragment nHomeFragment = new HomeFragment();
        Fragment fragment = nFragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)) {
            Log.d("fragment", "fragment Name : " + HomeFragment.class.getSimpleName());
            nFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_container, nHomeFragment, HomeFragment.class.getSimpleName())
                    .commit();
        }
    }
}
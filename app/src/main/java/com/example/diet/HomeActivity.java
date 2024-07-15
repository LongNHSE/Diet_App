package com.example.diet;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;

import com.example.diet.ui.product.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView
                = findViewById(R.id.bottom_navigation);

        bottomNavigationView
                .setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_meal);
    }

    MainFragment mainFragment = new MainFragment();
    BlogFragment blogFragment = new BlogFragment();

    ProductFragment productFragment = new ProductFragment();

    @Override
    public boolean
    onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_meal:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_host1, mainFragment)
                        .commit();
                return true;

            case R.id.navigation_blog:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_host1, blogFragment)
                        .commit();
                return true;

            case R.id.navigation_product:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_host1, productFragment)
                        .commit();
                return true;
        }
        return false;
    }


}
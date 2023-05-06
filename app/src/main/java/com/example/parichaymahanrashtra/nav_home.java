package com.example.parichaymahanrashtra;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parichaymahanrashtra.databinding.ActivityNavHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class nav_home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavHomeBinding binding;
    private MenuItem logout;
    private ImageButton fort,cave,temple,shopping,agrot,ruralt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        logout=(MenuItem) findViewById(R.id.action_logout);
        fort= (ImageButton)findViewById(R.id.imageButton8);
        cave=(ImageButton)findViewById(R.id.imageButton4);
        agrot=(ImageButton)findViewById(R.id.imageButton6);

        cave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Caves.class));
            }
        });

        fort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Forts.class));
            }
        });

        setSupportActionBar(binding.appBarNavHome.toolbar);
//        binding.appBarNavHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.Diary, R.id.Hotels,R.id.About,R.id.bucketList,R.id.Contact,R.id.location,R.id.logout,R.id.Favourites,R.id.Rating,R.id.Help,R.id.speedDials,R.id.Transport,R.id.Terms)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_logout)
        {
            SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
            editor.putString("password", "");
            editor.putString("email", "");
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("finish", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            Toast.makeText(this, "Logged Out succesfully", Toast.LENGTH_SHORT).show();

            finish();
        }
        return true;
    }



}
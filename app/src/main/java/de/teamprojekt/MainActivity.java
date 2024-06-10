package de.teamprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Add a new to-do
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DetailActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_reset) {
            startActivity(new Intent(MainActivity.this, ResetActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
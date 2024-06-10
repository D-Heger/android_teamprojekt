package de.teamprojekt;

import static de.teamprojekt.Utils.handleSelectedOption;
import static de.teamprojekt.Utils.setNavBar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (handleSelectedOption(this, item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
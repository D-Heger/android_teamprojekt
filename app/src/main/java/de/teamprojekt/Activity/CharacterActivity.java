package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.teamprojekt.R;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_character);
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

package de.teamprojekt;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Utils {
    public static void setNavBar(BottomNavigationView bottomNavigationView, Context context) {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_list) {
                context.startActivity(new Intent(context, MainActivity.class));
                return true;
            }
            if (item.getItemId() == R.id.navigation_add) {
                context.startActivity(new Intent(context, DetailActivity.class));
                return true;
            }
            if (item.getItemId() == R.id.navigation_character) {
                context.startActivity(new Intent(context, CharacterActivity.class));
                return true;
            }
            return false;
        });
    }
}

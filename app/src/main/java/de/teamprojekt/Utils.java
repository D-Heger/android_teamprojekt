package de.teamprojekt;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Utils {
    public static void setNavBar(BottomNavigationView bottomNavigationView, Context context, int selectedItemId) {
        bottomNavigationView.setSelectedItemId(selectedItemId);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == selectedItemId) {
                return true; // Already selected, no need to do anything
            }

            if (itemId == R.id.navigation_list) {
                context.startActivity(new Intent(context, MainActivity.class));
                return true;
            } else if (itemId == R.id.navigation_add) {
                context.startActivity(new Intent(context, DetailActivity.class));
                return true;
            } else if (itemId == R.id.navigation_character) {
                context.startActivity(new Intent(context, CharacterActivity.class));
                return true;
            }
            return false;
        });
    }
}

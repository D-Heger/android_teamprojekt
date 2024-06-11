package de.teamprojekt;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Date;

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

    public static boolean handleSelectedOption(Context context, MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            context.startActivity(new Intent(context, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_reset) {
            context.startActivity(new Intent(context, ResetActivity.class));
            return true;
        }
        return false;
    }

    public static Date toDate(long date) {
        return date == 0 ? null : new Date(date);
    }

    public static Long toTimestamp(Date date) {
        return date == null ? 0 : date.getTime();
    }


}

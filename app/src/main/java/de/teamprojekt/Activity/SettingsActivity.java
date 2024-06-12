package de.teamprojekt.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import de.teamprojekt.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);

            // Find reset preference
            Preference resetPref = findPreference("pref_reset");
            if (resetPref != null) {
                resetPref.setOnPreferenceClickListener(preference -> {
                    // Reset shared preferences to default values
                    resetPreferences();
                    return true;
                });
            }
        }

        private void resetPreferences() {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear().apply();

            Toast.makeText(getContext(), "Settings reset to default values", Toast.LENGTH_SHORT).show();

            // Optionally, restart the activity to reload the default settings
            getActivity().recreate();
        }
    }

}

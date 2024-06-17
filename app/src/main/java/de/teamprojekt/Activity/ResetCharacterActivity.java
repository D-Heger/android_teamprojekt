package de.teamprojekt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class ResetCharacterActivity extends AppCompatActivity {
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        dbHelper = new DataBaseHelper(this);
        showConfirmationDialog();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Reset");
        builder.setMessage("Are you sure you want to reset your character?");
        builder.setPositiveButton("Yes", (dialog, which) -> performReset());
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void performReset() {
        // Remove all character data
        dbHelper.deleteCharacter();

        // Show success message
        Toast.makeText(this, "Character data has been reset.", Toast.LENGTH_SHORT).show();

        // Open Character Creation Activity
        startActivity(new Intent(this, CharacterCreationActivity.class));
        finish();
    }
}

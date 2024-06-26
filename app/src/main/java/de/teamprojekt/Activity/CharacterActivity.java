package de.teamprojekt.Activity;

import static de.teamprojekt.Util.LevelCalculator.calculateProgress;
import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.teamprojekt.Entity.Character;
import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class CharacterActivity extends AppCompatActivity {
    private ImageView characterIcon;
    private TextView characterName;
    private TextView characterAge;
    private TextView characterGender;
    private TextView characterLevel;
    private ProgressBar characterExp;
    private TextView characterStrength;
    private ProgressBar characterStrengthExp;
    private TextView characterPerception;
    private ProgressBar characterPerceptionExp;
    private TextView characterEndurance;
    private ProgressBar characterEnduranceExp;
    private TextView characterCharisma;
    private ProgressBar characterCharismaExp;
    private TextView characterIntelligence;
    private ProgressBar characterIntelligenceExp;
    private TextView characterAgility;
    private ProgressBar characterAgilityExp;
    private TextView characterLuck;
    private ProgressBar characterLuckExp;

    private DataBaseHelper dbHelper;
    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        // Link layout elements
        characterIcon = findViewById(R.id.ivIcon);
        characterName = findViewById(R.id.tvCharacterName);
        characterAge = findViewById(R.id.tvCharacterAge);
        characterGender = findViewById(R.id.tvCharacterGender);
        characterLevel = findViewById(R.id.tvCharacterLevel);
        characterExp = findViewById(R.id.pbCharacterLevel);
        characterStrength = findViewById(R.id.tvStrength);
        characterStrengthExp = findViewById(R.id.pbStrength);
        characterPerception = findViewById(R.id.tvPerception);
        characterPerceptionExp = findViewById(R.id.pbPerception);
        characterEndurance = findViewById(R.id.tvEndurance);
        characterEnduranceExp = findViewById(R.id.pbEndurance);
        characterCharisma = findViewById(R.id.tvCharisma);
        characterCharismaExp = findViewById(R.id.pbCharisma);
        characterIntelligence = findViewById(R.id.tvIntelligence);
        characterIntelligenceExp = findViewById(R.id.pbIntelligence);
        characterAgility = findViewById(R.id.tvAgility);
        characterAgilityExp = findViewById(R.id.pbAgility);
        characterLuck = findViewById(R.id.tvLuck);
        characterLuckExp = findViewById(R.id.pbLuck);


        // Get character from database
        dbHelper = new DataBaseHelper(this);
        character = dbHelper.getCharacter();
        // Initialize values
        initValues();

        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_character);
    }

    private void initValues() {
        characterIcon.setImageResource(character.getIcon());
        characterName.setText(character.getName());
        characterAge.setText(String.valueOf(character.getAge()));
        characterGender.setText(character.getGender());
        characterLevel.setText(getString(R.string.default_level, character.getLevel()));
        characterExp.setProgress(calculateProgress(character.getLevel(), character.getExperience()));
        characterStrength.setText(String.valueOf(character.getStrength()));
        characterStrengthExp.setProgress(calculateProgress(character.getStrength(), character.getStrengthExp()));
        characterPerception.setText(String.valueOf(character.getPerception()));
        characterPerceptionExp.setProgress(calculateProgress(character.getPerception(), character.getPerceptionExp()));
        characterEndurance.setText(String.valueOf(character.getEndurance()));
        characterEnduranceExp.setProgress(calculateProgress(character.getEndurance(), character.getEnduranceExp()));
        characterCharisma.setText(String.valueOf(character.getCharisma()));
        characterCharismaExp.setProgress(calculateProgress(character.getCharisma(), character.getCharismaExp()));
        characterIntelligence.setText(String.valueOf(character.getIntelligence()));
        characterIntelligenceExp.setProgress(calculateProgress(character.getIntelligence(), character.getIntelligenceExp()));
        characterAgility.setText(String.valueOf(character.getAgility()));
        characterAgilityExp.setProgress(calculateProgress(character.getAgility(), character.getAgilityExp()));
        characterLuck.setText(String.valueOf(character.getLuck()));
        characterLuckExp.setProgress(calculateProgress(character.getLuck(), character.getLuckExp()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_filter).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (handleSelectedOption(this, item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

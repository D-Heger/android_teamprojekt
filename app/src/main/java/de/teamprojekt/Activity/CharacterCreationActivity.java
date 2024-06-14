package de.teamprojekt.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import de.teamprojekt.Entity.Character;
import de.teamprojekt.Fragment.AgeFragment;
import de.teamprojekt.Fragment.GenderFragment;
import de.teamprojekt.Fragment.NameFragment;
import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class CharacterCreationActivity extends AppCompatActivity {
    private final Character.Builder characterBuilder = new Character.Builder();
    private DataBaseHelper dbHelper;

    public void collectName(String name) {
        characterBuilder.name(name);
        loadNextFragment(new AgeFragment());
    }

    public void collectAge(int age) {
        characterBuilder.age(age);
        loadNextFragment(new GenderFragment());
    }

    public void collectGender(String gender) {
        characterBuilder.gender(gender);
        finalizeCharacter(); //TODO implement icon picker!!!!!!!!!!!!!!!!
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);

        dbHelper = new DataBaseHelper(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new NameFragment())
                    .commit();
        }
    }

    private void loadNextFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void finalizeCharacter() {
        Character character = characterBuilder
                .icon(R.drawable.ic_character) // TODO REMOVE AFTER IMPLEMENTING ICON PICKER
                .level(0)
                .experience(0)
                .strength(0)
                .strength_exp(0)
                .perception(0)
                .perception_exp(0)
                .endurance(0)
                .endurance_exp(0)
                .charisma(0)
                .charisma_exp(0)
                .intelligence(0)
                .intelligence_exp(0)
                .agility(0)
                .agility_exp(0)
                .luck(0)
                .luck_exp(0)
                .build();

        dbHelper.addCharacter(character);
        setResult(RESULT_OK);
        finish();
    }

}

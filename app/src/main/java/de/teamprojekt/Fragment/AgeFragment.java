package de.teamprojekt.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import de.teamprojekt.Activity.CharacterCreationActivity;
import de.teamprojekt.R;

public class AgeFragment extends Fragment {
    private EditText editTextAge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_age, container, false);
        editTextAge = view.findViewById(R.id.editTextAge);
        Button buttonNext = view.findViewById(R.id.buttonAgeNext);
        buttonNext.setOnClickListener(v -> {
            int age = getCharAge();
            if (age > 0) {
                Toast.makeText(getContext(), age + " years already? You've made it far.", Toast.LENGTH_SHORT).show();
                ((CharacterCreationActivity) getActivity()).collectAge(age);
            } else {
                Toast.makeText(getContext(), "Please enter a valid age.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private int getCharAge() {
        String age = editTextAge.getText().toString();
        if (!age.isEmpty()) {
            return Integer.parseInt(editTextAge.getText().toString());
        }
        return -1;
    }
}
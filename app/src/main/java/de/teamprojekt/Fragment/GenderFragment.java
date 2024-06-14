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

public class GenderFragment extends Fragment {
    private EditText editTextGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender, container, false);
        editTextGender = view.findViewById(R.id.editTextGender);
        Button buttonNext = view.findViewById(R.id.buttonGenderNext);
        buttonNext.setOnClickListener(v -> {
            String gender = getGender().trim();
            if (!gender.isEmpty()) {
                Toast.makeText(getContext(), "A " + gender + "? At least I'm proud of you.", Toast.LENGTH_SHORT).show();
                ((CharacterCreationActivity) getActivity()).collectGender(gender);
            } else {
                Toast.makeText(getContext(), "Please enter something to represent yourself.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private String getGender() {
        return editTextGender.getText().toString();
    }
}

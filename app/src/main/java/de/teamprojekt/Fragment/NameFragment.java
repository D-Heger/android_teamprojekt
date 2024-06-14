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

public class NameFragment extends Fragment {
    private EditText editTextName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        editTextName = view.findViewById(R.id.editTextName);
        Button buttonNext = view.findViewById(R.id.buttonNameNext);
        buttonNext.setOnClickListener(v -> {
            String name = getName().trim();
            if (!name.isEmpty()) {
                Toast.makeText(getContext(), "Hello, " + name + ".", Toast.LENGTH_SHORT).show();
                ((CharacterCreationActivity) getActivity()).collectName(name);
            } else {
                Toast.makeText(getContext(), "Please enter a name.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private String getName() {
        return editTextName.getText().toString();
    }


}

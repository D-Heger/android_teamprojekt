package de.teamprojekt.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import de.teamprojekt.R;

public class IconFragment extends Fragment {

    private int iconID = -1;
    private ImageView lastSelectedIcon = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_icon, container, false);

        ImageView icon1 = view.findViewById(R.id.icon1);
        ImageView icon2 = view.findViewById(R.id.icon2);
        ImageView icon3 = view.findViewById(R.id.icon3);
        ImageView icon4 = view.findViewById(R.id.icon4);
        ImageView icon5 = view.findViewById(R.id.icon5);
        ImageView icon6 = view.findViewById(R.id.icon6);
        ImageView icon7 = view.findViewById(R.id.icon7);
        ImageView icon8 = view.findViewById(R.id.icon8);
        ImageView icon9 = view.findViewById(R.id.icon9);

        View.OnClickListener listener = this::onIconSelected;

        icon1.setOnClickListener(listener);
        icon2.setOnClickListener(listener);
        icon3.setOnClickListener(listener);
        icon4.setOnClickListener(listener);
        icon5.setOnClickListener(listener);
        icon6.setOnClickListener(listener);
        icon7.setOnClickListener(listener);
        icon8.setOnClickListener(listener);
        icon9.setOnClickListener(listener);

        Button buttonFinish = view.findViewById(R.id.buttonIconNext);
        buttonFinish.setOnClickListener(v -> {
            if (iconID != -1) {
                Toast.makeText(getContext(), "You look great. We're all done now, enjoy the App!", Toast.LENGTH_SHORT).show();
                ((de.teamprojekt.Activity.CharacterCreationActivity) getActivity()).collectIcon(iconID);
            } else {
                Toast.makeText(getContext(), "Please select an icon.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void onIconSelected(View view) {
        if (lastSelectedIcon != null) {
            resetIconStyle(lastSelectedIcon);
        }
        highlightIcon(view);
        lastSelectedIcon = (ImageView) view;

        int id = view.getId();
        if (id == R.id.icon1) {
            iconID = R.drawable.icon1;
        } else if (id == R.id.icon2) {
            iconID = R.drawable.icon2;
        } else if (id == R.id.icon3) {
            iconID = R.drawable.icon3;
        } else if (id == R.id.icon4) {
            iconID = R.drawable.icon4;
        } else if (id == R.id.icon5) {
            iconID = R.drawable.icon5;
        } else if (id == R.id.icon6) {
            iconID = R.drawable.icon6;
        } else if (id == R.id.icon7) {
            iconID = R.drawable.icon7;
        } else if (id == R.id.icon8) {
            iconID = R.drawable.icon8;
        } else if (id == R.id.icon9) {
            iconID = R.drawable.icon9;
        } else {
            iconID = -1;  // No icon selected or unknown view
        }
    }

    private void resetIconStyle(ImageView icon) {
        icon.setBackgroundColor(Color.TRANSPARENT);
    }

    private void highlightIcon(View view) {
        view.setBackgroundColor(Color.BLUE);
    }
}


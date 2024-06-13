package de.teamprojekt.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ExpandableEditText extends LinearLayout {
    private EditText editText;

    public ExpandableEditText(Context context) {
        super(context);
        init(context);
    }

    public ExpandableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ExpandableEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        editText = new EditText(context);
        addView(editText);
        editText.setMaxLines(Integer.MAX_VALUE);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String text) {
        editText.setText(text);
    }

}

package de.teamprojekt;

import androidx.annotation.NonNull;

public enum Priority {
    LOW("Low", R.color.grey),
    MEDIUM("Medium", R.color.yellow),
    HIGH("High", R.color.red);

    private final String name;
    private int color;

    Priority(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}

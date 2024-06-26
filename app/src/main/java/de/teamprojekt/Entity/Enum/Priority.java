package de.teamprojekt.Entity.Enum;

import androidx.annotation.NonNull;

import de.teamprojekt.R;

public enum Priority {
    LOW("Low", R.color.grey),
    MEDIUM("Medium", R.color.yellow),
    HIGH("High", R.color.red);

    private final String name;
    private final int color;

    Priority(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public static Priority priorityFromName(String name) {
        for (Priority priority : values()) {
            if (priority.name.equals(name)) {
                return priority;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}

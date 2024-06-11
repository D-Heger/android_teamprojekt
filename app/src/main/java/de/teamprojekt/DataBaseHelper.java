package de.teamprojekt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TODO_TABLE = "TODO_TABLE";
    public static final String TODO_ID = "ID";
    public static final String TODO_PRIORITY = "PRIORITY_ID";
    public static final String TODO_CATEGORY = "CATEGORY_ID";
    public static final String TODO_STATUS = "STATUS";
    public static final String TODO_ENDE_DATE = "ENDE_DATE";
    public static final String TODO_ANFANG_DATE = "ANFANG_DATE";
    public static final String TODO_TITEL = "TITEL";
    public static final String TODO_DESCRIPTION = "DESCRIPTION";
    public static final String CHARACTER_TABLE = "CHARACTER_TABLE";
    public static final String CHARACTER_NAME = "CHARACTER_NAME";
    public static final String CHARACTER_AGE = "CHARACTER_AGE";
    public static final String CHARACTER_GENDER = "CHARACTER_GENDER";
    public static final String CHARACTER_PICTURE_ID = "CHARACTER_PICTURE_ID";
    public static final String CHARACTER_STRENGTH = "STRENGTH";
    public static final String CHARACTER_STRENGTH_EXP = "STRENGTH_EXP";
    public static final String CHARACTER_ENDURANCE = "ENDURANCE";
    public static final String CHARACTER_ENDURANCE_EXP = "ENDURANCE_EXP";
    public static final String CHARACTER_AGILITY = "AGILITY";
    public static final String CHARACTER_AGILITY_EXP = "AGILITY_EXP";
    public static final String CHARACTER_INTELLIGENCE = "INTELLIGENCE";
    public static final String CHARACTER_INTELLIGENCE_EXP = "INTELLIGENCE_EXP";
    public static final String CHARACTER_LUCK = "LUCK";
    public static final String CHARACTER_LUCK_EXP = "LUCK_EXP";
    public static final String CHARACTER_CHARISMA = "CHARISMA";
    public static final String CHARACTER_CHARISMA_EXP = "CHARISMA_EXP";
    public static final String CHARACTER_PERCEPTION = "PERCEPTION";
    public static final String CHARACTER_PERCEPTION_EXP = "PERCEPTION_EXP";
    public static final String CHARACTER_LEVEL = "CHARACTER_LEVEL";
    public static final String CHARACTER_EXP = "CHARACTER_EXP";

    public DataBaseHelper(Context context) {
        super(context, "todo_gamification.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODO_TABLE + " (" + TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_TITEL + " TEXT, " + TODO_DESCRIPTION + " TEXT," +
                TODO_ANFANG_DATE + " INTERGER, " + TODO_ENDE_DATE + " INTEGER, " + TODO_STATUS + " BOOL, " + TODO_PRIORITY + " TEXT, " + TODO_CATEGORY + " TEXT)";
        db.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + CHARACTER_TABLE + " (CHARACTER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                CHARACTER_NAME + " TEXT, " + CHARACTER_AGE + " INTEGER, " + CHARACTER_GENDER + " TEXT, " + CHARACTER_PICTURE_ID + " INTEGER," +
                CHARACTER_STRENGTH + " INTEGER, " + CHARACTER_STRENGTH_EXP + " INTEGER," +
                CHARACTER_ENDURANCE + " INTEGER, " + CHARACTER_ENDURANCE_EXP + " INTEGER, " + CHARACTER_AGILITY + " INTEGER, " + CHARACTER_AGILITY_EXP + " INTEGER," +
                CHARACTER_INTELLIGENCE + " INTEGER, " + CHARACTER_INTELLIGENCE_EXP + " INTEGER, " + CHARACTER_LUCK + " INTEGER, " + CHARACTER_LUCK_EXP + " INTEGER," +
                CHARACTER_CHARISMA + " INTEGER, " + CHARACTER_CHARISMA_EXP + " INTEGER, " + CHARACTER_PERCEPTION + " INTEGER, " + CHARACTER_PERCEPTION_EXP + " INTEGER," +
                CHARACTER_LEVEL + " INTEGER, " + CHARACTER_EXP + " INTEGER)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
}

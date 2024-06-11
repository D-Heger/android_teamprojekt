package de.teamprojekt;

import static de.teamprojekt.Category.categoryFromName;
import static de.teamprojekt.Priority.priorityFromName;
import static de.teamprojekt.Utils.toDate;
import static de.teamprojekt.Utils.toTimestamp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TODO_TABLE = "TODO_TABLE";
    public static final String TODO_ID = "ID";
    public static final String TODO_PRIORITY = "PRIORITY_ID";
    public static final String TODO_CATEGORY = "CATEGORY_ID";
    public static final String TODO_STATUS = "STATUS";
    public static final String TODO_END_DATE = "END_DATE";
    public static final String TODO_START_DATE = "START_DATE";
    public static final String TODO_TITLE = "TITLE";
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
    public static final String CHARACTER_ID = "CHARACTER_ID";
    public Context context;

    public DataBaseHelper(Context context) {
        super(context, "todo_gamification.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TODO_TABLE + " (" + TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TODO_TITLE + " TEXT, " + TODO_DESCRIPTION + " TEXT," +
                TODO_START_DATE + " INTERGER, " + TODO_END_DATE + " INTEGER, " + TODO_STATUS + " BOOL, " + TODO_PRIORITY + " TEXT, " + TODO_CATEGORY + " TEXT)";
        db.execSQL(createTableStatement);
        createTableStatement = "CREATE TABLE " + CHARACTER_TABLE + " (" + CHARACTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
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

    public void dropDatabase(SQLiteDatabase db) {
        context.deleteDatabase("todo_gamification.db");
    }

    public boolean addCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHARACTER_NAME, character.getName());
        values.put(CHARACTER_AGE, character.getAge());
        values.put(CHARACTER_GENDER, character.getGender());
        values.put(CHARACTER_PICTURE_ID, character.getIcon());
        values.put(CHARACTER_LEVEL, character.getLevel());
        values.put(CHARACTER_EXP, character.getExperience());
        values.put(CHARACTER_STRENGTH, character.getStrength());
        values.put(CHARACTER_STRENGTH_EXP, character.getStrength_exp());
        values.put(CHARACTER_PERCEPTION, character.getPerception());
        values.put(CHARACTER_PERCEPTION_EXP, character.getPerception_exp());
        values.put(CHARACTER_ENDURANCE, character.getEndurance());
        values.put(CHARACTER_ENDURANCE_EXP, character.getEndurance_exp());
        values.put(CHARACTER_CHARISMA, character.getCharisma());
        values.put(CHARACTER_CHARISMA_EXP, character.getCharisma_exp());
        values.put(CHARACTER_INTELLIGENCE, character.getIntelligence());
        values.put(CHARACTER_INTELLIGENCE_EXP, character.getIntelligence_exp());
        values.put(CHARACTER_AGILITY, character.getAgility());
        values.put(CHARACTER_AGILITY_EXP, character.getAgility_exp());
        values.put(CHARACTER_LUCK, character.getLuck());
        values.put(CHARACTER_LUCK_EXP, character.getLuck_exp());

        long result = db.insert(CHARACTER_TABLE, null, values);
        db.close();
        return result != -1;
    }

    public boolean addTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TODO_TITLE, todo.getTitle());
        values.put(TODO_DESCRIPTION, todo.getDescription());
        values.put(TODO_START_DATE, toTimestamp(todo.getStartDate()));
        values.put(TODO_END_DATE, toTimestamp(todo.getEndDate()));
        values.put(TODO_STATUS, todo.getStatus());
        values.put(TODO_PRIORITY, todo.getPriority().toString());
        values.put(TODO_CATEGORY, todo.getCategory().toString());

        long result = db.insert(TODO_TABLE, null, values);
        db.close();
        return result != -1;
    }

    @SuppressLint("Range")
    public Character getCharacter() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + CHARACTER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        Character.Builder character = new Character.Builder();
        if (cursor.moveToFirst()) {
            character.name(cursor.getString(cursor.getColumnIndex(CHARACTER_NAME)))
                    .age(cursor.getInt(cursor.getColumnIndex(CHARACTER_AGE)))
                    .gender(cursor.getString(cursor.getColumnIndex(CHARACTER_GENDER)))
                    .icon(cursor.getInt(cursor.getColumnIndex(CHARACTER_PICTURE_ID)))
                    .level(cursor.getInt(cursor.getColumnIndex(CHARACTER_LEVEL)))
                    .experience(cursor.getInt(cursor.getColumnIndex(CHARACTER_EXP)))
                    .strength(cursor.getInt(cursor.getColumnIndex(CHARACTER_STRENGTH)))
                    .strength_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_STRENGTH_EXP)))
                    .perception(cursor.getInt(cursor.getColumnIndex(CHARACTER_PERCEPTION)))
                    .perception_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_PERCEPTION_EXP)))
                    .endurance(cursor.getInt(cursor.getColumnIndex(CHARACTER_ENDURANCE)))
                    .endurance_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_ENDURANCE_EXP)))
                    .charisma(cursor.getInt(cursor.getColumnIndex(CHARACTER_CHARISMA)))
                    .charisma_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_CHARISMA_EXP)))
                    .intelligence(cursor.getInt(cursor.getColumnIndex(CHARACTER_INTELLIGENCE)))
                    .intelligence_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_INTELLIGENCE_EXP)))
                    .agility(cursor.getInt(cursor.getColumnIndex(CHARACTER_AGILITY)))
                    .agility_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_AGILITY_EXP)))
                    .luck(cursor.getInt(cursor.getColumnIndex(CHARACTER_LUCK)))
                    .luck_exp(cursor.getInt(cursor.getColumnIndex(CHARACTER_LUCK_EXP)));
        }
        cursor.close();
        db.close();
        return character.build();
    }

    @SuppressLint("Range")
    public List<Todo> getAllTodos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TODO_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        List<Todo> todos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Todo todo = new Todo.Builder()
                        .title(cursor.getString(cursor.getColumnIndex(TODO_TITLE)))
                        .description(cursor.getString(cursor.getColumnIndex(TODO_DESCRIPTION)))
                        .startDate(toDate(cursor.getLong(cursor.getColumnIndex(TODO_START_DATE))))
                        .endDate(toDate(cursor.getLong(cursor.getColumnIndex(TODO_END_DATE))))
                        .status(cursor.getInt(cursor.getColumnIndex(TODO_STATUS)) == 1)
                        .priority(priorityFromName(cursor.getString(cursor.getColumnIndex(TODO_PRIORITY))))
                        .category(categoryFromName(cursor.getString(cursor.getColumnIndex(TODO_CATEGORY))))
                        .build();
                todos.add(todo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return todos;
    }

    @SuppressLint("Range")
    public Todo getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TODO_TABLE + " WHERE " + TODO_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        Todo.Builder todo = new Todo.Builder();
        if (cursor.moveToFirst()) {
            todo.title(cursor.getString(cursor.getColumnIndex(TODO_TITLE)))
                    .description(cursor.getString(cursor.getColumnIndex(TODO_DESCRIPTION)))
                    .startDate(toDate(cursor.getLong(cursor.getColumnIndex(TODO_START_DATE))))
                    .endDate(toDate(cursor.getLong(cursor.getColumnIndex(TODO_END_DATE))))
                    .status(cursor.getInt(cursor.getColumnIndex(TODO_STATUS)) == 1)
                    .priority(priorityFromName(cursor.getString(cursor.getColumnIndex(TODO_PRIORITY))))
                    .category(categoryFromName(cursor.getString(cursor.getColumnIndex(TODO_CATEGORY))));
        }
        cursor.close();
        db.close();
        return todo.build();
    }

    public boolean updateCharacter(String Colum, String Value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = CHARACTER_ID + " = 1";
        values.put(Colum, Value);
        long result = db.update(CHARACTER_TABLE, values, whereClause, null);
        db.close();
        return result != -1;
    }

    public boolean updateTodo(String Colum, String Value, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = TODO_ID + " = " + id;
        values.put(Colum, Value);
        long result = db.update(TODO_TABLE, values, whereClause, null);
        db.close();
        return result != -1;
    }

    public boolean deleteTodo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = TODO_ID + " = " + id;
        long result = db.delete(TODO_TABLE, whereClause, null);
        db.close();
        return result != -1;
    }

}

package de.teamprojekt.Util;

import static de.teamprojekt.Entity.Enum.Category.categoryFromName;
import static de.teamprojekt.Entity.Enum.Priority.priorityFromName;
import static de.teamprojekt.Util.DatabaseConstants.CharacterColumn;
import static de.teamprojekt.Util.DatabaseConstants.Table;
import static de.teamprojekt.Util.DatabaseConstants.TodoColumn;
import static de.teamprojekt.Util.Utils.toDate;
import static de.teamprojekt.Util.Utils.toTimestamp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import de.teamprojekt.Entity.Character;
import de.teamprojekt.Entity.Todo;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo_gamification.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + Table.TODO_TABLE + " (" +
            TodoColumn.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TodoColumn.TITLE + " TEXT, " +
            TodoColumn.DESCRIPTION + " TEXT, " +
            TodoColumn.START_DATE + " INTEGER, " +
            TodoColumn.END_DATE + " INTEGER, " +
            TodoColumn.STATUS + " BOOL, " +
            TodoColumn.PRIORITY_ID + " TEXT, " +
            TodoColumn.CATEGORY_ID + " TEXT)";

    private static final String SQL_CREATE_CHARACTER_TABLE = "CREATE TABLE " + Table.CHARACTER_TABLE + " (" +
            CharacterColumn.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CharacterColumn.NAME + " TEXT, " +
            CharacterColumn.AGE + " INTEGER, " +
            CharacterColumn.GENDER + " TEXT, " +
            CharacterColumn.ICON + " INTEGER, " +
            CharacterColumn.STRENGTH + " INTEGER, " +
            CharacterColumn.STRENGTH_EXP + " INTEGER, " +
            CharacterColumn.ENDURANCE + " INTEGER, " +
            CharacterColumn.ENDURANCE_EXP + " INTEGER, " +
            CharacterColumn.AGILITY + " INTEGER, " +
            CharacterColumn.AGILITY_EXP + " INTEGER, " +
            CharacterColumn.INTELLIGENCE + " INTEGER, " +
            CharacterColumn.INTELLIGENCE_EXP + " INTEGER, " +
            CharacterColumn.LUCK + " INTEGER, " +
            CharacterColumn.LUCK_EXP + " INTEGER, " +
            CharacterColumn.CHARISMA + " INTEGER, " +
            CharacterColumn.CHARISMA_EXP + " INTEGER, " +
            CharacterColumn.PERCEPTION + " INTEGER, " +
            CharacterColumn.PERCEPTION_EXP + " INTEGER, " +
            CharacterColumn.LEVEL + " INTEGER, " +
            CharacterColumn.EXP + " INTEGER)";

    public Context context;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO_TABLE);
        db.execSQL(SQL_CREATE_CHARACTER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: Implement onUpgrade
    }

    public void dropDatabase() {
        context.deleteDatabase(DATABASE_NAME);
    }

    public boolean addCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = characterToContentValues(character);
        long result = db.insert(Table.CHARACTER_TABLE.toString(), null, values);
        db.close();
        return result != -1;
    }

    public boolean addTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = todoToContentValues(todo);
        long result = db.insert(Table.TODO_TABLE.toString(), null, values);
        db.close();
        return result != -1;
    }


    @SuppressLint("Range")
    public Character getCharacter() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table.CHARACTER_TABLE, null);
        Character.Builder character = new Character.Builder();
        if (cursor.moveToFirst()) {
            character = cursorToCharacter(cursor);
        }
        cursor.close();
        db.close();
        return character.build();
    }

    @SuppressLint("Range")
    public List<Todo> getAllTodos() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Todo> todos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table.TODO_TABLE, null);
        if (cursor.moveToFirst()) {
            do {
                todos.add(cursorToTodo(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return todos;
    }

    @SuppressLint("Range")
    public Todo getTodo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Table.TODO_TABLE + " WHERE " + TodoColumn.ID + " = " + id, null);
        Todo todo = null;
        if (cursor.moveToFirst()) {
            todo = cursorToTodo(cursor);
        }
        cursor.close();
        db.close();
        return todo;
    }


    public boolean updateCharacterOneValue(CharacterColumn column, String value) {
        return update(Table.CHARACTER_TABLE.toString(), column.toString(), value, CharacterColumn.ID + " = 1");
    }

    public boolean updateCharacter(Character character) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(Table.CHARACTER_TABLE.toString(), characterToContentValues(character), CharacterColumn.ID + " = 1", null);
        db.close();
        return result != -1;
    }


    public boolean updateTodoOneValue(TodoColumn column, String value, int id) {
        return update(Table.TODO_TABLE.toString(), column.toString(), value, TodoColumn.ID + " = " + id);
    }

    public boolean updateTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(Table.TODO_TABLE.toString(), todoToContentValues(todo), TodoColumn.ID + " = " + todo.getId(), null);
        db.close();
        return result != -1;
    }

    public boolean deleteTodo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = TodoColumn.ID + " = " + id;
        long result = db.delete(Table.TODO_TABLE.toString(), whereClause, null);
        db.close();
        return result != -1;
    }

    private boolean update(String table, String column, String value, String whereClause) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column, value);
        long result = db.update(table, values, whereClause, null);
        db.close();
        return result != -1;
    }

    private ContentValues characterToContentValues(Character character) {
        ContentValues values = new ContentValues();
        values.put(CharacterColumn.NAME.toString(), character.getName());
        values.put(CharacterColumn.AGE.toString(), character.getAge());
        values.put(CharacterColumn.GENDER.toString(), character.getGender());
        values.put(CharacterColumn.ICON.toString(), character.getIcon());
        values.put(CharacterColumn.LEVEL.toString(), character.getLevel());
        values.put(CharacterColumn.EXP.toString(), character.getExperience());
        values.put(CharacterColumn.STRENGTH.toString(), character.getStrength());
        values.put(CharacterColumn.STRENGTH_EXP.toString(), character.getStrength_exp());
        values.put(CharacterColumn.PERCEPTION.toString(), character.getPerception());
        values.put(CharacterColumn.PERCEPTION_EXP.toString(), character.getPerception_exp());
        values.put(CharacterColumn.ENDURANCE.toString(), character.getEndurance());
        values.put(CharacterColumn.ENDURANCE_EXP.toString(), character.getEndurance_exp());
        values.put(CharacterColumn.CHARISMA.toString(), character.getCharisma());
        values.put(CharacterColumn.CHARISMA_EXP.toString(), character.getCharisma_exp());
        values.put(CharacterColumn.INTELLIGENCE.toString(), character.getIntelligence());
        values.put(CharacterColumn.INTELLIGENCE_EXP.toString(), character.getIntelligence_exp());
        values.put(CharacterColumn.AGILITY.toString(), character.getAgility());
        values.put(CharacterColumn.AGILITY_EXP.toString(), character.getAgility_exp());
        values.put(CharacterColumn.LUCK.toString(), character.getLuck());
        values.put(CharacterColumn.LUCK_EXP.toString(), character.getLuck_exp());
        return values;
    }

    private ContentValues todoToContentValues(Todo todo) {
        ContentValues values = new ContentValues();
        values.put(TodoColumn.TITLE.toString(), todo.getTitle());
        values.put(TodoColumn.DESCRIPTION.toString(), todo.getDescription());
        values.put(TodoColumn.START_DATE.toString(), toTimestamp(todo.getStartDate()));
        values.put(TodoColumn.END_DATE.toString(), toTimestamp(todo.getEndDate()));
        values.put(TodoColumn.STATUS.toString(), todo.getStatus());
        values.put(TodoColumn.PRIORITY_ID.toString(), todo.getPriority().toString());
        values.put(TodoColumn.CATEGORY_ID.toString(), todo.getCategory().toString());
        return values;
    }

    @SuppressLint("Range")
    private Character.Builder cursorToCharacter(Cursor cursor) {
        return new Character.Builder()
                .name(cursor.getString(cursor.getColumnIndex(CharacterColumn.NAME.toString())))
                .age(cursor.getInt(cursor.getColumnIndex(CharacterColumn.AGE.toString())))
                .gender(cursor.getString(cursor.getColumnIndex(CharacterColumn.GENDER.toString())))
                .icon(cursor.getInt(cursor.getColumnIndex(CharacterColumn.ICON.toString())))
                .level(cursor.getInt(cursor.getColumnIndex(CharacterColumn.LEVEL.toString())))
                .experience(cursor.getInt(cursor.getColumnIndex(CharacterColumn.EXP.toString())))
                .strength(cursor.getInt(cursor.getColumnIndex(CharacterColumn.STRENGTH.toString())))
                .strength_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.STRENGTH_EXP.toString())))
                .perception(cursor.getInt(cursor.getColumnIndex(CharacterColumn.PERCEPTION.toString())))
                .perception_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.PERCEPTION_EXP.toString())))
                .endurance(cursor.getInt(cursor.getColumnIndex(CharacterColumn.ENDURANCE.toString())))
                .endurance_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.ENDURANCE_EXP.toString())))
                .charisma(cursor.getInt(cursor.getColumnIndex(CharacterColumn.CHARISMA.toString())))
                .charisma_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.CHARISMA_EXP.toString())))
                .intelligence(cursor.getInt(cursor.getColumnIndex(CharacterColumn.INTELLIGENCE.toString())))
                .intelligence_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.INTELLIGENCE_EXP.toString())))
                .agility(cursor.getInt(cursor.getColumnIndex(CharacterColumn.AGILITY.toString())))
                .agility_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.AGILITY_EXP.toString())))
                .luck(cursor.getInt(cursor.getColumnIndex(CharacterColumn.LUCK.toString())))
                .luck_exp(cursor.getInt(cursor.getColumnIndex(CharacterColumn.LUCK_EXP.toString())));
    }

    @SuppressLint("Range")
    private Todo cursorToTodo(Cursor cursor) {
        return new Todo.Builder()
                .id(cursor.getInt(cursor.getColumnIndex(TodoColumn.ID.toString())))
                .title(cursor.getString(cursor.getColumnIndex(TodoColumn.TITLE.toString())))
                .description(cursor.getString(cursor.getColumnIndex(TodoColumn.DESCRIPTION.toString())))
                .startDate(toDate(cursor.getLong(cursor.getColumnIndex(TodoColumn.START_DATE.toString()))))
                .endDate(toDate(cursor.getLong(cursor.getColumnIndex(TodoColumn.END_DATE.toString()))))
                .status(cursor.getInt(cursor.getColumnIndex(TodoColumn.STATUS.toString())) == 1)
                .priority(priorityFromName(cursor.getString(cursor.getColumnIndex(TodoColumn.PRIORITY_ID.toString()))))
                .category(categoryFromName(cursor.getString(cursor.getColumnIndex(TodoColumn.CATEGORY_ID.toString()))))
                .build();
    }

}

package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Date;
import java.util.List;

import de.teamprojekt.Activity.Adapter.TodoAdapter;
import de.teamprojekt.Entity.Character;
import de.teamprojekt.Entity.Enum.Category;
import de.teamprojekt.Entity.Enum.Priority;
import de.teamprojekt.Entity.Todo;
import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private static final int REQUEST_CODE = 1;
    private TodoAdapter todoAdapter;
    private RecyclerView todoListView;
    private DataBaseHelper dbHelper;
    private List<Todo> todoList;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_list);

        // Initialize RecyclerView and DB helper
        todoListView = findViewById(R.id.recyclerView);
        dbHelper = new DataBaseHelper(this);

        // ADDING TODOS FOR TESTING PURPOSES IF TODO TABLE IS EMPTY
        todoList = dbHelper.getAllTodos();
        if (todoList.isEmpty()) {
            dbHelper.addTodo(new Todo.Builder()
                    .id(0)
                    .title("Test")
                    .description("Test description")
                    .startDate(new Date(System.currentTimeMillis()))
                    .endDate(new Date(System.currentTimeMillis() * 2))
                    .status(false)
                    .priority(Priority.MEDIUM)
                    .category(Category.CHESS)
                    .build()
            );
            dbHelper.addTodo(new Todo.Builder()
                    .id(1)
                    .title("Test")
                    .description("Test description")
                    .startDate(new Date(System.currentTimeMillis()))
                    .endDate(new Date(System.currentTimeMillis() * 2))
                    .status(false)
                    .priority(Priority.MEDIUM)
                    .category(Category.CHESS)
                    .build()
            );
            dbHelper.addTodo(new Todo.Builder()
                    .id(2)
                    .title("Test")
                    .description("Test description")
                    .startDate(new Date(System.currentTimeMillis()))
                    .endDate(new Date(System.currentTimeMillis() * 2))
                    .status(false)
                    .priority(Priority.MEDIUM)
                    .category(Category.CHESS)
                    .build()
            );
        }

        // ADDING A CHARACTER FOR TESTING PURPOSES IF CHARACTER TABLE IS EMPTY
//        if (dbHelper.getCharacter() == null) {
//            dbHelper.addCharacter(new Character.Builder()
//                            .icon(R.drawable.ic)
//                    .build()
//            );
//        }

        // Load To-do items from the database and fill the adapter
        todoList = dbHelper.getAllTodos();
        todoAdapter = new TodoAdapter(todoList, this);

        // Set the adapter for the RecyclerView
        todoListView.setAdapter(todoAdapter);
        todoListView.setLayoutManager(new LinearLayoutManager(this));

        // Register shared preferences listener
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        // Apply initial preferences
        applyPreferences();

        // Set up swipe-to-delete functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                onTodoSwipe(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(todoListView);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        applyPreferences();
    }

    private void applyPreferences() {
        // Retrieve preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String fontSize = sharedPreferences.getString("pref_font_size", "medium");
        int backgroundColor = sharedPreferences.getInt("pref_background_color", R.color.white);
        int textColor = sharedPreferences.getInt("pref_text_color", R.color.black);
        int notCompletedColor = sharedPreferences.getInt("pref_not_completed_color", R.color.red);
        int completedColor = sharedPreferences.getInt("pref_completed_color", R.color.green);

        // Apply preferences
        RelativeLayout layout = findViewById(R.id.main);
        layout.setBackgroundColor(getResources().getColor(backgroundColor, null));
        todoAdapter.applyPreferences(fontSize, textColor, notCompletedColor, completedColor);
    }

    @Override
    public void onItemClick(int position) {
        startActivityForResult(new Intent(this, DetailActivity.class).putExtra("TODO_ID", todoList.get(position).getId()), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            refreshTodoList();
        }
    }

    private void refreshTodoList() {
        todoList.clear();
        todoList.addAll(dbHelper.getAllTodos());
        todoAdapter.notifyDataSetChanged();
    }

    private void onTodoSwipe(int position) {
        Todo todo = todoList.get(position);
        dbHelper.deleteTodo(todo.getId());
        todoList.remove(position);
        todoAdapter.notifyItemRemoved(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (handleSelectedOption(this, item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
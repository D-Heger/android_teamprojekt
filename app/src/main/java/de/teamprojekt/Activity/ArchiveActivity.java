package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import de.teamprojekt.Activity.Adapter.TodoAdapter;
import de.teamprojekt.Entity.Todo;
import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class ArchiveActivity extends AppCompatActivity implements TodoAdapter.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private TodoAdapter todoAdapter;
    private RecyclerView todoListView;
    private DataBaseHelper dbHelper;
    private List<Todo> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_list);

        // Initialize RecyclerView and DB helper
        todoListView = findViewById(R.id.recyclerView);
        dbHelper = new DataBaseHelper(this);

        //Load all todos
        todoList = dbHelper.getAllTodos();
        todoAdapter = new TodoAdapter(todoList, this);

        // Set the adapter for the RecyclerView
        todoListView.setAdapter(todoAdapter);
        todoListView.setLayoutManager(new LinearLayoutManager(this));

        // Register shared preference listener
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        // Apply initial preferences
        applyPreferences();

        // Set up fab
        findViewById(R.id.fab).setOnClickListener(v -> {
            finish();
        });

        // Set up swipe to delete
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

    private void onTodoSwipe(int position) {
        Todo todo = todoList.get(position);
        dbHelper.deleteTodo(todo.getId());
        todoList.remove(position);
        todoAdapter.notifyItemRemoved(position);
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
    public void onItemClick(int position) {
        Log.d("ArchiveActivity", "No action on item click");
    }
}

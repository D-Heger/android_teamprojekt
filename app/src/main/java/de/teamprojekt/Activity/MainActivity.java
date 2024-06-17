package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import de.teamprojekt.Activity.Adapter.TodoAdapter;
import de.teamprojekt.Entity.Enum.Category;
import de.teamprojekt.Entity.Enum.Priority;
import de.teamprojekt.Entity.Todo;
import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private static final int REQUEST_CODE = 1;
    private TodoAdapter todoAdapter;
    private DataBaseHelper dbHelper;
    private List<Todo> todoList;
    private SharedPreferences sharedPreferences;
    private boolean startDateAscending = true;
    private boolean endDateAscending = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_list);

        // Initialize RecyclerView and DB helper
        RecyclerView todoListView = findViewById(R.id.recyclerView);
        dbHelper = new DataBaseHelper(this);

        // If there is no character in the database, launch the character creation process
        if (dbHelper.getCharacter().getName() == null) {
            startActivityForResult(new Intent(this, CharacterCreationActivity.class), REQUEST_CODE);
        }

        // FIXME: REMOVE THIS
        // ADDING TODOS FOR TESTING PURPOSES IF TODO TABLE IS EMPTY
        todoList = dbHelper.getAllNonCompletedTodos();
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

//        // FIXME: REMOVE THIS
//        // ADDING A CHARACTER FOR TESTING PURPOSES IF CHARACTER TABLE IS EMPTY
//        if (dbHelper.getCharacter() == null) {
//            if (!dbHelper.addCharacter(new Character.Builder()
//                    .name("Red Guy")
//                    .age(28)
//                    .gender("RED")
//                    .icon(R.drawable.male1)
//                    .level(0)
//                    .experience(0)
//                    .strength(0)
//                    .strength_exp(0)
//                    .perception(0)
//                    .perception_exp(0)
//                    .endurance(10)
//                    .endurance_exp(100)
//                    .charisma(10)
//                    .charisma_exp(100)
//                    .intelligence(10)
//                    .intelligence_exp(100)
//                    .agility(10)
//                    .agility_exp(100)
//                    .luck(10)
//                    .luck_exp(100)
//                    .build()
//            )) {
//                Log.e("MainActivity", "Failed to add character");
//            }
//        }

        // Load all completed todos from the database and fill the adapter
        todoList = dbHelper.getAllNonCompletedTodos();
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
        todoAdapter.updateData(dbHelper.getAllNonCompletedTodos());
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
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterForSearchQuery(newText);
                return true;
            }
        });

        return true;
    }

    private void filterForSearchQuery(String query) {
        List<Todo> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            refreshTodoList();
            return;
        }
        for (Todo todo : todoList) {
            if (todo.getTitle().toLowerCase().contains(query.toLowerCase()) || todo.getDescription().toLowerCase().contains(query.toLowerCase()) || todo.getCategory().toString().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(todo);
            }
        }
        todoAdapter.setTodoList(filteredList);
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_search).setVisible(true);
        menu.findItem(R.id.action_filter).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.filter_reset) {
            Toast.makeText(this, "Reset your filter.", Toast.LENGTH_SHORT).show();
            refreshTodoList();
            return true;
        } else if (id == R.id.filter_start_date) {
            if (startDateAscending) {
                todoList.sort(Comparator.comparing(Todo::getStartDate));
                startDateAscending = false;
            } else {
                todoList.sort((o1, o2) -> o2.getStartDate().compareTo(o1.getStartDate()));
                startDateAscending = true;
            }
            todoAdapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.filter_end_date) {
            if (endDateAscending) {
                todoList.sort(Comparator.comparing(Todo::getEndDate));
                endDateAscending = false;
            } else {
                todoList.sort((o1, o2) -> o2.getEndDate().compareTo(o1.getEndDate()));
                endDateAscending = true;
            }
            todoAdapter.notifyDataSetChanged();
            return true;
        } else if (id == R.id.filter_priority) {
            showFilterDialog(Priority.values());
            return true;
        } else if (id == R.id.filter_category) {
            showFilterDialog(Category.values());
            return true;
        }
        if (handleSelectedOption(this, item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilterDialog(Enum[] values) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] options = Arrays.stream(values).map(Enum::name).toArray(String[]::new);
        builder.setItems(options, (dialog, which) -> {
            filterTodoList(options[which]);
        });
        builder.create().show();
    }

    private void filterTodoList(String filterOption) {
        List<Todo> filteredList;
        if (Arrays.stream(Priority.values()).anyMatch(priority -> priority.name().equals(filterOption))) {
            filteredList = dbHelper.getTodosByPriority(Priority.valueOf(filterOption));
        } else {
            filteredList = dbHelper.getTodosByCategory(Category.valueOf(filterOption));
        }
        todoAdapter.updateData(filteredList);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
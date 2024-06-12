package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.teamprojekt.Entity.Todo;
import de.teamprojekt.R;
import de.teamprojekt.Util.DataBaseHelper;

public class DetailActivity extends AppCompatActivity {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private TextView titleDetail;
    private TextView descriptionDetail;
    private Button buttonStartDate;
    private Button buttonEndDate;
    private TextView startDate;
    private TextView endDate;
    private CheckBox checkBox;
    private Spinner priorityDetail;
    private Spinner categoryDetail;
    private Button buttonDelete;
    private Button buttonSave;
    private DataBaseHelper dbHelper;
    private Todo todo;
    private Calendar selectedStartDate;
    private Calendar selectedEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleDetail = findViewById(R.id.text_title_detail);
        descriptionDetail = findViewById(R.id.text_description_detail);
        buttonStartDate = findViewById(R.id.button_select_start_date);
        buttonEndDate = findViewById(R.id.button_select_end_date);
        startDate = findViewById(R.id.text_view_selected_start_date);
        endDate = findViewById(R.id.text_view_selected_end_date);
        checkBox = findViewById(R.id.checkBox);
        priorityDetail = findViewById(R.id.spinner_priority_detail);
        categoryDetail = findViewById(R.id.spinner_category_detail);
        buttonDelete = findViewById(R.id.button_delete_detail);
        buttonSave = findViewById(R.id.button_save_detail);

        dbHelper = new DataBaseHelper(this);

        if (getIntent().hasExtra("TODO_ID")) {
            int todoId = getIntent().getIntExtra("TODO_ID", -1);
            todo = dbHelper.getTodo(todoId);
            initVals();
        }
        buttonSave.setOnClickListener(v -> saveTodo());
        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_add);
    }

    private void initVals() {
        titleDetail.setText(todo.getTitle());
        descriptionDetail.setText(todo.getDescription());
        startDate.setText(dateFormat.format(todo.getStartDate()));
        endDate.setText(dateFormat.format(todo.getEndDate()));
        checkBox.setChecked(todo.getStatus());
    }

    private void saveTodo() {
        if (todo == null) {
            todo = new Todo();
        }
        todo.setTitle(titleDetail.getText().toString());
        todo.setDescription(descriptionDetail.getText().toString());
        todo.setStatus(checkBox.isChecked());

        if (todo.getId() == -1) {
            dbHelper.addTodo(todo);
        } else {
            dbHelper.updateTodo(todo);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (handleSelectedOption(this, item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

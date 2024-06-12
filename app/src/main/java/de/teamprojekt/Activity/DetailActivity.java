package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;
import static de.teamprojekt.Util.Utils.toSqlDate;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.teamprojekt.Entity.Enum.Category;
import de.teamprojekt.Entity.Enum.Priority;
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

        // If an existing to-do item is being edited, populate the fields with its data
        if (getIntent().hasExtra("TODO_ID")) {
            int todoId = getIntent().getIntExtra("TODO_ID", -1);
            todo = dbHelper.getTodo(todoId);
            initVals();
        }

        buttonStartDate.setOnClickListener(v -> {
            final Calendar startDateC = Calendar.getInstance();
            int _year = startDateC.get(Calendar.YEAR);
            int _month = startDateC.get(Calendar.MONTH);
            int _day = startDateC.get(Calendar.DAY_OF_MONTH);
            if (todo != null) {
                _year = todo.getStartDate().getYear() + 1900;
                _month = todo.getStartDate().getMonth();
                _day = todo.getStartDate().getDate();
            }

            DatePickerDialog startDatePicker = new DatePickerDialog(DetailActivity.this, (view, year, month, dayOfMonth) -> {
                Calendar tempStartDate = Calendar.getInstance();
                tempStartDate.clear();
                tempStartDate.set(year, month, dayOfMonth);

                if (selectedEndDate != null && tempStartDate.after(selectedEndDate)) {
                    Toast.makeText(DetailActivity.this, "Start date cannot be after end date.", Toast.LENGTH_SHORT).show();
                } else {
                    selectedStartDate = tempStartDate;
                    startDate.setText(dateFormat.format(selectedStartDate.getTime()));
                }
            }, _year, _month, _day);
            startDatePicker.show();
        });

        buttonEndDate.setOnClickListener(v -> {
            final Calendar endDateC = Calendar.getInstance();
            int _year = endDateC.get(Calendar.YEAR);
            int _month = endDateC.get(Calendar.MONTH);
            int _day = endDateC.get(Calendar.DAY_OF_MONTH) + 1;
            if (todo != null) {
                _year = todo.getEndDate().getYear() + 1900;
                _month = todo.getEndDate().getMonth();
                _day = todo.getEndDate().getDate();
            }

            DatePickerDialog endDatePicker = new DatePickerDialog(DetailActivity.this, (view, year, month, dayOfMonth) -> {
                Calendar tempEndDate = Calendar.getInstance();
                tempEndDate.clear();
                tempEndDate.set(year, month, dayOfMonth);

                if (selectedStartDate != null && tempEndDate.before(selectedStartDate)) {
                    Toast.makeText(DetailActivity.this, "End date cannot be before start date.", Toast.LENGTH_SHORT).show();
                } else {
                    selectedEndDate = tempEndDate;
                    endDate.setText(dateFormat.format(selectedEndDate.getTime()));
                }
            }, _year, _month, _day);
            endDatePicker.show();
        });

        // Set up priority spinner
        ArrayAdapter<Priority> priorityArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Priority.values());
        priorityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priorityDetail.setAdapter(priorityArrayAdapter);
        if (todo != null) {
            priorityDetail.setSelection(todo.getPriority().ordinal());
        }

        // Set up category spinner
        ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Category.values());
        categoryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryDetail.setAdapter(categoryArrayAdapter);
        if (todo != null) {
            categoryDetail.setSelection(todo.getCategory().ordinal());
        }

        // Set up save and delete buttons
        buttonSave.setOnClickListener(v -> saveTodo());
        buttonDelete.setOnClickListener(v -> deleteTodo());

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

        selectedStartDate = Calendar.getInstance();
        selectedStartDate.clear();
        selectedStartDate.setTime(todo.getStartDate());

        selectedEndDate = Calendar.getInstance();
        selectedEndDate.clear();
        selectedEndDate.setTime(todo.getEndDate());
    }

    private void saveTodo() {
        if (todo == null) {
            todo = new Todo();
        }
        
        todo.setTitle(titleDetail.getText().toString());
        todo.setDescription(descriptionDetail.getText().toString());
        todo.setStatus(checkBox.isChecked());
        todo.setStartDate(toSqlDate(selectedStartDate.getTime()));
        todo.setEndDate(toSqlDate(selectedEndDate.getTime()));
        todo.setPriority((Priority) priorityDetail.getSelectedItem());
        todo.setCategory((Category) categoryDetail.getSelectedItem());

        if (todo.getId() == -1) {
            dbHelper.addTodo(todo);
        } else {
            dbHelper.updateTodo(todo);
        }
        finish();
    }

    private void deleteTodo() {
        if (todo != null) {
            dbHelper.deleteTodo(todo.getId());
        }
        finish();
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

}

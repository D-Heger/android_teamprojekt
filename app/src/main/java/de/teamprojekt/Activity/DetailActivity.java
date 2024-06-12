package de.teamprojekt.Activity;

import static de.teamprojekt.Util.Utils.handleSelectedOption;
import static de.teamprojekt.Util.Utils.setNavBar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        // Usage for start date button
        setupDatePickerButton(buttonStartDate, getInitialStartDate(), startDate, new DateValidator() {
            @Override
            public boolean isDateValid(Calendar date) {
                return selectedEndDate == null || !date.after(selectedEndDate);
            }

            @Override
            public String getErrorMessage() {
                return "Start date cannot be after end date.";
            }

            @Override
            public void updateSelectedDate(Calendar date) {
                selectedStartDate = date;
            }
        });

        // Usage for end date button
        setupDatePickerButton(buttonEndDate, getInitialEndDate(), endDate, new DateValidator() {
            @Override
            public boolean isDateValid(Calendar date) {
                return selectedStartDate == null || !date.before(selectedStartDate);
            }

            @Override
            public String getErrorMessage() {
                return "End date cannot be before start date.";
            }

            @Override
            public void updateSelectedDate(Calendar date) {
                selectedEndDate = date;
            }
        });


        buttonSave.setOnClickListener(v -> saveTodo());
        // Set up bottom navigation bar
        BottomNavigationView bnView = findViewById(R.id.bottom_navigation);
        setNavBar(bnView, this, R.id.navigation_add);
    }

    public void setupDatePickerButton(Button button, Calendar initialDate, TextView dateTextView, DateValidator dateValidator) {
        button.setOnClickListener(v -> {
            int _year = initialDate.get(Calendar.YEAR);
            int _month = initialDate.get(Calendar.MONTH);
            int _day = initialDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePicker = new DatePickerDialog(DetailActivity.this, (view, year, month, dayOfMonth) -> {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.clear();
                selectedDate.set(year, month, dayOfMonth);

                if (!dateValidator.isDateValid(selectedDate)) {
                    Toast.makeText(DetailActivity.this, dateValidator.getErrorMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    dateValidator.updateSelectedDate(selectedDate);
                    dateTextView.setText(dateFormat.format(selectedDate.getTime()));
                }
            }, _year, _month, _day);
            datePicker.show();
        });
    }

    // Helper methods to get initial dates
    private Calendar getInitialStartDate() {
        Calendar startDateC = Calendar.getInstance();
        if (todo != null) {
            startDateC.set(todo.getStartDate().getYear() + 1900, todo.getStartDate().getMonth(), todo.getStartDate().getDate());
        }
        return startDateC;
    }

    private Calendar getInitialEndDate() {
        Calendar endDateC = Calendar.getInstance();
        if (todo != null) {
            endDateC.set(todo.getEndDate().getYear() + 1900, todo.getEndDate().getMonth(), todo.getEndDate().getDate());
        } else {
            endDateC.add(Calendar.DAY_OF_MONTH, 1);
        }
        return endDateC;
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

    public interface DateValidator {
        boolean isDateValid(Calendar date);

        String getErrorMessage();

        void updateSelectedDate(Calendar date);
    }
}

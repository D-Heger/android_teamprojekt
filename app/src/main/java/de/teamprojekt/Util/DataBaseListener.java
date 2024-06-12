package de.teamprojekt.Util;

import de.teamprojekt.Entity.Todo;

public class DataBaseListener {
    public interface TodoChangeListener {
        void onTodoAdded(Todo todo);

        void onTodoDeleted(int todoId);

        void onTodoUpdated(Todo todo);
    }

    public class TodoManager implements TodoChangeListener {
        @Override
        public void onTodoAdded(Todo todo) {
            // Add todo to database
        }

        @Override
        public void onTodoDeleted(int todoId) {
            // Delete todo from database
        }

        @Override
        public void onTodoUpdated(Todo todo) {
            // Update todo in database
        }
    }
}

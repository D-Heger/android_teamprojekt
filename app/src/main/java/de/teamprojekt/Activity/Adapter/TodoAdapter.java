package de.teamprojekt.Activity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import de.teamprojekt.Entity.Todo;
import de.teamprojekt.R;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    private final List<Todo> todoList;
    private final OnItemClickListener onClickListener;

    // Styling
    private String fontSize;
    private int textColor;
    private int notCompletedColor;
    private int completedColor;

    public TodoAdapter(List<Todo> todoList, OnItemClickListener onClickListener) {
        this.todoList = todoList;
        this.onClickListener = onClickListener;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList.clear();
        this.todoList.addAll(todoList);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = todoList.get(position);
        holder.bind(todo, onClickListener, fontSize, textColor, notCompletedColor, completedColor);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void applyPreferences(String fontSize, int textColor, int notCompletedColor, int completedColor) {
        this.fontSize = fontSize;
        this.textColor = textColor;
        this.notCompletedColor = notCompletedColor;
        this.completedColor = completedColor;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvStatus;
        private final TextView tvCategories;
        private final TextView tvPriority;
        private final TextView tvStartDate;
        private final TextView tvDueDate;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvCategories = itemView.findViewById(R.id.tvCategories);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
        }

        public void bind(Todo todo, OnItemClickListener onClickListener, String fontSize, int textColor, int notCompletedColor, int completedColor) {
            // Applying data values
            tvTitle.setText(todo.getTitle());
            tvDescription.setText(getTruncatedDescription(todo.getDescription()));
            tvStatus.setText(todo.getStatus() ? "Completed" : "Not completed");
            tvCategories.setText(todo.getCategory().getName());
            tvPriority.setText(todo.getPriority().getName());
            tvStartDate.setText(dateFormat.format(todo.getStartDate()));
            tvDueDate.setText(dateFormat.format(todo.getEndDate()));

            // Font size
            float size;
            switch (fontSize) {
                case "small":
                    size = 12f;
                    break;
                case "large":
                    size = 18f;
                    break;
                default:
                    size = 14f;
            }
            tvTitle.setTextSize(size);
            tvDescription.setTextSize(size);
            tvStatus.setTextSize(size);
            tvCategories.setTextSize(size);
            tvPriority.setTextSize(size);
            tvStartDate.setTextSize(size);
            tvDueDate.setTextSize(size);

            // Text color
            tvTitle.setTextColor(textColor);
            tvDescription.setTextColor(textColor);
            tvStatus.setTextColor(textColor);
            tvCategories.setTextColor(textColor);
            tvPriority.setTextColor(textColor);
            tvStartDate.setTextColor(textColor);
            tvDueDate.setTextColor(textColor);

            // Set priority background color
            tvPriority.setBackgroundColor(todo.getPriority().getColor());

            // Completed status color
            tvStatus.setBackgroundColor(todo.getStatus() ? completedColor : notCompletedColor);

            // Set click listener
            itemView.setOnClickListener(v -> onClickListener.onItemClick(getAdapterPosition()));
        }

        private String getTruncatedDescription(String description) {
            if (description == null || description.isEmpty()) {
                return "";
            }
            String[] words = description.split(" ");
            int wordCount = Math.min(words.length, 5); // Limit to first 5 words
            StringBuilder truncatedDescription = new StringBuilder();
            for (int i = 0; i < wordCount; i++) {
                truncatedDescription.append(words[i]).append(" ");
            }
            if (words.length > 10) {
                truncatedDescription.append("...");
            }
            return truncatedDescription.toString().trim();
        }
    }
}

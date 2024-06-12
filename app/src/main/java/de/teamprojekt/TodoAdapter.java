package de.teamprojekt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        private final CheckBox cbDone;
        private final TextView tvCategories;
        private final TextView tvPriority;
        private final TextView tvStartDate;
        private final TextView tvDueDate;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            cbDone = itemView.findViewById(R.id.cbDone);
            tvCategories = itemView.findViewById(R.id.tvCategories);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvDueDate = itemView.findViewById(R.id.tvDueDate);
        }

        public void bind(Todo todo, OnItemClickListener onClickListener, String fontSize, int textColor, int notCompletedColor, int completedColor) {
            // Applying data values
            tvTitle.setText(todo.getTitle());
            tvDescription.setText(todo.getDescription());
            cbDone.setChecked(todo.getStatus());
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
            cbDone.setTextSize(size);
            tvCategories.setTextSize(size);
            tvPriority.setTextSize(size);
            tvStartDate.setTextSize(size);
            tvDueDate.setTextSize(size);

            // Text color
            tvTitle.setTextColor(textColor);
            tvDescription.setTextColor(textColor);
            cbDone.setTextColor(textColor);
            tvCategories.setTextColor(textColor);
            tvPriority.setTextColor(textColor);
            tvStartDate.setTextColor(textColor);
            tvDueDate.setTextColor(textColor);

            // Completed status color
            if (todo.getStatus()) {
                cbDone.setBackgroundColor(completedColor);
            } else {
                cbDone.setBackgroundColor(notCompletedColor);
            }

            // Set click listener
            itemView.setOnClickListener(v -> onClickListener.onItemClick(getAdapterPosition()));
        }
    }
}

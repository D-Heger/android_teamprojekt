package de.teamprojekt.Entity;

import androidx.annotation.NonNull;

import java.sql.Date;

import de.teamprojekt.Entity.Enum.Category;
import de.teamprojekt.Entity.Enum.Priority;

public class Todo {
    private int id = -1;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean status;
    private Priority priority;
    private Category category;

    private Todo(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.status = builder.status;
        this.priority = builder.priority;
        this.category = builder.category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return getTitle();
    }

    public static class Builder {
        private int id = -1;
        private String title;
        private String description;
        private Date startDate;
        private Date endDate;
        private boolean status;
        private Priority priority;
        private Category category;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder status(boolean status) {
            this.status = status;
            return this;
        }

        public Builder priority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Todo build() {
            return new Todo(this);
        }
    }
}

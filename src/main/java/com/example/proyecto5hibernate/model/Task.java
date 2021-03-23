package com.example.proyecto5hibernate.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Boolean finished;

    private Instant finishDate;

    public Task() {
    }

    public String getTitle() {
        return title;
    }

    public Task setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getFinished() {
        return finished;
    }

    public Task setFinished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public Instant getCreatedDate() {
        return finishDate;
    }

    public Task setCreatedDate(Instant createdDate) {
        this.finishDate = createdDate;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                ", createdDate=" + finishDate +
                '}';
    }
}

package com.example.proyecto5hibernate.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "task")
    public List<Tag> tags = new ArrayList<>();


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

    public Instant getFinishDate() {
        return finishDate;
    }

    public Task setFinishDate(Instant finishDate) {
        this.finishDate = finishDate;
        return this;
    }


    public List<Tag> getTags() {
        return tags;
    }

    public Task setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", finished=" + finished +
                ", finishDate=" + finishDate +
                '}';
    }
}

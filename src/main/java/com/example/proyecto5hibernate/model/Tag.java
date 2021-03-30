package com.example.proyecto5hibernate.model;

import javax.persistence.*;


@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TagColor color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_task")
    private Task task;


    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public TagColor getColor() {
        return color;
    }

    public Tag setColor(TagColor color) {
        this.color = color;
        return this;
    }

    public Task getTask() {
        return task;
    }

    public Tag setTask(Task task) {
        this.task = task;
        return this;
    }


    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}

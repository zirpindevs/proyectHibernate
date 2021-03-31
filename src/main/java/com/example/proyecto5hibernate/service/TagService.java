package com.example.proyecto5hibernate.service;

import com.example.proyecto5hibernate.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAllFromRepository();

    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tag);

    List<Tag> findAll();
    Tag findOne(Long id);
    Tag findByName(String name);

    }

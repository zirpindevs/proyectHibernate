package com.example.proyecto5hibernate.dao;

import com.example.proyecto5hibernate.model.Tag;

import java.util.List;

public interface TagDAO {

    List<Tag> findAllFromSession();
    List<Tag> findAllFromRepository();
    List<Tag> findAll();
    Tag findById(Long id);
    List<Tag> findByAllByName(String name);
    Tag createTag(Tag tag);
    Tag modifyTag(Tag tag, Tag findedTag);



}

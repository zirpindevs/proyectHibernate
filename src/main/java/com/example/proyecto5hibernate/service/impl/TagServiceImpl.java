package com.example.proyecto5hibernate.service.impl;
import com.example.proyecto5hibernate.model.Tag;

import com.example.proyecto5hibernate.dao.TagDAO;
import com.example.proyecto5hibernate.service.TagService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagDAO tagDAO;

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }



/*    @Override
    public List<Tag> findAllFromSession() {
        List<Tag> results = this.tagDAO.findAllFromSession();
        System.out.println("***********");
        return results;
    }*/

    @Override
    public List<Tag> findAllFromRepository() {
        List<Tag> results = this.tagDAO.findAllFromRepository();
        System.out.println("***********");
        return results;
    }

    @Override
    public Tag createTag(Tag tag) {
        log.info("REST request to create a tag");
        return this.tagDAO.createTag(tag);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        log.info("REST request to update an tag");

        Tag findTag = this.findOne(id);

        if(findTag == null) {
            return null;
        }

        return this.tagDAO.modifyTag(tag, findTag);
    }

    @Override
    public List<Tag> findAll() {
        log.info("REST request to find all tags");

        return this.tagDAO.findAll();
    }

    @Override
    public Tag findOne(Long id) {
        log.info("REST request to find one tag by id");

        if(id == null)
            return null;
        return this.tagDAO.findById(id);
    }

    @Override
    public List<Tag> findByAllByName(String name) {
        log.info("REST request to find an tag by title");

        if(name.isEmpty())
            return null;
        return this.tagDAO.findByAllByName(name);
    }
}

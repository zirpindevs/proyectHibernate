package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.Tag;
import com.example.proyecto5hibernate.model.TagColor;
import com.example.proyecto5hibernate.service.impl.TagServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagServiceImpl tagService;

    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }


    /**
     * CREATE A TAG
     * @param tag
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @PostMapping("/tags")
    @ApiOperation("Post create tag")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws URISyntaxException {
        log.debug("REST request to create a tag: {} ", tag);

        if (tag.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Tag createTag = this.tagService.createTag(tag);

        return ResponseEntity
                .created(new URI("/api/tags/" + createTag.getName()))
                .body(createTag);
    }

    /**
     * UPDATE TAG
     * @param id
     * @param modifiedTag
     * @return ResponseEntity<Tag>
     */
    @PutMapping("/tags/{id}")
    @ApiOperation("PUT update tags")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag modifiedTag){
        log.debug("REST request to update one tag: {} ",modifiedTag);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Tag updateTag = this.tagService.updateTag(id, modifiedTag);

        if(updateTag.getId() == null) {
            log.warn("update tag without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(updateTag);
    }

    /**
     * FIND ALL TAGS
     * @return List<Tag>
     */
    @GetMapping("/tags")
    @ApiOperation("GET find tags")
    public List<Tag> findTags(){
        log.debug("REST request to find all Tags");

        return this.tagService.findAll();
    }

    /**
     * FIND ONE TAG BY ID
     * @param id
     * @return ResponseEntity<Tag>
     * @throws URISyntaxException
     */
    @GetMapping("/tags/{id}")
    @ApiOperation("find tag id")
    public ResponseEntity<Tag> findTagId(@PathVariable Long id) throws URISyntaxException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);

        criteria.where(builder.equal(root.get("id"), id));

        Tag findTag = this.tagService.findOne(id);

        session.close();

        return ResponseEntity.ok().body(findTag);
    }

    /**
     * FIND ALL TAGS BY NAME
     * @param name
     * @return List<Tag>
     * @throws URISyntaxException
     */
    @GetMapping("/tags/name/{name}")
    @ApiOperation("Get tags name")
    public List<Tag> findTagName(@PathVariable String name) throws URISyntaxException {

        return this.tagService.findByAllByName(name);
    }
}

package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.Tag;
import com.example.proyecto5hibernate.model.TagColor;
import com.example.proyecto5hibernate.model.Task;
import com.example.proyecto5hibernate.repository.TagRepository;
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
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    private final Logger log = LoggerFactory.getLogger(TagController.class);

    private final TagRepository tagRepo;

    public TagController(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    @GetMapping("/tags")
    public List<Tag> findTags(){
        log.debug("REST request to find all Tags");
        return tagRepo.findAll();
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) throws URISyntaxException {
        log.debug("REST request to create a tag: {} ", tag);

        Session session = HibernateUtil.getSessionFactory().openSession();

        if (tag.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

     switch (tag.getColor()){
         case RED -> tag.setColor(TagColor.RED);
         case BLUE -> tag.setColor(TagColor.BLUE);
         case GREEN -> tag.setColor(TagColor.GREEN);
         case YELLOW -> tag.setColor(TagColor.YELLOW);
         default -> tag.setColor(TagColor.RED);
     }

        session.beginTransaction();

        session.save(tag);

        session.getTransaction().commit();

        session.close();

        return ResponseEntity
                .created(new URI("/api/tags/" + tag.getName()))
                .body(tag);
    }


    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateFish(@PathVariable Long id, @RequestBody Tag newModifiedTag){
        log.debug("REST request to update one tag: {} ",newModifiedTag);

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);

        criteria.where(builder.equal(root.get("id"), id));

        Tag tag = session.createQuery(criteria).uniqueResult();


        if(tag.getId() == null) {
            log.warn("update fish without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        session.beginTransaction();

        tag.setName(newModifiedTag.getName());

        switch (newModifiedTag.getColor()) {
            case RED -> tag.setColor(TagColor.RED);
            case BLUE -> tag.setColor(TagColor.BLUE);
            case GREEN -> tag.setColor(TagColor.GREEN);
            case YELLOW -> tag.setColor(TagColor.YELLOW);
            default -> tag.setColor(TagColor.RED);
        }

        session.save(tag);

        session.getTransaction().commit();

        session.close();

        return ResponseEntity.ok().body(tag);
    }

    @PostMapping("/tags/{id}")
    public ResponseEntity<Tag> findTagId(@PathVariable Long id) throws URISyntaxException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);

        criteria.where(builder.equal(root.get("id"), id));

        Tag tag = session.createQuery(criteria).uniqueResult();

        System.out.println(tag);
        session.close();

        return ResponseEntity.ok().body(tag);
    }

}

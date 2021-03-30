package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.Task;
import com.example.proyecto5hibernate.repository.TaskRepository;
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
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskRepository taskRepo;

    public TaskController(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    @GetMapping("/tasks")
    public List<Task> findTasks(){
        log.debug("REST request to find all tasks");
        return taskRepo.findAll();
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
        log.debug("REST request to create a task: {} ", task);

        Session session = HibernateUtil.getSessionFactory().openSession();

        if (task.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        session.beginTransaction();

        task.setFinishDate(Instant.now());
        session.save(task);

        session.getTransaction().commit();

        session.close();

        return ResponseEntity
                .created(new URI("/api/tasks/" + task.getTitle()))
                .body(task);
    }


    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateFish(@PathVariable Long id, @RequestBody Task newModifiedTask){
        log.debug("REST request to update one task: {} ",newModifiedTask);

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        criteria.where(builder.equal(root.get("id"), id));

        Task task = session.createQuery(criteria).uniqueResult();


        if(task.getId() == null) {
            log.warn("update fish without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        session.beginTransaction();

        task.setTitle(newModifiedTask.getTitle());
        task.setDescription(newModifiedTask.getDescription());
        task.setFinished(newModifiedTask.getFinished());

        session.save(task);

        session.getTransaction().commit();

        session.close();

        return ResponseEntity.ok().body(task);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<Task> findTaskId(@PathVariable Long id) throws URISyntaxException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        criteria.where(builder.equal(root.get("id"), id));

        Task task = session.createQuery(criteria).uniqueResult();

        System.out.println(task);
        session.close();

        return ResponseEntity.ok().body(task);
    }
}

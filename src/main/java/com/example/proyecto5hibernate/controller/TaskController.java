package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.Task;
import com.example.proyecto5hibernate.service.impl.TaskServiceImpl;
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
public class TaskController {

    private final Logger log = LoggerFactory.getLogger(TaskController.class);

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }


    /**
     * CREATE TASK
     * @param task
     * @return ResponseEntity<Task>
     * @throws URISyntaxException
     */
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) throws URISyntaxException {
        log.debug("REST request to create a task: {} ", task);

/*
        Session session = HibernateUtil.getSessionFactory().openSession();
*/

        if (task.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        Task createTask = this.taskService.createTask(task);

/*
        session.beginTransaction();

        task.setFinishDate(Instant.now());
        session.save(task);

        session.getTransaction().commit();

        session.close();
*/

        return ResponseEntity
                .created(new URI("/api/tasks/" + task.getTitle()))
                .body(task);
    }

    /**
     * UPDATE TASK
     * @param id
     * @param modifiedTask
     * @return ResponseEntity<Task>
     */
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task modifiedTask){
        log.debug("REST request to update one task: {} ",modifiedTask);
/*
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        criteria.where(builder.equal(root.get("id"), id));

        Task task = session.createQuery(criteria).uniqueResult();*/

        Task updateTask = this.taskService.updateTask(id, modifiedTask);


        if(updateTask.getId() == null) {
            log.warn("update task without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

/*        session.beginTransaction();

        task.setTitle(newModifiedTask.getTitle());
        task.setDescription(newModifiedTask.getDescription());
        task.setFinished(newModifiedTask.getFinished());

        session.save(task);

        session.getTransaction().commit();

        session.close();*/

        return ResponseEntity.ok().body(updateTask);
    }

    /**
     * FIND ALL TASKS
     * @return ResponseEntity<Task>
     */
    @GetMapping("/tasks")
    public List<Task> findTasks(){
        log.debug("REST request to find all tasks");

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);
        criteria.select(root);

        List<Task> tasks = session.createQuery(criteria).list();

        session.close();

        return tasks;
    }

    /**
     * FIND TASK BY ID
     * @param id
     * @return ResponseEntity<Task>
     * @throws URISyntaxException
     */
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

    /**
     * FIND TASK BY TITLE
     * @param title
     * @return ResponseEntity<Task>
     * @throws URISyntaxException
     */
    @PostMapping("/tasks/title/{title}")
    public ResponseEntity<Task> findTaskTitle(@PathVariable String title) throws URISyntaxException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);

        criteria.where(builder.equal(root.get("title"), title));

        Task task = session.createQuery(criteria).uniqueResult();

        session.close();

        return ResponseEntity.ok().body(task);
    }

}

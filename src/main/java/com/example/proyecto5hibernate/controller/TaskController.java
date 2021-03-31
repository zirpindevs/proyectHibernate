package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.Task;
import com.example.proyecto5hibernate.model.User;
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

        if (task.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Task createTask = this.taskService.createTask(task);

        return ResponseEntity
                .created(new URI("/api/tasks/" + createTask.getTitle()))
                .body(createTask);
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

        Task updateTask = this.taskService.updateTask(id, modifiedTask);

        if(updateTask.getId() == null) {
            log.warn("update task without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(updateTask);
    }

    /**
     * FIND ALL TASKS
     * @return ResponseEntity<Task>
     */
    @GetMapping("/tasks")
    public List<Task> findTasks(){
        log.debug("REST request to find all tasks");

        return this.taskService.findAll();
    }

    /**
     * FIND TASK BY ID
     * @param id
     * @return ResponseEntity<Task>
     * @throws URISyntaxException
     */
    @PostMapping("/tasks/{id}")
    public ResponseEntity<Task> findTaskId(@PathVariable Long id) throws URISyntaxException {

        Task findTask = this.taskService.findOne(id);
        if (findTask == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findTask);
    }

    /**
     * FIND TASK BY TITLE
     * @param title
     * @return ResponseEntity<Task>
     * @throws URISyntaxException
     */
    @PostMapping("/tasks/title/{title}")
    public ResponseEntity<Task> findTaskTitle(@PathVariable String title) throws URISyntaxException {
        Task findTaskTitle = this.taskService.findByTitle(title);

        if (findTaskTitle == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(findTaskTitle);
    }

}

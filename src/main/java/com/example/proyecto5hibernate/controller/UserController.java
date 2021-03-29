package com.example.proyecto5hibernate.controller;

import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.repository.UserRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }



/*    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAllFromRepository();
    }*/

    @GetMapping("/users")
    public List<User> findUsers(){
        log.debug("REST request to find all users");
        return userRepo.findAll();
    }

    /**
     * CREATE USER
     * @param user
     * @return ResponseEntity<User>
     * @throws URISyntaxException
     */
    @PostMapping("/users")
    public ResponseEntity<User> createFish(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to create an user: {} ", user);

    Session session = HibernateUtil.getSessionFactory().openSession();

        if (user.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        session.beginTransaction();

        User userDB = user;

        userDB.setCreatedDate(Instant.now());
        session.save(userDB);

        session.getTransaction().commit();

        session.close();

        return ResponseEntity
                .created(new URI("/api/users/" + userDB.getName()))
                .body(userDB);
    }

    /**
     * UPDATE USER
     * @param id
     * @param newModifiedUser
     * @return ResponseEntity<User>
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateFish(@PathVariable Long id, @RequestBody User newModifiedUser){
        log.debug("REST request to update one user: {} ",newModifiedUser);

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        criteria.where(builder.equal(root.get("id"), id));

        User user = session.createQuery(criteria).uniqueResult();


        if(user.getId() == null) {
            log.warn("update fish without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        session.beginTransaction();
        System.out.println(newModifiedUser);

        user.setName(newModifiedUser.getName());
        user.setSurname(newModifiedUser.getSurname());
        user.setDni(newModifiedUser.getDni());
        user.setActive(newModifiedUser.getActive());

        session.save(user);

        session.getTransaction().commit();

        session.close();

        return ResponseEntity.ok().body(user);
    }

}

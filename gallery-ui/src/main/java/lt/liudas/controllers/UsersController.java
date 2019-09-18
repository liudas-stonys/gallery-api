package lt.liudas.controllers;

import lt.liudas.dao.UserDao;
import lt.liudas.entities.UserEntity;
import lt.liudas.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private DbService dbServiceImpl;

    @Autowired
    private UserDao userDaoImpl;

    // Get All Users
    @GetMapping("/users")
    public List<UserEntity> getAllUsers() {
        return userDaoImpl.findAll();
    }
}

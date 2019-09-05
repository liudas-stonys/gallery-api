package lt.liudas.controllers;

import lt.liudas.repositoriesDAO.ImageRepository;
import lt.liudas.sbs.dao.UserDao;
import lt.liudas.sbs.models.UserDaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

    private UserDao userDaoImpl;
    private ImageRepository imageRepositoryImpl;

    @Autowired
    public PageController(UserDao userDaoImpl, ImageRepository imageRepositoryImpl) {
        this.userDaoImpl = userDaoImpl;
        this.imageRepositoryImpl = imageRepositoryImpl;
    }

    @GetMapping("/users")
    public List<UserDaoEntity> getUsers() {
        List<UserDaoEntity> users = this.userDaoImpl.findAll();

        return users;
    }

//    @GetMapping("/images")
//    public List<ImageEntity> getImages() {
//        List<ImageEntity> images = this.imageRepositoryImpl.findAll();
//
//        return images;
//    }
}

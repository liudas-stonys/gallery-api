package lt.liudas.sbs.services;

import java.util.ArrayList;

import lt.liudas.sbs.dao.UserDao;
import lt.liudas.sbs.models.UserDTO;
import lt.liudas.sbs.models.UserDaoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("aeom".equals(username)) {
//            return new User("aeom", "$2a$10$/Luc8CKWsH6jSJnAs6Mj..o8JUjuxaNGENsBWQgKXulnuhCsDe8xy",
//                    new ArrayList<>()); // "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6"
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

        UserDaoEntity user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public UserDaoEntity save(UserDTO user) {
        UserDaoEntity newUser = new UserDaoEntity();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}
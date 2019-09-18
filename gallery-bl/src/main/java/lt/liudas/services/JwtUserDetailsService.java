package lt.liudas.services;

import lt.liudas.dao.UserDao;
import lt.liudas.dto.UserDto;
import lt.liudas.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDaoImpl;
    @Autowired
    private UserService userServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDaoImpl.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }

    public UserEntity save(UserDto user) {
        UserEntity newUser = userServiceImpl.createUserEntity(user.getUsername(), user.getEmail(), user.getPassword(), "user");
        return userDaoImpl.save(newUser);
    }
}
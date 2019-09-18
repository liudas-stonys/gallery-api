package lt.liudas.services;

import lt.liudas.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bcrypEncoder;

    @Override
    public UserEntity createUserEntity(String username, String email, String password, String role) {
        return new UserEntity(username, email, bcrypEncoder.encode(password), role);
    }
}

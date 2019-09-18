package lt.liudas.services;

import lt.liudas.entities.UserEntity;

public interface UserService {
    UserEntity createUserEntity(String username, String email, String password, String role);
}

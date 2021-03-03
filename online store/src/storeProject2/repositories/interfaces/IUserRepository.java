package storeProject2.repositories.interfaces;

import storeProject2.entities.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
}

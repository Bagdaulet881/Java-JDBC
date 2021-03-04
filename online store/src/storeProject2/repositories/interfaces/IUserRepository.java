package storeProject2.repositories.interfaces;

import storeProject2.entities.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    boolean createSalesman(User user);
    
    User getUser(int id);
    List<User> getAllUsers();
    boolean addOrderToClient(int client_id, int order_id);
    
    User login(String login, String password);
    User loginSalesman(String login, String password);
}

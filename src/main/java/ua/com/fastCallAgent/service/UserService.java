package ua.com.fastCallAgent.service;

import ua.com.fastCallAgent.entity.User;

import java.util.List;

/**
 * Created by koko on 31.08.16.
 */
public interface UserService {

    void save(User user);
    List<User> findAll();
    User findOne(int id);
    void delete(int id);

}

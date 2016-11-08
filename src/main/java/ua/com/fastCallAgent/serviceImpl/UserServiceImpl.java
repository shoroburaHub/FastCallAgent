package ua.com.fastCallAgent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.fastCallAgent.entity.User;
import ua.com.fastCallAgent.repository.UserRepository;
import ua.com.fastCallAgent.service.UserService;

import java.util.List;

/**
 * Created by koko on 31.08.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(int id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }
}

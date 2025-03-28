package my.kaytmb.dormitory.service;


import my.kaytmb.dormitory.dto.UserRequest;
import my.kaytmb.dormitory.entity.User;
import my.kaytmb.dormitory.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kay 27.03.2025
 */
@Service
public class UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Integer createUser(UserRequest request) {
        if (userRepository.findByLogin(request.getLogin()).isPresent()) {
            throw new RuntimeException("Пользователь с таким логином уже зарегистрирован!");
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setSurname(request.getSurname());
        user.setFirstname(request.getFirstname());
        user.setPatrname(request.getPatrname());
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsBlocked(false);
        userRepository.save(user);
        return user.getId();
    }

    @Transactional
    public void updateUser(UserRequest request) {
        User user = userRepository.getReferenceById(request.getId());
        user.setSurname(request.getSurname());
        user.setFirstname(request.getFirstname());
        user.setPatrname(request.getPatrname());
        user.setRole(User.Role.valueOf(request.getRole()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public User updateInfo(UserRequest request) {
        User user = userRepository.getReferenceById(request.getId());
        user.setSurname(request.getSurname());
        user.setFirstname(request.getFirstname());
        user.setPatrname(request.getPatrname());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User getUser(Integer id) {
        return userRepository.getReferenceById(id);
    }

    @Transactional
    public void disableUser(Integer id) {
        User user = userRepository.getReferenceById(id);
        user.setIsBlocked(true);
        userRepository.save(user);
    }

    @Transactional
    public void enableUser(Integer id) {
        User user = userRepository.getReferenceById(id);
        user.setIsBlocked(false);
        userRepository.save(user);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}

package com.appstra.users.implementation;

import com.appstra.users.entity.User;
import com.appstra.users.repository.UserRepository;
import com.appstra.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.regex.Pattern;

import java.util.List;

@Service
public class UserImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        if (!isValidPassword(user.getUserPassword())) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula, un número, un carácter especial y un mínimo de 5 caracteres.");
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user);
    }

    @Override
    public User upDateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new IllegalArgumentException("El usuario no existe: " + user.getUserId()));
        if (!isValidPassword(user.getUserPassword())) {
            throw new IllegalArgumentException("La contraseña debe contener al menos una mayúscula, un número, un carácter especial y un mínimo de 5 caracteres.");
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setUserCreationDate(existingUser.getUserCreationDate());

        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(password).matches();
    }
}

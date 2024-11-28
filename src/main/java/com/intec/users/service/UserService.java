package com.intec.users.service;


import com.intec.users.entity.StateType;
import com.intec.users.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    User saveUser (User user);
    User upDateUser (User user);
    Boolean deleteUser (Integer userId);
    List<User> listUser();

}

package com.appstra.users.service;


import com.appstra.users.dto.MassiveUsersDTO;
import com.appstra.users.entity.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    @Transactional
    User saveUser (User user);
    User upDateUser (User user);
    Boolean deleteUser (Integer userId);
    List<User> listUser();
    List<MassiveUsersDTO> uploadMassiveUsers(MultipartFile document);

}

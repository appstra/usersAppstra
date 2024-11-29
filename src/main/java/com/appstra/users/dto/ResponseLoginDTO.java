package com.appstra.users.dto;

import com.appstra.users.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Data
@Getter
@Setter
public class ResponseLoginDTO {
    private String token;
    private Optional<User> user;
    private String message;
}

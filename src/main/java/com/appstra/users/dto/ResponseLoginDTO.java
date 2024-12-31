package com.appstra.users.dto;

import com.appstra.users.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Getter
@Setter
public class ResponseLoginDTO {
    private String token;
    private Optional<User> user;
    private String message;
    private List<Map<String, Object>> listCompany;
}

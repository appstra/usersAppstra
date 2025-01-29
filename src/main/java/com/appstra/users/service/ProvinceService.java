package com.appstra.users.service;

import com.appstra.users.entity.Department;
import com.appstra.users.entity.Province;

import java.util.List;

public interface ProvinceService {
    List<Province> listDepartment(Integer departmentId);
}

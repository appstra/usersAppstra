package com.appstra.users.service;

import com.appstra.users.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> listDepartment(Integer departmentId);
}

package com.appstra.users.repository;

import com.appstra.users.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository  extends JpaRepository<Department,Integer> {
    List<Department> findByDepartmentId(Integer departmentId);
}

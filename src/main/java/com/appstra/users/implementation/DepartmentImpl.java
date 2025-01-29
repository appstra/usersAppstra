package com.appstra.users.implementation;

import com.appstra.users.entity.Department;
import com.appstra.users.repository.DepartmentRepository;
import com.appstra.users.service.DepartmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> listDepartment(Integer countryId) {
        return departmentRepository.findByCountryCountryId(countryId);
    }
}

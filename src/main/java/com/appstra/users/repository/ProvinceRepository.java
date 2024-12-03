package com.appstra.users.repository;

import com.appstra.users.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    List<Province> findByProvinceId(Integer provinceId);
}

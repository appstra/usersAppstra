package com.appstra.users.repository;

import com.appstra.users.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    List<City> findByCityId(Integer cityId);
}

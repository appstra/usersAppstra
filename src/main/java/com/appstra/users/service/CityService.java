package com.appstra.users.service;

import com.appstra.users.entity.City;

import java.util.List;

public interface CityService {
    List<City> findByProvinceProvinceId(Integer provinceId);
}

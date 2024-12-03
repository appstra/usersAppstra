package com.appstra.users.implementation;

import com.appstra.users.entity.City;
import com.appstra.users.repository.CityRepository;
import com.appstra.users.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityImpl implements CityService {
    private final CityRepository cityRepository;

    public CityImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public List<City> findByCityId(Integer cityId) {
        return cityRepository.findByCityId(cityId);
    }
}

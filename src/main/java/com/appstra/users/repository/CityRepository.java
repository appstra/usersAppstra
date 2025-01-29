package com.appstra.users.repository;

import com.appstra.users.dto.CityDTO;
import com.appstra.users.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    List<City> findByCityId(Integer cityId);
    List<City> findByProvinceProvinceId(Integer provinceId);

    @Query("""
        SELECT new com.appstra.users.dto.CityDTO(
            coun.countryId,
            coun.countryName,
            depa.departmentId,
            depa.departmentName,
            prov.provinceId,
            prov.provinceName,
            city.cityId,
            city.cityName
        )
        FROM
            City city
            INNER JOIN city.province prov
            INNER JOIN prov.department depa
            INNER JOIN depa.country coun
        WHERE
            city.cityId = :cityId
    """)
    List<CityDTO> listInfoCity(Integer cityId);
}

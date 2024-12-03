package com.appstra.users.repository;

import com.appstra.users.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CountryRepository extends JpaRepository<Country,Integer> {
}

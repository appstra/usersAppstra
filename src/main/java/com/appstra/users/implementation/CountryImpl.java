package com.appstra.users.implementation;

import com.appstra.users.entity.Country;
import com.appstra.users.repository.CountryRepository;
import com.appstra.users.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listCountry() {
        return countryRepository.findAll();
    }
}

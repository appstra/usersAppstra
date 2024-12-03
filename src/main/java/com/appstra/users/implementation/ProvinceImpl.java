package com.appstra.users.implementation;

import com.appstra.users.entity.Province;
import com.appstra.users.repository.ProvinceRepository;
import com.appstra.users.service.ProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceImpl implements ProvinceService {
    public final ProvinceRepository provinceRepository;

    public ProvinceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<Province> listDepartment(Integer provinceId) {
        return provinceRepository.findByProvinceId(provinceId);
    }
}

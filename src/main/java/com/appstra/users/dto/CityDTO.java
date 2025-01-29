package com.appstra.users.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class CityDTO {
    private Integer countryId;
    private String countryName;
    private Integer departmentId;
    private String departmentName;
    private Integer provinceId;
    private String provinceName;
    private Integer cityId;
    private String cityName;

    public CityDTO(Integer countryId, String countryName, Integer departmentId, String departmentName, Integer provinceId, String provinceName, Integer cityId, String cityName) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

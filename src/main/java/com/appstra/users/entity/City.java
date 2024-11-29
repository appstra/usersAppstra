package com.appstra.users.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "CITY", schema = "PARAMETERIZATION")
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private Integer cityId;

    @Column(name = "CITY_CODE")
    private String cityCode;

    @Column(name = "CITY_NAME")
    private String cityName;

    @Column(name = "CITY_CREATION_DATE")
    private Timestamp cityCreationDate;

    @Column(name = "CITY_EDIT_DATE")
    private Timestamp cityEditDate;

    @Column(name = "CITY_EDIT_USER_ID")
    private Integer cityEditUserID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "PROV_ID", referencedColumnName = "PROV_ID")
    private Province province;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "city")
    private List<Person> personList;

}

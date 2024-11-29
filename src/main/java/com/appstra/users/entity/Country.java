package com.appstra.users.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COUNTRY",schema = "PARAMETERIZATION")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUN_ID")
    private Integer countryId;

    @Column(name = "COUN_NAME")
    private String countryName;

    @Column(name = "COUN_CREATION_DATE")
    private Timestamp countryCreationDate;

    @Column(name = "COUN_EDIT_DATE")
    private Timestamp countryEditDate;

    @OneToMany(mappedBy = "country")
    private List<Department> departmentList;

}

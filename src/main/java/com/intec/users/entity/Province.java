package com.intec.users.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "PROVINCE", schema = "PARAMETERIZATION")
@Entity
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROV_ID")
    private Integer provinceId;

    @Column(name = "PROV_NAME")
    private String provinceName;

    @Column(name = "PROV_CREATION_DATE")
    private Timestamp provinceCreationDate;

    @Column(name = "PROV_EDIT_DATE")
    private Timestamp provinceEditDate;

    @Column(name = "PROV_EDIT_USER_ID")
    private Integer provinceEditUserID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "DEPA_ID", referencedColumnName = "DEPA_ID")
    private Department department;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "province")
    private List<City> municipalitiesList;
}

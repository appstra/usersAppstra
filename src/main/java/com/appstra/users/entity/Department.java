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
@Table(name = "DEPARTMENT", schema = "PARAMETERIZATION")
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPA_ID")
    private Integer departmentId;

    @Column(name = "DEPA_NAME")
    private String departmentName;

    @Column(name = "DEPA_CREATION_DATE")
    private Timestamp departmentCreationDate;

    @Column(name = "DEPA_EDIT_DATE")
    private Timestamp departmentEditDate;

    @Column(name = "DEPA_EDIT_USER_ID")
    private Integer departmentEditUserID;

    @ManyToOne
    @JoinColumn(name = "COUN_ID", referencedColumnName = "COUN_ID")
    private Country country;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "department")
    private List<Province> provinceList;

}

package com.intec.users.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "PERSON",schema = "PARAMETERIZATION")

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERS_ID")
    private Integer personId;

    @Column(name = "PERS_FIRST_NAME")
    private String personFirstName;

    @Column(name = "PERS_LAST_NAME")
    private String personLastName;

    @Column(name = "PERS_EMAIL")
    private String personEmail;

    @Column(name = "PERS_MOBILE_PHONE")
    private String personMobilePhone;

    @Column(name = "PERS_LANDLINE_PHONE")
    private String personLandlinePhone;

    @Column(name = "PERS_ADDRESS")
    private String personAddress;

    @ManyToOne
    @JoinColumn(name = "CITY_ID", referencedColumnName = "CITY_ID")
    private City city;

    @Column(name = "PERS_BLOOD_TYPE")
    private String personBloodType;

    @Column(name = "PERS_CREATION_DATE")
    private Timestamp personCreationDate;

    @Column(name = "PERS_EDIT_DATE")
    private Timestamp personEditDate;

    @Column(name = "PERS_EDIT_USER_ID")
    private Integer personEditUserID;
    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "DCTY_ID", referencedColumnName = "DCTY_ID")
    private DocumentType documentType;
}

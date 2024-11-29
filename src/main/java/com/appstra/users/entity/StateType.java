package com.appstra.users.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "STATE_TYPE",schema = "PARAMETERIZATION")

public class StateType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STTY_ID")
    private Integer stateTypeId;

    @Column(name = "STTY_NAME")
    private String stateTypeName;

    @Column(name = "STTY_DESCRIPTION")
    private String stateTypeDescription;

    @Column(name = "STTY_CREATION_DATE")
    private Timestamp stateTypeCreationDate;

    @Column(name = "STTY_EDIT_DATE")
    private Timestamp stateTypeEditDate;

    @Column(name = "STTY_EDIT_USER_ID")
    private Integer stateTypeEditUserID;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "stateType")
    private List<State> stateList;
}

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
@Table(name = "STATE",schema = "PARAMETERIZATION")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STAT_ID")
    private Integer stateId;

    @Column(name = "STAT_NAME")
    private String stateName;

    @Column(name = "STAT_DESCRIPTION")
    private String stateDescription;

    @Column(name = "STAT_CREATION_DATE")
    private Timestamp stateCreationDate;

    @Column(name = "STAT_EDIT_DATE")
    private Timestamp stateEditDate;

    @Column(name = "STAT_EDIT_USER_ID")
    private Integer stateEditUserID;
    @ManyToOne
    @JoinColumn(name = "STTY_ID", referencedColumnName = "STTY_ID")
    private StateType stateType;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "state")
    private List<User> userList;

}

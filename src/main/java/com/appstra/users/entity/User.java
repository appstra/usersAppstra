package com.appstra.users.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "USER",schema = "SECURITY")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "USER_USER")
    private String userUser;

    @Column(name = "USER_PASSWORD")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;

    @ManyToOne
    @JoinColumn(name = "STATE_ID", referencedColumnName = "STAT_ID")
    private State state;

    @Column(name = "ROLE_ID")
    @Comment("ID del rol asociado al usuario, base de datos de compañia")
    private Integer roleId;

    @Column(name = "USER_CREATION_DATE")
    private Timestamp userCreationDate;

    @Column(name = "USER_EDIT_DATE")
    private Timestamp userEditDate;

    @Column(name = "USER_EDIT_USER_ID")
    private Integer userEditUserID;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Person person;
}
